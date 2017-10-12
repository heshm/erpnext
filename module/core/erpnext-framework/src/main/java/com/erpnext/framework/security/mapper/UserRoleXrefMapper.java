package com.erpnext.framework.security.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Param;

import com.erpnext.framework.security.domain.UserRoleXref;

public interface UserRoleXrefMapper {
    int deleteByPrimaryKey(@Param("userId") String userId, @Param("roleId") String roleId);
    
    int deleteByUser(@Param("userId") String userId);

    int insert(UserRoleXref record);
    
    int insertList(List<UserRoleXref> list);

    List<UserRoleXref> selectAll();
    
    List<UserRoleXref> selectList(@Param("userId") String userId, @Param("roleId") String roleId);
}