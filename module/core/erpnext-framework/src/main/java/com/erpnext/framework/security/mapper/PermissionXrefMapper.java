package com.erpnext.framework.security.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.erpnext.framework.security.domain.PermissionXref;

public interface PermissionXrefMapper {
    int deleteByPrimaryKey(@Param("permissionId") String permissionId, @Param("childPermissionId") String childPermissionId);

    int insert(PermissionXref record);

    List<PermissionXref> selectAll();
    
    List<PermissionXref> selectList(@Param("permissionId") String permissionId, @Param("childPermissionId") String childPermissionId);
}