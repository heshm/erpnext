package com.erpnext.oa.act.service;

import java.util.List;

import com.erpnext.oa.act.dto.ProcessInstanceDTO;

public interface ProcessInstanceService {
	
	String STATE_RUNNING = "running";
	String STATE_COMPLETED = "completed";
	String STATE_ALL = "all";
	
	List<ProcessInstanceDTO> listProcessInstanceDTO(String userId,String state);

	void deleteProcessInstance(String processInstanceId);

}
