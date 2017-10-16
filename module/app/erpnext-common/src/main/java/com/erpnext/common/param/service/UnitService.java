package com.erpnext.common.param.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erpnext.common.param.domain.Unit;

public interface UnitService{
	
	List<Unit> readAllUnit();
	
	Unit readOneUnit(String unitId);
	
	void saveUnit(Unit unit);

	void updateUnit(Unit unit);
	
	void deleteUnit(String id);
	
	Page<Unit> readPageUnit(Pageable pageable);

}
