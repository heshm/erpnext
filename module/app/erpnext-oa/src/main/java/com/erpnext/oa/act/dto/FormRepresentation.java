package com.erpnext.oa.act.dto;

import java.util.Date;

import com.erpnext.oa.act.domain.AbstractModel;
//import com.erpnext.oa.act.domain.SimpleFormModel;
import com.erpnext.oa.act.domain.SimpleFormModel;

public class FormRepresentation {

	private String id;
	private String name;
	private String key;
	private String description;
	private Integer version;
	private String lastUpdatedBy;
	private Date lastUpdated;
	private SimpleFormModel formDefinition;
	
	public FormRepresentation() {}
	
	public FormRepresentation(AbstractModel model) {
		this.id = model.getId();
	    this.name = model.getName();
	    this.key = model.getModelKey();
	    this.description = model.getDescription();
	    this.version = model.getVersion();
	    this.lastUpdated = model.getLastUpdated();
	    this.lastUpdatedBy = model.getLastUpdatedBy();
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
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getVersion() {
		return version;
	}
	public void setVersion(Integer version) {
		this.version = version;
	}
	public String getLastUpdatedBy() {
		return lastUpdatedBy;
	}
	public void setLastUpdatedBy(String lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}
	public Date getLastUpdated() {
		return lastUpdated;
	}
	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

	public SimpleFormModel getFormDefinition() {
		return formDefinition;
	}

	public void setFormDefinition(SimpleFormModel formDefinition) {
		this.formDefinition = formDefinition;
	}
	
	

}
