package com.erpnext.framework.security.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.erpnext.framework.security.domain.AdminUser;

public interface AdminUserMapper {
    int deleteByPrimaryKey(String userId);

    int insert(AdminUser record);

    AdminUser selectByPrimaryKey(String userId);

    List<AdminUser> selectAll();

    int updateByPrimaryKey(AdminUser record);
    
    AdminUser selectByLoginName(@Param("loginName")String loginName);
}