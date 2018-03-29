package com.erpnext.oa.act.service;

import java.util.List;
import java.util.Map;

import com.erpnext.oa.act.dto.ProcessInstanceDTO;

import org.flowable.engine.history.HistoricProcessInstance;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProcessInstanceService {
	
	String STATE_RUNNING = "running";
	String STATE_COMPLETED = "completed";
	String STATE_ALL = "all";
	
	List<ProcessInstanceDTO> listProcessInstanceDTO(String userId,String state);

	void deleteProcessInstance(String processInstanceId);
	
	Page<HistoricProcessInstance> listHisProcessInstance(Pageable pageable,Map<String, Object> filter);

}
