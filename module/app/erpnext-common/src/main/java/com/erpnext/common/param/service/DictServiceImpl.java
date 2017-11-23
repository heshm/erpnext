package com.erpnext.common.param.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.common.param.domain.Dict;
import com.erpnext.common.param.mapper.DictMapper;

@Service
@Transactional(readOnly=true) 
public class DictServiceImpl implements DictService {
	
	@Autowired
	private DictMapper dictMapper;

	@Override
	public List<Dict> listAllDict() {
		// TODO Auto-generated method stub
		return dictMapper.selectAll();
	}

}
