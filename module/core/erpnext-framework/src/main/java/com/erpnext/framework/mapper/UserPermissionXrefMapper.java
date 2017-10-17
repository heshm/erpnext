package com.erpnext.framework.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.erpnext.framework.domain.UserPermissionXref;

public interface UserPermissionXrefMapper {
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("permissionId") String permissionId);

    int deleteByUserId(@Param("userId") String userId);
    
    int insert(UserPermissionXref record);
    
    int insertList(List<UserPermissionXref> record);

    List<UserPermissionXref> selectAll();
    
    List<UserPermissionXref> selectList(@Param("userId") String userId, @Param("permissionId") String permissionId);
}