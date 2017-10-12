package com.erpnext.framework.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.erpnext.framework.security.domain.Permission;

public interface PermissionMapper {
    int deleteByPrimaryKey(String permissionId);

    int insert(Permission record);

    Permission selectByPrimaryKey(String permissionId);

    List<Permission> selectAll();
    
    List<Permission> selectList(@Param("status") String status,@Param("isFriendly") boolean isFriendly);

    int updateByPrimaryKey(Permission record);
    
    Permission selectByPermissionId(String permissionId);
    
}