package com.erpnext.oa.act.endpoint;

import org.activiti.engine.FormService;
import org.activiti.engine.form.TaskFormData;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
	@Autowired
	private FormService formService;
	
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
	
	@GetMapping("/form-data/{taskId}")
	public TaskFormDTO getFormData(@PathVariable String taskId) {
		TaskFormData formData = formService.getTaskFormData(taskId);
		TaskFormDTO result = new TaskFormDTO();
		BeanUtils.copyProperties(formData, result);
		return result;
	}

}
