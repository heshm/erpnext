package com.erpnext.framework.security.service;

import java.util.List;

import com.erpnext.framework.security.domain.AdminRole;
import com.erpnext.framework.security.domain.AdminUser;
import com.erpnext.framework.security.domain.Permission;

public interface SiteSecurityService {
	
	Permission readPermissionById(String id);
	AdminRole readAdminRoleById(String roleId);
	AdminUser readAdminUserById(String userId);
	AdminUser readAdminUserByLoginName(String loginName);
	
	List<AdminUser> getAllUser();

}
