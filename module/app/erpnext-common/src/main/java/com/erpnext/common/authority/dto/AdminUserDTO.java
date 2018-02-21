package com.erpnext.common.authority.dto;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class AdminUserDTO {
	
	/**
	 * 用户ID
	 */
    private String userId;
    /**
     * 用户是否有效
     */
    private Byte activeStatusFlag;
    /**
     * 登录名
     */
    private String loginName;
    /**
     * 界面显示名称
     */
    private String userName;
    /**
     * 电话号码 
     */
    private String phoneNumber;
    /**
     * 电子邮箱
     */
    private String email;
    /**
     * 显示角色名
     */
    private String roleName;
    /**
     * 创建日期
     */
    private Date createDate;
    
    private String[] departmentIds;
    
    private String departmentNames;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Byte getActiveStatusFlag() {
		return activeStatusFlag;
	}

	public void setActiveStatusFlag(Byte activeStatusFlag) {
		this.activeStatusFlag = activeStatusFlag;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public String[] getDepartmentIds() {
		return departmentIds;
	}

	public void setDepartmentIds(String[] departmentIds) {
		this.departmentIds = departmentIds;
	}

	public String getDepartmentNames() {
		return departmentNames;
	}

	public void setDepartmentNames(String departmentNames) {
		this.departmentNames = departmentNames;
	}
    
	

}
