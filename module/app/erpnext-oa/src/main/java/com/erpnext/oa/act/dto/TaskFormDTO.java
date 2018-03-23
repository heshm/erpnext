package com.erpnext.oa.act.dto;

import java.util.List;

import org.activiti.engine.form.FormProperty;

public class TaskFormDTO {
	
	private String formKey;
	
	private String deploymentId;
	
	private List<FormProperty> formProperties;

	public String getFormKey() {
		return formKey;
	}

	public void setFormKey(String formKey) {
		this.formKey = formKey;
	}

	public String getDeploymentId() {
		return deploymentId;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public List<FormProperty> getFormProperties() {
		return formProperties;
	}

	public void setFormProperties(List<FormProperty> formProperties) {
		this.formProperties = formProperties;
	}

	
}
