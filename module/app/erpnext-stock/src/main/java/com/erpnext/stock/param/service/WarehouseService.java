package com.erpnext.stock.param.service;

import java.util.List;

import com.erpnext.stock.param.domain.Warehouse;

public interface WarehouseService {
	
	List<Warehouse> readAllWarehouseListWithoutChildren();

	List<Warehouse> readAllWarehouseListWithChildren();

	Warehouse getOneWarehouseWithChildren(String id);

}
