package com.erpnext.common.authority.manager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.framework.domain.AdminUser;
import com.erpnext.framework.mapper.AdminUserMapper;

@Component
@Transactional(readOnly=true) 
public class UserManagerImpl implements UserManager {
	
	@Autowired
	private AdminUserMapper adminUserMapper;

	@Override
	public AdminUser getOneUser(String userId) {
		// TODO Auto-generated method stub
		return adminUserMapper.selectByPrimaryKey(userId);
	}

}
