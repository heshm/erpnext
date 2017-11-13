package com.erpnext.stock.param.endpoint;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.stock.param.dto.ItemDTO;
import com.erpnext.stock.param.service.ItemService;

@RestController
@RequestMapping(value = "/stock/param/item")
public class ItemEndpoint extends BaseEndpoint{
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/list/{itemGroupId}")
	public List<ItemDTO> list(@PathVariable("itemGroupId") String itemGroupId){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("itemGroupId", itemGroupId);
		return itemService.readItemList(map);
	}
}
