package com.erpnext.stock.param.service;

import java.util.List;

import com.erpnext.stock.param.mapper.EntryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erpnext.stock.param.domain.Warehouse;
import com.erpnext.stock.param.mapper.WarehouseMapper;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class WarehouseServiceImpl implements WarehouseService {
	
	@Autowired
	private WarehouseMapper warehouseMapper;
	@Autowired
	private EntryMapper entryMapper;

	@Override
	public List<Warehouse> readAllWarehouseListWithoutChildren() {
		return warehouseMapper.selectAll();
	}

	@Override
	public List<Warehouse> readAllWarehouseListWithChildren(){
		List<Warehouse> list = warehouseMapper.selectAll();
		for(Warehouse warehouse : list){
			warehouse.setEntryList(entryMapper.selectByParent(warehouse.getId()));
		}
		return list;
	}

	@Override
	public Warehouse getOneWarehouseWithChildren(String id) {
		Warehouse warehouse = warehouseMapper.selectByPrimaryKey(id);
		warehouse.setEntryList(entryMapper.selectByParent(id));
		return warehouse;
	}
}
