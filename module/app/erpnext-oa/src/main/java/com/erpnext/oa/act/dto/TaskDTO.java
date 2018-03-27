package com.erpnext.oa.act.dto;

import java.util.Comparator;
import java.util.Date;

import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.task.api.TaskInfo;
import org.flowable.task.api.history.HistoricTaskInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TaskDTO {
	
	private String id;
	
	private String name;
	
	private String description;
	
	private int priority; 
	
	private String owner;
	
	private String assignee;
	
	private String processInstanceId;
	
	private String executionId;
	
	private String processDefinitionId;

	private String processDefinitionName;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date createTime;
	
	private String taskDefinitionKey;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date dueDate;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date endDate;
	
	private Long duration;
	
	private String category;
	
	private String parentTaskId;
	
	private String tenantId;
	
	private String formKey;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date claimTime;
	
	private boolean taskHasForm = false;
	
	public TaskDTO() {
		
	}
	
	public TaskDTO(TaskInfo taskInfo) {
		BeanUtils.copyProperties(taskInfo, this);
		if(taskInfo instanceof HistoricTaskInstance) {
			this.endDate = ((HistoricTaskInstance) taskInfo).getEndTime();
			this.duration = ((HistoricTaskInstance) taskInfo).getDurationInMillis();
		}
		if(!StringUtils.isEmpty(taskInfo.getFormKey())) {
			this.taskHasForm = true;
		}
	}
	
	public TaskDTO(TaskInfo task,ProcessDefinition processDefinition) {
		this(task);
		if(processDefinition != null) {
			this.processDefinitionName = processDefinition.getName();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getPriority() {
		return priority;
	}

	public void setPriority(int priority) {
		this.priority = priority;
	}

	public String getOwner() {
		return owner;
	}

	public void setOwner(String owner) {
		this.owner = owner;
	}

	public String getAssignee() {
		return assignee;
	}

	public void setAssignee(String assignee) {
		this.assignee = assignee;
	}

	public String getProcessInstanceId() {
		return processInstanceId;
	}

	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}

	public String getExecutionId() {
		return executionId;
	}

	public void setExecutionId(String executionId) {
		this.executionId = executionId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionName() {
		return processDefinitionName;
	}

	public void setProcessDefinitionName(String processDefinitionName) {
		this.processDefinitionName = processDefinitionName;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getTaskDefinitionKey() {
		return taskDefinitionKey;
	}

	public void setTaskDefinitionKey(String taskDefinitionKey) {
		this.taskDefinitionKey = taskDefinitionKey;
	}

	public Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public Long getDuration() {
		return duration;
	}

	public void setDuration(Long duration) {
		this.duration = duration;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getParentTaskId() {
		return parentTaskId;
	}

	public void setParentTaskId(String parentTaskId) {
		this.parentTaskId = parentTaskId;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public Date getClaimTime() {
		return claimTime;
	}

	public void setClaimTime(Date claimTime) {
		this.claimTime = claimTime;
	}

	public boolean isTaskHasForm() {
		return taskHasForm;
	}

	public void setTaskHasForm(boolean taskHasForm) {
		this.taskHasForm = taskHasForm;
	}

	public static final class IdOrder implements Comparator<TaskDTO> {

		@Override
		public int compare(TaskDTO arg0, TaskDTO arg1) {
			return arg0.id.compareTo(arg1.id);
		}
		
	}

}
