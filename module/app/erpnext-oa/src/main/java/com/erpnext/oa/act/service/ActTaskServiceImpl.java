package com.erpnext.oa.act.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.Process;
import org.flowable.bpmn.model.StartEvent;
import org.flowable.cmmn.api.CmmnTaskService;
import org.flowable.engine.HistoryService;
import org.flowable.engine.IdentityService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.form.api.FormDefinition;
import org.flowable.form.api.FormRepositoryService;
import org.flowable.form.api.FormService;
import org.flowable.form.model.FormField;
import org.flowable.form.model.FormFieldTypes;
import org.flowable.form.model.FormModel;
import org.flowable.task.api.Task;
import org.flowable.task.api.TaskInfo;
import org.flowable.task.api.TaskInfoQueryWrapper;
import org.flowable.task.api.history.HistoricTaskInstanceQuery;
import org.apache.commons.lang3.time.DateUtils;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erpnext.framework.domain.UserRoleXref;
import com.erpnext.framework.mapper.UserRoleXrefMapper;
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
	private RuntimeService runtimeService;

	@Autowired
	private HistoryService historyService;

	@Autowired
	private TaskService taskService;
	
	@Autowired
    private CmmnTaskService cmmnTaskService;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private UserRoleXrefMapper userRoleXrefMapper;

	@Override
	@Transactional
	public void startProcessInstance(CreateProcessInstanceRepresentation startRequest) {
		if (StringUtils.isEmpty(startRequest.getProcessDefinitionId())) {
			throw new BadRequestException("Process definition id is required");
		}

		identityService.setAuthenticatedUserId(AuthenticationUtils.getUserId());

		runtimeService.startProcessInstanceWithForm(startRequest.getProcessDefinitionId(), startRequest.getOutcome(),
				startRequest.getValues(), startRequest.getName());

	}

	@Override
	public List<TaskDTO> getToDoTask(String userId) {
		List<UserRoleXref> userRoles = userRoleXrefMapper.selectList(userId, null);
		List<String> groups = new LinkedList<>();
		if (userRoles != null) {
			userRoles.forEach(userRole -> {
				groups.add(userRole.getRoleId());
			});
		}

		List<Task> task = taskService.createTaskQuery().taskCandidateGroupIn(groups).list();
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
		Collections.sort(list, new TaskDTO.IdOrder());
		return list;
	}

	@Override
	public TaskDTO getOneTask(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		ProcessDefinition processDefinition = null;
		if (!StringUtils.isEmpty(task.getProcessDefinitionId())) {
			processDefinition = repositoryService.getProcessDefinition(task.getProcessDefinitionId());
		}
		return new TaskDTO(task, processDefinition);
	}

	@Override
	public List<TaskDTO> listTasks(String processInstanceId, String state) {
		TaskInfoQueryWrapper taskInfoQueryWrapper = null;

		if (null != state && "completed".equals(state)) {
			HistoricTaskInstanceQuery historicTaskInstanceQuery = historyService.createHistoricTaskInstanceQuery();
			historicTaskInstanceQuery.finished();
			taskInfoQueryWrapper = new TaskInfoQueryWrapper(historicTaskInstanceQuery);
		} else {
			taskInfoQueryWrapper = new TaskInfoQueryWrapper(taskService.createTaskQuery());
		}
		taskInfoQueryWrapper.getTaskInfoQuery().processInstanceId(processInstanceId);

		List<? extends TaskInfo> tasks = taskInfoQueryWrapper.getTaskInfoQuery().list();
		List<TaskDTO> result = null;
		if (tasks != null) {
			result = new ArrayList<>(tasks.size());
			for (TaskInfo task : tasks) {
				result.add(new TaskDTO(task));
			}
		}

		return result;
	}

	@Override
	@Transactional
	public void completeTask(String taskId) {
		Task task = taskService.createTaskQuery().taskId(taskId).singleResult();
		if(StringUtils.isEmpty(task.getScopeType())) {
			taskService.complete(task.getId());
		}else {
			cmmnTaskService.complete(task.getId());
		}
	}

	@Override
	public FormModel getTaskForm(String taskId) {
		FormModel formModel = taskService.getTaskFormModel(taskId);
		return formModel;
	}

}
