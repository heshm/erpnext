package com.erpnext.oa.act.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricTaskInstance;
import org.activiti.engine.history.HistoricTaskInstanceQuery;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfo;
import org.activiti.engine.task.TaskInfoQueryWrapper;
import org.activiti.form.api.FormRepositoryService;
import org.activiti.form.api.FormService;
import org.activiti.form.model.FormDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erpnext.framework.web.service.exception.BadRequestException;
import com.erpnext.framework.web.util.AuthenticationUtils;
import com.erpnext.oa.act.dto.CreateProcessInstanceRepresentation;
import com.erpnext.oa.act.dto.TaskDTO;

@Service
@Transactional(readOnly = true)
public class ActTaskServiceImpl implements ActTaskService {

	@Autowired
	private RepositoryService repositoryService;
	
	@Autowired
	private FormService formService;
	
	@Autowired
	private FormRepositoryService formRepositoryService;
	
	@Autowired
	private RuntimeService runtimeService;
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private TaskService taskService;
	
	@Autowired
	private IdentityService identityService;

	@Override
	@Transactional
	public void startProcessInstance(CreateProcessInstanceRepresentation startRequest) {
		if (StringUtils.isEmpty(startRequest.getProcessDefinitionId())) {
			throw new BadRequestException("Process definition id is required");
		}

		FormDefinition formDefinition = null;
		Map<String, Object> variables = null;
		ProcessDefinition processDefinition = repositoryService
				.getProcessDefinition(startRequest.getProcessDefinitionId());
		
		if (startRequest.getValues() != null || startRequest.getOutcome() != null) {
			BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinition.getId());
			Process process = bpmnModel.getProcessById(processDefinition.getKey());
			FlowElement startElement = process.getInitialFlowElement();
			if (startElement instanceof StartEvent) {
				StartEvent startEvent = (StartEvent) startElement;
				if (!StringUtils.isEmpty(startEvent.getFormKey())) {
					formDefinition = formRepositoryService.getFormDefinitionByKey(startEvent.getFormKey());
					if (formDefinition != null) {
						variables = formService.getVariablesFromFormSubmission(formDefinition, startRequest.getValues(),
								startRequest.getOutcome());
					}
				}
			}
		}
		
		identityService.setAuthenticatedUserId(AuthenticationUtils.getUserId());
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(startRequest.getProcessDefinitionId(), variables);
		
		HistoricProcessInstance historicProcess = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstance.getId()).singleResult();

	    if (formDefinition != null) {
	    	formService.storeSubmittedForm(variables, formDefinition, null, historicProcess.getId());
	    }
	    
		
	}

	@Override
	public List<TaskDTO> getToDoTask(String userId) {
		List<Task> task = taskService.createTaskQuery().taskCandidateUser(userId).list();
		List<TaskDTO> list = new ArrayList<>(task.size());
		task.forEach(t -> {
			list.add(new TaskDTO(t));
		});
		return list;
	}

	@Override
	public List<TaskDTO> getDoingTask(String userId) {
		List<Task> task = taskService.createTaskQuery().taskAssignee(userId).list();
		List<TaskDTO> list = new ArrayList<>(task.size());
		task.forEach(t -> {
			list.add(new TaskDTO(t));
		});
		return list;
	}

	@Override
	public List<TaskDTO> getTasks(String userId) {
		List<TaskDTO> todoTask = getToDoTask(userId);
		List<TaskDTO> doingTask = getDoingTask(userId);
		List<TaskDTO> list = new ArrayList<>(todoTask.size() + doingTask.size());
		list.addAll(todoTask);
		list.addAll(doingTask);
		Collections.sort(list,new TaskDTO.IdOrder());
		return list;
	}

	@Override
	public TaskDTO getOneTask(String taskId){
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ProcessDefinition processDefinition = null;
		if(!StringUtils.isEmpty(task.getProcessDefinitionId())) {
			processDefinition = repositoryService.getProcessDefinition(task.getProcessDefinitionId());
		}
		return new TaskDTO(task,processDefinition);
	}

	@Override
	public List<TaskDTO> listTasks(String processInstanceId, String state) {
		TaskInfoQueryWrapper taskInfoQueryWrapper = null;
		
		if (null != state && "completed".equals(state)) {
			HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();
		    historicTaskInstanceQuery.finished();
		    taskInfoQueryWrapper = new TaskInfoQueryWrapper(historicTaskInstanceQuery);
		}else {
			taskInfoQueryWrapper = new TaskInfoQueryWrapper(taskService.createTaskQuery());
		}
		taskInfoQueryWrapper.getTaskInfoQuery().processInstanceId(processInstanceId);
		
		List<? extends TaskInfo> tasks = taskInfoQueryWrapper.getTaskInfoQuery().list();
		List<TaskDTO> result = null;
		if(tasks != null) {
			result = new ArrayList<>(tasks.size());
			for(TaskInfo task: tasks) {
				result.add(new TaskDTO(task));
			}
		}
		
		return result;
	}

	@Override
	@Transactional
	public void completeTask(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();

	    if (task == null) {
	      throw new BadRequestException("Task with id: " + taskId + " does not exist");
	    }
	    taskService.complete(task.getId());
	}

	@Override
	public FormDefinition getTaskForm(String taskId) {
		
		return null;
	}
	
	private HistoricTaskInstance getHistoricTaskInstance(String taskId) {
		HistoricTaskInstance task = historyService.createHistoricTaskInstanceQuery().taskId(taskId).singleResult();
		return task;
	}

}
