package com.erpnext.common.param.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.param.domain.Menu;
import com.erpnext.common.param.service.MenuService;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

@RestController
@RequestMapping("/common/param/menu")
public class MenuEndpoint extends BaseEndpoint {
	
	@Autowired
	private MenuService menuService;
	
	@GetMapping("/tree/{menuId}")
	public List<Menu> tree(@PathVariable("menuId") String menuId){
		return menuService.readAllMenuById(menuId).getChildren();
	}
	
	@GetMapping("/list_app")
	public List<Menu> getAuthApp(){
		User user = (User)SecurityContextHolder.getContext().getAuthentication().
				getPrincipal();	
		return menuService.readAuthApp(user);
	}
	
	@GetMapping("/getOneMenu/{id}")
	public Menu getOneMenu(@PathVariable("id")String id) {
		return menuService.readByPrimaryKey(id);
	}

}
