package com.erpnext.common.authority.dto;

import java.util.ArrayList;
import java.util.List;

public class UserPermissionDTO {
	
	private String userId;
	
	private List<String> permissionList;
	
	public UserPermissionDTO(){};
	
	public UserPermissionDTO(String userId){
		this.userId = userId;
		this.permissionList = new ArrayList<>();
	}

	public UserPermissionDTO(String userId, List<String> permissionList) {
		this.userId = userId;
		this.permissionList = permissionList;
	}

	public String getUserId() {
		return userId;
	}

	public List<String> getPermissionList() {
		return permissionList;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setPermissionList(List<String> permissionList) {
		this.permissionList = permissionList;
	}

	@Override
	public String toString() {
		return "UserPermissionDTO [userId=" + userId + ", permissionList=" + permissionList + "]";
	}

}
