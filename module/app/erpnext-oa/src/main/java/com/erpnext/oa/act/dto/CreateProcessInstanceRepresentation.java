package com.erpnext.oa.act.dto;

public class CreateProcessInstanceRepresentation extends CompleteFormRepresentation {
	private String processDefinitionId;
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setProcessDefinitionId(String processDefinitionId) {
		this.processDefinitionId = processDefinitionId;
	}

	public String getProcessDefinitionId() {
		return processDefinitionId;
	}

}
