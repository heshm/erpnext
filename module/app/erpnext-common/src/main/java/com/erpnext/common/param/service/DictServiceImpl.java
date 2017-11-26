package com.erpnext.common.param.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.common.param.domain.Dict;
import com.erpnext.common.param.domain.DictType;
import com.erpnext.common.param.mapper.DictMapper;
import com.erpnext.common.param.mapper.DictTypeMapper;

@Service
@Transactional(readOnly=true) 
public class DictServiceImpl implements DictService {
	
	@Autowired
	private DictTypeMapper dictTypeMapper;
	
	@Autowired
	private DictMapper dictMapper;

	@Override
	public List<Dict> listAllDict() {
		return dictMapper.selectAll();
	}

	@Override
	public Page<Dict> listPageDict(String dictType, Pageable pageable) {
		List<Dict> list = dictMapper.selectList(dictType, pageable);
		int total = dictMapper.countByExample(dictType);
		return new PageImpl<Dict>(list,pageable,total);
	}

	@Override
	public List<DictType> listAllDictType() {
		return dictTypeMapper.selectAll();
	}

}
