package com.erpnext.common.authority.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.authority.service.PermissionService;
import com.erpnext.framework.domain.Permission;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

@RestController
@RequestMapping("/common/authority/perm")
public class PermissionEndpoint extends BaseEndpoint {
	
	@Autowired
	private PermissionService permissionService;
	
	@GetMapping("/list")
	public List<Permission> list(){
		return permissionService.getNestedPermission();
	}

}
