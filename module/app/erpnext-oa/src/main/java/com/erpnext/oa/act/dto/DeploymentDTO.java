package com.erpnext.oa.act.dto;

import java.util.Date;
import java.util.Map;

import org.flowable.engine.common.api.repository.EngineResource;
import org.flowable.engine.repository.Deployment;

import com.fasterxml.jackson.annotation.JsonFormat;

public class DeploymentDTO implements Deployment{
	
	public DeploymentDTO() {}
	
	public DeploymentDTO(Deployment deployment) {
		this.id = deployment.getId();
		this.name = deployment.getName();
		this.deploymentTime = deployment.getDeploymentTime();
		this.category = deployment.getCategory();
		this.key = deployment.getKey();
		
	}
	
	private String id;
	
	private String name;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", locale = "zh", timezone = "GMT+8")
	private Date deploymentTime;
	
	private String category;
	
	private String key;
	
	private String tenantId;
	
	private String engineVersion;
	
	private boolean isNew;
	
	private Map<String, EngineResource> resources;

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

	public Date getDeploymentTime() {
		return deploymentTime;
	}

	public void setDeploymentTime(Date deploymentTime) {
		this.deploymentTime = deploymentTime;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getTenantId() {
		return tenantId;
	}

	public void setTenantId(String tenantId) {
		this.tenantId = tenantId;
	}

	public String getEngineVersion() {
		return engineVersion;
	}

	public void setEngineVersion(String engineVersion) {
		this.engineVersion = engineVersion;
	}

	public boolean isNew() {
		return isNew;
	}

	public void setNew(boolean isNew) {
		this.isNew = isNew;
	}

	public Map<String, EngineResource> getResources() {
		return resources;
	}

	public void setResources(Map<String, EngineResource> resources) {
		this.resources = resources;
	}

	

}
