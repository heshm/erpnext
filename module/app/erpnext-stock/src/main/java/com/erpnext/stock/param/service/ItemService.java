package com.erpnext.stock.param.service;

import java.util.List;
import java.util.Map;

import com.erpnext.stock.param.dto.ItemDTO;

public interface ItemService {
	
	List<ItemDTO> readItemList(Map<String,Object> map);
	
	ItemDTO readItemById(String id);

}
