package com.erpnext.common.authority.dto;

import java.io.Serializable;

public class PermissionDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1198064724734598892L;
	
	private String permissionId;

    private String permissionDesc;

    private String permissionName;

    private String status;

    private String permissionType;
    
    private Boolean isFriendly;
    
    private String parentPermissionId;
    
    public PermissionDTO(){
		
	}

	public PermissionDTO(String permissionId, String permissionDesc, String permissionName, String status,
			String permissionType, Boolean isFriendly, String parentPermissionId) {
		super();
		this.permissionId = permissionId;
		this.permissionDesc = permissionDesc;
		this.permissionName = permissionName;
		this.status = status;
		this.permissionType = permissionType;
		this.isFriendly = isFriendly;
		this.parentPermissionId = parentPermissionId;
	}


	public String getPermissionId() {
		return permissionId;
	}

	public String getPermissionDesc() {
		return permissionDesc;
	}

	public String getPermissionName() {
		return permissionName;
	}

	public String getStatus() {
		return status;
	}

	public String getPermissionType() {
		return permissionType;
	}

	public Boolean getIsFriendly() {
		return isFriendly;
	}

	public String getParentPermissionId() {
		return parentPermissionId;
	}

	public void setPermissionId(String permissionId) {
		this.permissionId = permissionId;
	}

	public void setPermissionDesc(String permissionDesc) {
		this.permissionDesc = permissionDesc;
	}

	public void setPermissionName(String permissionName) {
		this.permissionName = permissionName;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public void setPermissionType(String permissionType) {
		this.permissionType = permissionType;
	}

	public void setIsFriendly(Boolean isFriendly) {
		this.isFriendly = isFriendly;
	}

	public void setParentPermissionId(String parentPermissionId) {
		this.parentPermissionId = parentPermissionId;
	}

}
