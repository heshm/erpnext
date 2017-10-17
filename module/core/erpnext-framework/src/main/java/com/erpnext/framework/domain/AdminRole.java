package com.erpnext.framework.domain;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class AdminRole implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7195264505656047386L;

	/**
	 * 角色ID
	 */
    private String roleId;

    private String roleName;

    private String roleDesc;
    /**
     * 角色类型,存储应用ID
     */
    private String roleType;
    
    private Set<Permission> allPermissions = new HashSet<Permission>();

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getRoleDesc() {
        return roleDesc;
    }

    public void setRoleDesc(String roleDesc) {
        this.roleDesc = roleDesc;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }

    public Set<Permission> getAllPermissions() {
		return allPermissions;
	}

	public void setAllPermissions(Set<Permission> allPermissions) {
		this.allPermissions = allPermissions;
	}
}