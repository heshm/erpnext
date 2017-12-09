package com.erpnext.common.authority.dto;

import java.util.ArrayList;
import java.util.List;

public class RolePermissionDTO {
	
	private String roleId;
	
	private List<String> permissionSet;
	
	public RolePermissionDTO(){}
	
	public RolePermissionDTO(String roleId){
		this.roleId = roleId;
		this.permissionSet = new ArrayList<>();
	}
	
	public RolePermissionDTO(String roleId,List<String> permissionSet){
		this.roleId = roleId;
		this.permissionSet = permissionSet;
	}
	

	public String getRoleId() {
		return roleId;
	}

	public List<String> getPermissionSet() {
		return permissionSet;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public void setPermissionSet(List<String> permissionSet) {
		this.permissionSet = permissionSet;
	}

	@Override
	public String toString() {
		return "RolePermissionDTO [roleId=" + roleId + ", permissionSet=" + permissionSet + "]";
	}

}
