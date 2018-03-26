package com.erpnext.framework.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.erpnext.framework.domain.AdminRole;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(AdminRole record);

    AdminRole selectByPrimaryKey(String roleId);

    List<AdminRole> selectAll();

    int updateByPrimaryKey(AdminRole record);
    
    List<AdminRole> selectByName(@Param("roleName") String roleName);
    
}