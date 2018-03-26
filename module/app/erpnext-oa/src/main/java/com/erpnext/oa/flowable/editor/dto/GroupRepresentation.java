package com.erpnext.oa.flowable.editor.dto;

import com.erpnext.framework.domain.AdminRole;

public class GroupRepresentation {
	
	private String id;
	private String name;
	private String type;
	
	public GroupRepresentation() {}
	
	public GroupRepresentation(AdminRole role) {
		this.id = role.getRoleId();
		this.name = role.getRoleName();
		this.type = role.getRoleType();
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
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
