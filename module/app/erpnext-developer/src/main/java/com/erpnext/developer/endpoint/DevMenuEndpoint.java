package com.erpnext.developer.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.param.dto.MenuDTO;
import com.erpnext.developer.service.DevMenuService;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

@RestController
@RequestMapping(value = "/developer/menu")
public class DevMenuEndpoint extends BaseEndpoint{
	
	@Autowired
	private DevMenuService devMenuService;
	
	@GetMapping("/getChildMenuList/{id}")
	public List<MenuDTO> getChildMenuList(@PathVariable("id")String id){
		return devMenuService.readApplicationMenu(id);
	}
	
}
