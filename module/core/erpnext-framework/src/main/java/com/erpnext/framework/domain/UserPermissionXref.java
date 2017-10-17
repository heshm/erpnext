package com.erpnext.framework.domain;

public class UserPermissionXref {
    private String userId;

    private String permissionId;

    public UserPermissionXref(String userId, String permissionId) {
		super();
		this.userId = userId;
		this.permissionId = permissionId;
	}

	public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}