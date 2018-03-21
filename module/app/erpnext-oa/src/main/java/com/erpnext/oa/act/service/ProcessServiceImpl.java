package com.erpnext.oa.act.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.runtime.ProcessInstanceQuery;
import org.activiti.form.api.FormRepositoryService;
import org.activiti.form.model.FormDefinition;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.oa.act.dto.DeploymentDTO;
import com.erpnext.oa.act.dto.ProcessDefinitionDTO;
import com.erpnext.oa.act.dto.ProcessQueryDTO;
import org.springframework.util.StringUtils;

@Service
@Transactional(readOnly=true)
public class ProcessServiceImpl implements ProcessService {

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String XML = "xml";
	private static final String PNG = "png";
	
	@Autowired
	private RepositoryService repositoryService;
	@Autowired
	private RuntimeService runtimeService;
	@Autowired
	private FormRepositoryService formRepositoryService;

	@Override
	public Page<ProcessQueryDTO> getPageProcessDefinitionList(Pageable pageable,String category) {
		ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
		if(!StringUtils.isEmpty(category)){
			processDefinitionQuery.processDefinitionCategory(category);
		}
		int total = (int)processDefinitionQuery.count();
		List<ProcessDefinition> processList = processDefinitionQuery.listPage(pageable.getOffset(), pageable.getPageSize());
		List<ProcessQueryDTO> result = new ArrayList<ProcessQueryDTO>(pageable.getPageSize());
		for(ProcessDefinition processDefinition : processList){
			String deploymentId = processDefinition.getDeploymentId();
		    Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
		    ProcessQueryDTO processDTO = new ProcessQueryDTO(
		    		new ProcessDefinitionDTO(processDefinition),
		    		new DeploymentDTO(deployment)
		    		);
		    result.add(processDTO);
		}
		return new PageImpl<>(result, pageable, total);
	}

	@Override
	public void getResource(String processDefinitionId, String resourceType, HttpServletResponse response) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().
				processDefinitionId(processDefinitionId).singleResult();
		String resourceName = new String();
		if(XML.equals(resourceType)){
			resourceName = processDefinition.getResourceName();
			response.setHeader("Content-Type", "text/xml");
		}
		if(PNG.equals(resourceType)){
			resourceName = processDefinition.getDiagramResourceName();
			response.setHeader("Content-Type", "image/png");
		}
		response.setCharacterEncoding("utf-8");
		InputStream in = repositoryService.getResourceAsStream(processDefinition.getDeploymentId(), resourceName);
		try {
			IOUtils.copy(in, response.getOutputStream());
			response.flushBuffer();
		} catch (IOException e) {
			logger.error("导出流程的资源文件失败:processDefinitionId={}", processDefinitionId, e);
		}
		
	}

	@Override
	@Transactional
	public void deleteDeployment(String deploymentId) {
		repositoryService.deleteDeployment(deploymentId);
	}

	@Override
	public Page<ProcessInstance> getRunningInstance(Pageable pageable) {
		ProcessInstanceQuery processInstanceQuery = runtimeService.createProcessInstanceQuery();
		int total = (int)processInstanceQuery.count();
		List<ProcessInstance> instanceList = processInstanceQuery.listPage(pageable.getOffset(), pageable.getPageSize());
		return new PageImpl<>(instanceList, pageable, total);
	}

	@Override
	public boolean processDefinitionHasStartForm(String processDefinitionId) {
		BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);
		List<StartEvent> startEvents = bpmnModel.getMainProcess().findFlowElementsOfType(StartEvent.class, false);
		for (StartEvent startEvent : startEvents) {
			if (!StringUtils.isEmpty(startEvent.getFormKey())) {
				FormDefinition formDefinition = formRepositoryService.getFormDefinitionByKey(startEvent.getFormKey());
				if(null != formDefinition) {
					return true;
				}
			}
		}
		return false;
	}
	
}
