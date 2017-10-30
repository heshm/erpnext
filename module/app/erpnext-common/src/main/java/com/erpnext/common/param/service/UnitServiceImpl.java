package com.erpnext.common.param.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.common.param.domain.Unit;
import com.erpnext.common.param.mapper.UnitMapper;


@Service
@Transactional(readOnly=true) 
public class UnitServiceImpl implements UnitService{
	
	@Autowired
	private UnitMapper unitMapper;

	@Override
	public List<Unit> readAllUnit() {
		return unitMapper.selectAll();
	}

	@Override
	@Transactional
	public void saveUnit(Unit unit){
		// TODO Auto-generated method stub
		if(unitMapper.selectByPrimaryKey(unit.getId()) != null){
			throw new DuplicateKeyException("计量单位ID已经存在!");
		}
		unitMapper.insert(unit);
	}

	@Override
	@Transactional
	public void updateUnit(Unit unit) {
		unitMapper.updateByPrimaryKey(unit);
	}

	@Transactional
	@Override
	public void deleteUnit(String id) {
		// TODO Auto-generated method stub
		unitMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Unit readOneUnit(String unitId) {
		// TODO Auto-generated method stub
		return unitMapper.selectByPrimaryKey(unitId);
	}
	
	@Override
	public Page<Unit> readPageUnit(Pageable pageable){
		List<Unit> list = unitMapper.selectAll(pageable);
		int total = unitMapper.countByExample();
		return new PageImpl<Unit>(list,pageable,total);
	}

}
