package com.erpnext.common.param.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erpnext.common.param.domain.Dict;
import com.erpnext.common.param.domain.DictType;

public interface DictService {
	
	List<Dict> listAllDict();
	
	Page<Dict> listPageDict(String dictType,Pageable pageable);
	
	List<DictType> listAllDictType();

}
