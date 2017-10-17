package com.erpnext.framework.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.framework.domain.AdminRole;
import com.erpnext.framework.domain.AdminUser;
import com.erpnext.framework.domain.Permission;
import com.erpnext.framework.domain.RolePermissionXref;
import com.erpnext.framework.domain.UserPermissionXref;
import com.erpnext.framework.domain.UserRoleXref;
import com.erpnext.framework.mapper.AdminRoleMapper;
import com.erpnext.framework.mapper.AdminUserMapper;
import com.erpnext.framework.mapper.PermissionMapper;
import com.erpnext.framework.mapper.RolePermissionXrefMapper;
import com.erpnext.framework.mapper.UserPermissionXrefMapper;
import com.erpnext.framework.mapper.UserRoleXrefMapper;

@Service
@Transactional(readOnly=true)
public class SieSecurityServiceImpl implements SiteSecurityService{

	@Autowired
	private PermissionMapper permissionMapper;
	
	@Autowired
	private AdminRoleMapper adminRoleMapper;

	@Autowired
	private RolePermissionXrefMapper rolePermissionXrefMapper;
	
	@Autowired
	private AdminUserMapper adminUserMapper;
	
	@Autowired
	private UserRoleXrefMapper userRoleXrefMapper;

	@Autowired
	private UserPermissionXrefMapper userPermissionXrefMapper;
	
	@Override
	public Permission readPermissionById(String id) {
		return permissionMapper.selectByPermissionId(id);
	}

	@Override
	public AdminRole readAdminRoleById(String roleId) {
		AdminRole role = adminRoleMapper.selectByPrimaryKey(roleId);
		if (null != role) {
			List<RolePermissionXref> rolePermissionList = rolePermissionXrefMapper.selectList(roleId, null);
			if (null != rolePermissionList) {
				for (RolePermissionXref item : rolePermissionList) {
					Permission perm = readPermissionById(item.getPermissionId());
					if (null != perm){
						role.getAllPermissions().add(perm);
					}
				}
			}
		}
		return role;
	}

	@Override
	public AdminUser readAdminUserById(String userId) {
		AdminUser adminUser = adminUserMapper.selectByPrimaryKey(userId);
		readOtherUserInfo(adminUser);
		return adminUser;
	}

	@Override
	public AdminUser readAdminUserByLoginName(String loginName) {
		AdminUser adminUser = adminUserMapper.selectByLoginName(loginName);
		readOtherUserInfo(adminUser);
		return adminUser;
	}
	
	@Override
	//@PreAuthorize("#oauth2.clientHasRole('ROLE_ADMIN')")
	
	public List<AdminUser> getAllUser() {
		// TODO Auto-generated method stub
		return adminUserMapper.selectAll();
	}

	
	private void readOtherUserInfo(AdminUser adminUser){
		if (null != adminUser) {
			String userId = adminUser.getUserId();
			List<UserRoleXref> userRoleList = userRoleXrefMapper.selectList(userId, null);
			if (null != userRoleList) {
				for (UserRoleXref item : userRoleList) {
					AdminRole role = readAdminRoleById(item.getRoleId());
					if (null != role){
						adminUser.getAllRoles().add(role);
					}
				}
			}
			List<UserPermissionXref> UserPermissionList = userPermissionXrefMapper.selectList(userId, null);
			if (null != UserPermissionList) {
				for (UserPermissionXref item : UserPermissionList) {
					Permission perm = this.readPermissionById(item.getPermissionId());
					if(null != perm){
					    adminUser.getAllPermissions().add(perm);
					}
				}
			}
		}
	}

}
