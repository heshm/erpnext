package com.erpnext.oa.act.dto;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.flowable.engine.form.FormProperty;
import org.flowable.engine.form.TaskFormData;
import org.flowable.form.model.FormField;
import org.flowable.form.model.FormModel;
import org.flowable.form.model.FormOutcome;

public class TaskFormDTO {

	private String id;
	
	private String key;
	
	private String name;
	
	private List<FormField> fields;
	
	private List<FormOutcome> outcomes;
	
	private List<FormProperty> formProperties;
	
	private boolean fieldsReadOnly;
	
	public TaskFormDTO() {
		
	}
	
	public TaskFormDTO(FormModel formModel) {
		this.id = formModel.getId();
		this.key = formModel.getKey();
		this.name = formModel.getName();
		this.fields = formModel.getFields();
		this.outcomes = formModel.getOutcomes();
		this.fieldsReadOnly = true;
	}
	
	public TaskFormDTO(TaskFormData formData) {
		if(StringUtils.isEmpty(this.id)) {
			this.id = formData.getDeploymentId();
		}
		if(StringUtils.isEmpty(this.key)) {
			this.key = formData.getFormKey();
		}
		this.formProperties = formData.getFormProperties();
		this.fieldsReadOnly = false;
	}
	
	public TaskFormDTO(FormModel formModel,TaskFormData formData) {
		this(formModel);
		this.formProperties = formData.getFormProperties();
		this.fieldsReadOnly = false;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<FormField> getFields() {
		return fields;
	}

	public void setFields(List<FormField> fields) {
		this.fields = fields;
	}

	public List<FormOutcome> getOutcomes() {
		return outcomes;
	}

	public void setOutcomes(List<FormOutcome> outcomes) {
		this.outcomes = outcomes;
	}

	public List<FormProperty> getFormProperties() {
		return formProperties;
	}

	public void setFormProperties(List<FormProperty> formProperties) {
		this.formProperties = formProperties;
	}

	public boolean isFieldsReadOnly() {
		return fieldsReadOnly;
	}

	public void setFieldsReadOnly(boolean fieldsReadOnly) {
		this.fieldsReadOnly = fieldsReadOnly;
	}
	
	
}
