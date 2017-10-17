package com.erpnext.framework.domain;

public class UserRoleXref {
    private String userId;

    private String roleId;

    public UserRoleXref(String userId, String roleId) {
		super();
		this.userId = userId;
		this.roleId = roleId;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

	@Override
	public String toString() {
		return "UserRoleXref [userId=" + userId + ", roleId=" + roleId + "]";
	}
}