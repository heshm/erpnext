package com.erpnext.framework.service;

import java.util.List;

import org.springframework.security.access.prepost.PreAuthorize;

import com.erpnext.framework.domain.AdminRole;
import com.erpnext.framework.domain.AdminUser;
import com.erpnext.framework.domain.Permission;

public interface SiteSecurityService {
	
	Permission readPermissionById(String id);
	AdminRole readAdminRoleById(String roleId);
	AdminUser readAdminUserById(String userId);
	AdminUser readAdminUserByLoginName(String loginName);
	
	
	@PreAuthorize("hasAuthority('PERM_SYS_IMPORT')")
	//@PreAuthorize("#oauth2.clientHasRole('ROLE_ADMIN')")
	List<AdminUser> getAllUser();

}
