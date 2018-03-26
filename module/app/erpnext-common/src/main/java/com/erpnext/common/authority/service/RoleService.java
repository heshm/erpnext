package com.erpnext.common.authority.service;

import java.util.List;

import com.erpnext.common.authority.dto.UserRoleDTO;
import com.erpnext.framework.domain.AdminRole;

public interface RoleService {
	
	List<AdminRole> getAllRole();
	
	void saveRole(AdminRole role);
	/**
	 * 根据角色ID删除相应的角色信息
	 * @param roleId
	 */
	void deleteRole(String roleId);
	/**
	 * 根据用户ID取得用户的角色
	 * @param userId
	 * @return UserRoleDTO
	 */
	UserRoleDTO getUserRole(String userId);
	/**
	 * 更新用户的角色
	 * @param userRole
	 * @return
	 */
	UserRoleDTO updateUserRole(UserRoleDTO userRole);
	
	List<AdminRole> getRoleByName(String nameFilter);

}
