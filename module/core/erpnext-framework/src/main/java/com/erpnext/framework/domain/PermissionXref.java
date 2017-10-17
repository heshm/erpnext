package com.erpnext.framework.domain;

public class PermissionXref {
    private String permissionId;

    private String childPermissionId;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getChildPermissionId() {
        return childPermissionId;
    }

    public void setChildPermissionId(String childPermissionId) {
        this.childPermissionId = childPermissionId;
    }
}