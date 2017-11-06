package com.erpnext.common.param.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.param.domain.Menu;
import com.erpnext.common.param.dto.MenuDTO;
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
	
	@GetMapping("/getOneMenuWithParent/{id}")
	public MenuDTO getOneMenuWithParent(@PathVariable("id")String id){
		return menuService.readOneMenuWithParent(id);
	}
	
	@PutMapping("/updateOneMenu")
	public String updateOneMenu(@RequestBody Menu menu){
		menuService.savaMenu(menu);
		return UPDATED;
	}
	
	@PostMapping("/createOneMenu")
	public String createOneMenu(@RequestBody Menu menu){
		System.out.println(menu);
		return CREATED;
	}

}
