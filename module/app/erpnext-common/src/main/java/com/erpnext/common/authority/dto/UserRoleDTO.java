package com.erpnext.common.authority.dto;

import java.util.ArrayList;
import java.util.List;

public class UserRoleDTO {
	
	private String userId;
	
	private List<String> roleList;
	
	public UserRoleDTO(){}

	public UserRoleDTO(String userId) {
		this.userId = userId;
		roleList = new ArrayList<>();
	}

	public UserRoleDTO(String userId, List<String> roleList) {
		super();
		this.userId = userId;
		this.roleList = roleList;
	}

	public String getUserId() {
		return userId;
	}

	public List<String> getRoleList() {
		return roleList;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setRoleList(List<String> roleList) {
		this.roleList = roleList;
	}

	@Override
	public String toString() {
		return "UserRoleDTO [userId=" + userId + ", roleList=" + roleList + "]";
	}

}
