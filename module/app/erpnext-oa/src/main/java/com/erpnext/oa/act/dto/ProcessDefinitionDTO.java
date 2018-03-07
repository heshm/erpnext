package com.erpnext.oa.act.dto;

import org.activiti.engine.repository.ProcessDefinition;
import org.springframework.beans.BeanUtils;

public class ProcessDefinitionDTO implements ProcessDefinition {

	private String id;

	private String category;

	private String name;

	private String key;

	private String description;

	private int version;

	private String resourceName;

	private String deploymentId;

	private String diagramResourceName;

	private boolean startFormKey;

	private boolean graphicalNotation;

	private boolean suspended;

	private String tenantId;

	private String engineVersion;
	
	public ProcessDefinitionDTO() {}
	
	public ProcessDefinitionDTO(ProcessDefinition processDefinition) {
		BeanUtils.copyProperties(processDefinition, this);
	}

	@Override
	public String getId() {
		return id;
	}

	@Override
	public String getCategory() {
		return category;
	}

	@Override
	public String getName() {
		return name;
	}

	@Override
	public String getKey() {
		return key;
	}

	@Override
	public String getDescription() {
		return description;
	}

	@Override
	public int getVersion() {
		return version;
	}

	@Override
	public String getResourceName() {
		return resourceName;
	}

	@Override
	public String getDeploymentId() {
		return deploymentId;
	}

	@Override
	public String getDiagramResourceName() {
		return diagramResourceName;
	}

	@Override
	public boolean hasStartFormKey() {
		return startFormKey;
	}

	@Override
	public boolean hasGraphicalNotation() {
		return graphicalNotation;
	}

	@Override
	public boolean isSuspended() {
		return suspended;
	}

	@Override
	public String getTenantId() {
		return tenantId;
	}

	@Override
	public String getEngineVersion() {
		return engineVersion;
	}

	public boolean isStartFormKey() {
		return startFormKey;
	}

	public void setStartFormKey(boolean startFormKey) {
		this.startFormKey = startFormKey;
	}

	public boolean isGraphicalNotation() {
		return graphicalNotation;
	}

	public void setGraphicalNotation(boolean graphicalNotation) {
		this.graphicalNotation = graphicalNotation;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public void setResourceName(String resourceName) {
		this.resourceName = resourceName;
	}

	public void setDeploymentId(String deploymentId) {
		this.deploymentId = deploymentId;
	}

	public void setDiagramResourceName(String diagramResourceName) {
		this.diagramResourceName = diagramResourceName;
	}

	public void setSuspended(boolean suspended) {
		this.suspended = suspended;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public void setEngineVersion(String engineVersion) {
		this.engineVersion = engineVersion;
	}

}
