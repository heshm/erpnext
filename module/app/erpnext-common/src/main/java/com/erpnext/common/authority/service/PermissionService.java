package com.erpnext.common.authority.service;

import java.util.List;

import com.erpnext.common.authority.dto.PermissionDTO;
import com.erpnext.common.authority.dto.RolePermissionDTO;
import com.erpnext.common.authority.dto.UserPermissionDTO;
import com.erpnext.framework.domain.Permission;

public interface PermissionService {
	
	Permission getOnePermission(String permId);
	
	List<PermissionDTO> getAllPermission();
	
	List<Permission> getNestedPermission();
	
	void savePermission(PermissionDTO perm);
	
	RolePermissionDTO getPermissionByRole(String roleId);
	
	RolePermissionDTO updateRolePermission(RolePermissionDTO rolePerm);
	
 	UserPermissionDTO getSpecialPermissionByUser(String userId);
 	
 	void updateUserPermission(UserPermissionDTO userPerm);

}
