package com.erpnext.framework.domain;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

public class Permission implements Serializable{
	
    /**
	 * 
	 */
	private static final long serialVersionUID = 5580048067851215470L;

	private String permissionId;

    private String permissionDesc;

    private String permissionName;

    private String status;

    private String permissionType;
    
    private Boolean isFriendly;
    
    @JsonInclude(Include.NON_EMPTY)
    private List<Permission> children;

    public String getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(String permissionId) {
        this.permissionId = permissionId;
    }

    public String getPermissionDesc() {
        return permissionDesc;
    }

    public void setPermissionDesc(String permissionDesc) {
        this.permissionDesc = permissionDesc;
    }

    public String getPermissionName() {
        return permissionName;
    }

    public void setPermissionName(String permissionName) {
        this.permissionName = permissionName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPermissionType() {
        return permissionType;
    }

    public void setPermissionType(String permissionType) {
        this.permissionType = permissionType;
    }
    
    public Boolean getIsFriendly() {
        return isFriendly;
    }

    public void setIsFriendly(Boolean isFriendly) {
        this.isFriendly = isFriendly;
    }

	public List<Permission> getChildren() {
		return children;
	}

	public void setChildren(List<Permission> children) {
		this.children = children;
	}
}