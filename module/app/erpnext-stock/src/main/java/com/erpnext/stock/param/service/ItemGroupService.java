package com.erpnext.stock.param.service;

import java.util.List;

import com.erpnext.stock.param.domain.ItemGroup;

public interface ItemGroupService {
	
	ItemGroup readAllItemGroup(String id);
	
	List<ItemGroup> readItemGroupList(String id);
	
	ItemGroup readOneItemGroup(String id);
	
	void updateItemGroup(ItemGroup record);
	
	void addItemGroup(ItemGroup record);
	
	void deleteItemGroup(String id);

}
