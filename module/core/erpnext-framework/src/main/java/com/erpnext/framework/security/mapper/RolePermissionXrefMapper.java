package com.erpnext.framework.security.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.erpnext.framework.security.domain.RolePermissionXref;

public interface RolePermissionXrefMapper {
    int deleteByPrimaryKey(@Param("roleId") String roleId, @Param("permissionId") String permissionId);

    int deleteByRoleId(@Param("roleId") String roleId);
    
    int insert(RolePermissionXref parmList);
    
    int insertList(List<RolePermissionXref> record);

    List<RolePermissionXref> selectAll();
    
    List<RolePermissionXref> selectList(@Param("roleId") String roleId, @Param("permissionId") String permissionId);
}