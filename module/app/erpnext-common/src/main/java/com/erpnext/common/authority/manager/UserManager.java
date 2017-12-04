package com.erpnext.common.authority.manager;

import com.erpnext.framework.domain.AdminUser;

public interface UserManager {
	
	AdminUser getOneUser(String userId);

}
