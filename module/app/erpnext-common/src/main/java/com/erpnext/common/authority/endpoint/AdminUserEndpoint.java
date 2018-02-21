package com.erpnext.common.authority.endpoint;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.authority.dto.AdminUserDTO;
import com.erpnext.common.authority.service.AdminUserService;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

@RestController
@RequestMapping("/common/authority/user")
public class AdminUserEndpoint extends BaseEndpoint {
	@Autowired
	private AdminUserService adminUserService;
	
	@GetMapping("/list")
	public Page<AdminUserDTO> list(@RequestParam Map<String, Object> params,
			@PageableDefault(size=10, page=0)Pageable pageable){
		return adminUserService.getPageAdminUser(pageable, params);
	}
	
	@PostMapping("/create")
	public String createAdminUser(@RequestBody AdminUserDTO adminUser) {
		adminUserService.createAdminUser(adminUser);
		return CREATED;
	}
	
	@PutMapping("/update")
	public String updateAdminUser(@RequestBody AdminUserDTO adminUser) {
		adminUserService.updateAdminUser(adminUser);
		return UPDATED;
	}

}
