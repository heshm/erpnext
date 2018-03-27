package com.erpnext.oa.act.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.framework.web.util.AuthenticationUtils;
import com.erpnext.oa.act.dto.CreateProcessInstanceRepresentation;
import com.erpnext.oa.act.dto.TaskDTO;
import com.erpnext.oa.act.dto.TaskFormDTO;
import com.erpnext.oa.act.service.ActTaskService;

@RestController
@RequestMapping(value = "/oa/act/task")
public class TaskEndpoint extends BaseEndpoint{
	
	@Autowired
	private ActTaskService actTaskService;
	
	@PostMapping("/process-instances")
	public String startNewProcessInstance(@RequestBody CreateProcessInstanceRepresentation startRequest) {
		actTaskService.startProcessInstance(startRequest);
		return UPDATED;
	}
	
	@GetMapping("/doing")
	public List<TaskDTO> getDoingTask(){
		String userId = AuthenticationUtils.getUserId();
		return actTaskService.getDoingTask(userId);
	}
	
	@GetMapping("/list-tasks")
	public List<TaskDTO> getTasks() {
		String userId = AuthenticationUtils.getUserId();
		return actTaskService.getTasks(userId);
	}

	@GetMapping("/{taskId}")
	public TaskDTO getOne(@PathVariable String taskId) {
		return actTaskService.getOneTask(taskId);
	}
	
	@GetMapping("/list-tasks/{processInstanceId}/{state}")
	public List<TaskDTO> listTasks(@PathVariable("processInstanceId") String processInstanceId,
			@PathVariable(name="state",required=false) String state){
		return actTaskService.listTasks(processInstanceId, state);
	}
	
	@PutMapping("/{taskId}/action/complete")
	public String completeTask(@PathVariable String taskId) {
		actTaskService.completeTask(taskId);
		return UPDATED;
	}
	
	@PutMapping("/{taskId}/action/claim")
	public String claimTask(@PathVariable String taskId) {
		actTaskService.claimTask(taskId);
		return UPDATED;
    }
	
	@GetMapping("/task-form/{taskId}")
	public TaskFormDTO getFormData(@PathVariable String taskId) {
		return actTaskService.getTaskForm(taskId);
	}

}
