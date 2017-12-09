package com.erpnext.common.authority.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.erpnext.common.authority.dto.PermissionDTO;
import com.erpnext.common.authority.dto.RolePermissionDTO;
import com.erpnext.common.authority.dto.UserPermissionDTO;
import com.erpnext.framework.domain.Permission;
import com.erpnext.framework.domain.PermissionXref;
import com.erpnext.framework.domain.RolePermissionXref;
import com.erpnext.framework.domain.UserPermissionXref;
import com.erpnext.framework.mapper.PermissionMapper;
import com.erpnext.framework.mapper.PermissionXrefMapper;
import com.erpnext.framework.mapper.RolePermissionXrefMapper;
import com.erpnext.framework.mapper.UserPermissionXrefMapper;

@Service
@Transactional(readOnly=true) 
public class PermissionServiceImpl implements PermissionService {
	
	@Autowired
	private PermissionMapper permissionMapper;
	
	@Autowired
	private PermissionXrefMapper permissionXrefMapper;
	
	@Autowired
	private RolePermissionXrefMapper rolePermissionXrefMapper;
	
	@Autowired
	private UserPermissionXrefMapper userPermissionXrefMapper;
	
	@Override
	public Permission getOnePermission(String permId) {
		return permissionMapper.selectByPrimaryKey(permId);
	}

	@Override
	public List<PermissionDTO> getAllPermission() {
		List<PermissionDTO> resultList = new ArrayList<>(200);
		List<Permission> parent = permissionMapper.selectList("1", true);
		for(Permission perm : parent){
			resultList.add(new PermissionDTO(
					perm.getPermissionId(),
					perm.getPermissionDesc(),
					perm.getPermissionName(),
					perm.getStatus(),
					perm.getPermissionType(),
					perm.getIsFriendly(),
					null
					));
			List<Permission> child = permissionMapper.selectByPermissionId(perm.getPermissionId()).getChildPermission();
			if(child != null){
				for(Permission childPerm : child){
					resultList.add(new PermissionDTO(
							childPerm.getPermissionId(),
							childPerm.getPermissionDesc(),
							childPerm.getPermissionName(),
							childPerm.getStatus(),
							childPerm.getPermissionType(),
							childPerm.getIsFriendly(),
							perm.getPermissionId()
							));
				}
			}
		}
		return resultList;
	}

	@Override
	@Transactional
	public void savePermission(PermissionDTO perm) {
		Permission dbPerm = permissionMapper.selectByPrimaryKey(perm.getPermissionId());
		if(dbPerm != null){
			throw new IllegalArgumentException("权限已经存在!");
		}
		Permission permission = new Permission();
		permission.setPermissionId(perm.getPermissionId());
		permission.setPermissionType(perm.getPermissionType());
		permission.setPermissionName(perm.getPermissionName());
		permission.setPermissionDesc(perm.getPermissionDesc());
		permission.setStatus("1");
		permission.setIsFriendly(perm.getIsFriendly());
		if(perm.getIsFriendly()){
			permissionMapper.insert(permission);
		}else{
			PermissionXref permissionXref = new PermissionXref();
			permissionXref.setPermissionId(perm.getParentPermissionId());
			permissionXref.setChildPermissionId(perm.getPermissionId());
			permissionMapper.insert(permission);
			permissionXrefMapper.insert(permissionXref);
		}

	}

	@Override
	public List<Permission> getNestedPermission() {
		List<Permission> parent = permissionMapper.selectList("1", true);
		List<Permission> result = new ArrayList<>(100);
		for(Permission perm : parent){
			result.add(permissionMapper.selectByPermissionId(perm.getPermissionId()));
		}
		return result;
	}

	@Override
	public RolePermissionDTO getPermissionByRole(String roleId) {
		RolePermissionDTO result = new RolePermissionDTO(roleId);
		List<String> permList = new ArrayList<>(200);
		if(StringUtils.isEmpty(roleId)){
			return result;
		}
		List<RolePermissionXref> rolePermList = rolePermissionXrefMapper.selectList(roleId, null);
		for(RolePermissionXref rolePerm : rolePermList){
			Permission perm = permissionMapper.selectByPermissionId(rolePerm.getPermissionId());
			if(perm.getIsFriendly()){
				for(Permission childPerm : perm.getChildPermission()){
					permList.add(childPerm.getPermissionId());
				}
			}else{
				permList.add(perm.getPermissionId());
			}
		}
		result.setPermissionSet(permList);
		return result;
	}

	@Override
	@Transactional
	public RolePermissionDTO updateRolePermission(RolePermissionDTO rolePerm) {
		String roleId = rolePerm.getRoleId();
		if(StringUtils.isEmpty(roleId)){
			throw new IllegalArgumentException("角色ID不能为空!");
		}
		List<RolePermissionXref> parmList = new ArrayList<>(200);
		rolePermissionXrefMapper.deleteByRoleId(roleId);
		for(String perm : rolePerm.getPermissionSet()){
			parmList.add(new RolePermissionXref(roleId,perm));
		}
		if(!parmList.isEmpty()){
			rolePermissionXrefMapper.insertList(parmList);
		}
		return rolePerm;
	}

	@Override
	public UserPermissionDTO getSpecialPermissionByUser(String userId) {
		UserPermissionDTO result = new UserPermissionDTO(userId);
		if(StringUtils.isEmpty(userId)){
			return result;
		}
		List<String> permList = new ArrayList<>(200);
		List<UserPermissionXref> userPermList = userPermissionXrefMapper.selectList(userId, null);
		for(UserPermissionXref userPerm : userPermList){
			Permission perm = permissionMapper.selectByPermissionId(userPerm.getPermissionId());
			if(perm.getIsFriendly()){
				for(Permission childPerm : perm.getChildPermission()){
					permList.add(childPerm.getPermissionId());
				}
			}else{
				permList.add(perm.getPermissionId());
			}
		}
		result.setPermissionList(permList);
		return result;
	}

	@Override
	@Transactional
	public void updateUserPermission(UserPermissionDTO userPerm) {
		String userId = userPerm.getUserId();
		if(StringUtils.isEmpty(userId)){
			throw new IllegalArgumentException("用户ID不能为空!");
		}
		List<UserPermissionXref> parmList = new ArrayList<>(200);
		userPermissionXrefMapper.deleteByUserId(userId);
		for(String perm : userPerm.getPermissionList()){
			parmList.add(new UserPermissionXref(userId,perm));
		}
		if(!parmList.isEmpty()){
			userPermissionXrefMapper.insertList(parmList);
		}
	}
	
}
