package com.erpnext.oa.act.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erpnext.oa.act.dto.ProcessQueryDTO;
import com.erpnext.oa.act.dto.ProcessInstanceDTO;

public interface ProcessService {
	
	Page<ProcessQueryDTO> getPageProcessDefinitionList(Pageable pageable,String category);
	
	public void getResource(String processDefinitionId,String resourceType, HttpServletResponse response);
	
	public void deleteDeployment(String deploymentId);
	
	Page<ProcessInstance> getRunningInstance(Pageable pageable);

}
