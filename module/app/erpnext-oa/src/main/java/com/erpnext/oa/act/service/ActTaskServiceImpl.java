package com.erpnext.oa.act.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.form.api.FormRepositoryService;
import org.activiti.form.api.FormService;
import org.activiti.form.model.FormDefinition;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erpnext.framework.web.service.exception.BadRequestException;
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
	private TaskService taskService;

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
		
		ProcessInstance processInstance = runtimeService.startProcessInstanceById(startRequest.getProcessDefinitionId(), variables);
		
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
	
	

}
