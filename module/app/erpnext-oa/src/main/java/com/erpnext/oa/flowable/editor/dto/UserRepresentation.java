package com.erpnext.oa.flowable.editor.dto;

import java.util.ArrayList;
import java.util.List;

import com.erpnext.common.authority.dto.AdminUserDTO;
import com.erpnext.framework.domain.AdminUser;

public class UserRepresentation {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String fullName;
	private List<GroupRepresentation> groups = new ArrayList<>();
	private List<String> privileges = new ArrayList<>();
	
	public UserRepresentation() {
		
	}
	
	public UserRepresentation(AdminUserDTO user) {
		this.id = user.getUserId();
		this.fullName = user.getUserName();
		this.firstName = user.getUserName();
		this.email = user.getEmail();
	}
	
	public UserRepresentation(AdminUser user) {
		this.id = user.getUserId();
		this.fullName = user.getUserName();
		this.firstName = user.getUserName();
		this.email = user.getEmail();
	}
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public List<GroupRepresentation> getGroups() {
		return groups;
	}
	public void setGroups(List<GroupRepresentation> groups) {
		this.groups = groups;
	}
	public List<String> getPrivileges() {
		return privileges;
	}
	public void setPrivileges(List<String> privileges) {
		this.privileges = privileges;
	}

}
