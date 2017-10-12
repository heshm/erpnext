package com.erpnext.framework.security.domain;

public class RolePermissionXref {
    private String roleId;

    private String permissionId;

    public RolePermissionXref(String roleId, String permissionId) {
		super();
		this.roleId = roleId;
		this.permissionId = permissionId;
	}

	public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }
}