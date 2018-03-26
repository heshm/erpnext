package com.erpnext.oa.act.service;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.repository.ProcessDefinitionQuery;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.engine.runtime.ProcessInstanceQuery;
import org.flowable.form.api.FormDefinition;
import org.flowable.form.api.FormRepositoryService;
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
		    System.out.println(deployment);
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
		ProcessDefinitionQuery definitionQuery = repositoryService.createProcessDefinitionQuery();
		definitionQuery.processDefinitionId(processDefinitionId);
		ProcessDefinition definition = definitionQuery.singleResult();
		return definition.hasStartFormKey();
	}

	@Override
	public ProcessQueryDTO getOneProcessDefinition(String processDefinitionId) {
		ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery()
				.processDefinitionId(processDefinitionId).singleResult();
		String deploymentId = processDefinition.getDeploymentId();
	    Deployment deployment = repositoryService.createDeploymentQuery().deploymentId(deploymentId).singleResult();
		return new ProcessQueryDTO(
	    			new ProcessDefinitionDTO(processDefinition),
	    			new DeploymentDTO(deployment)
	    	);
	}
	
}
