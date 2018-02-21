package com.erpnext.common.authority.service;

import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erpnext.common.authority.dto.AdminUserDTO;

public interface AdminUserService {
	
	AdminUserDTO getOneAdminUser(String id);
	
	Page<AdminUserDTO> getPageAdminUser(Pageable pageable,Map<String,Object> param);
	
	void createAdminUser(AdminUserDTO adminUser);
	
	void updateAdminUser(AdminUserDTO adminUser);

}
