package com.erpnext.framework.mapper;

import java.util.List;

import com.erpnext.framework.domain.AdminRole;

public interface AdminRoleMapper {
    int deleteByPrimaryKey(String roleId);

    int insert(AdminRole record);

    AdminRole selectByPrimaryKey(String roleId);

    List<AdminRole> selectAll();

    int updateByPrimaryKey(AdminRole record);
    
}