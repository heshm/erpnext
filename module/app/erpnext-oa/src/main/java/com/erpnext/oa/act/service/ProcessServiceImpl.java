package com.erpnext.oa.act.service;

import java.util.ArrayList;
import java.util.List;

import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
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
	
	
	@Autowired
	private RepositoryService repositoryService;

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

	
}
