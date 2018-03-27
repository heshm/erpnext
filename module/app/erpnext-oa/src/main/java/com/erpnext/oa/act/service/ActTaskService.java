package com.erpnext.oa.act.service;

import java.util.List;

import com.erpnext.oa.act.dto.CreateProcessInstanceRepresentation;
import com.erpnext.oa.act.dto.TaskDTO;
import com.erpnext.oa.act.dto.TaskFormDTO;

public interface ActTaskService {
	
	void startProcessInstance(CreateProcessInstanceRepresentation startRequest);
	
	List<TaskDTO> getToDoTask(String userId);

	List<TaskDTO> getDoingTask(String userId);
	
	List<TaskDTO> getTasks(String userId);

	TaskDTO getOneTask(String taskId);
	
	List<TaskDTO> listTasks(String processInstanceId,String state);
	
	void completeTask(String taskId);
	
	void claimTask(String taskId);
	
	TaskFormDTO getTaskForm(String taskId);
}
