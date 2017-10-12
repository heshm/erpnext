package com.erpnext.framework.security.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class AdminUser implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4060846975160624986L;
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
     * 密码
     */
    private transient String password;
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
    /**
     * 用户的所有角色
     */
    private Set<AdminRole> allRoles = new HashSet<AdminRole>();
    /**
     * 用户的所有权限
     */
    private Set<Permission> allPermissions = new HashSet<Permission>();

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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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
	
	public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public Set<AdminRole> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(Set<AdminRole> allRoles) {
		this.allRoles = allRoles;
	}

	public Set<Permission> getAllPermissions() {
		return allPermissions;
	}

	public void setAllPermissions(Set<Permission> allPermissions) {
		this.allPermissions = allPermissions;
	}
}