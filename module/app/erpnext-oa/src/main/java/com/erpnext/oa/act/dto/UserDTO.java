package com.erpnext.oa.act.dto;

import java.util.ArrayList;
import java.util.List;

import org.flowable.idm.api.User;

public class UserDTO {

	private String id;
	private String firstName;
	private String lastName;
	private String email;
	private String fullName;
	private List<GroupDTO> groups = new ArrayList<GroupDTO>();

	public UserDTO() {

	}

	public UserDTO(User user) {
		if (user != null) {
			setId(user.getId());
			setFirstName(user.getFirstName());
			setLastName(user.getLastName());
			setFullName((user.getFirstName() != null ? user.getFirstName() : "") + " "
					+ (user.getLastName() != null ? user.getLastName() : ""));
			setEmail(user.getEmail());
		}
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

	public List<GroupDTO> getGroups() {
		return groups;
	}

	public void setGroups(List<GroupDTO> groups) {
		this.groups = groups;
	}

}
