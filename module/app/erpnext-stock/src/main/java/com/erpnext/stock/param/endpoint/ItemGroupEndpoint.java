package com.erpnext.stock.param.endpoint;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.stock.param.domain.ItemGroup;
import com.erpnext.stock.param.service.ItemGroupService;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping(value = "/stock/param/itemGroup")
public class ItemGroupEndpoint extends BaseEndpoint{
	
	@Autowired
	private ItemGroupService itemGroupService;
	
	@GetMapping("/getAllItemGroup")
	@ResponseStatus(HttpStatus.OK)
	public List<ItemGroup> getAllItemGroup(){
		ItemGroup itemGroup = itemGroupService.readAllItemGroup("root");
		List<ItemGroup> list = new ArrayList<ItemGroup>();
		list.add(itemGroup);
		//HttpStatus status = HttpStatus.OK;
		return list;
	}
	
	@GetMapping("/getOneItemGroup/{id}")
	@ResponseStatus(HttpStatus.OK)
	public ItemGroup getOneItemGroup(@PathVariable("id") String id){
		ItemGroup itemGroup = itemGroupService.readOneItemGroup(id);
		return itemGroup;
	}
	
	@GetMapping("/getChildItemGroup/{id}")
	public List<ItemGroup> getChildItemGroup(@PathVariable("id") String id){
		return itemGroupService.readChildItemGroup(id);
	}
	
	@PostMapping("/create")
	public String createOneItemGroup(@Valid @RequestBody ItemGroup itemGroup){
		itemGroupService.addItemGroup(itemGroup);
		return CREATED;
	}
	
	@PutMapping("/update")
	public String updateItemGroup(@RequestBody ItemGroup itemGroup){
		itemGroupService.updateItemGroup(itemGroup);
		return UPDATED;
	}
	
	@GetMapping("/delete/{id}")
	public String deleteItemGroup(@PathVariable("id") String id){
		itemGroupService.deleteItemGroup(id);
		return DELETED;
	}
	
}
