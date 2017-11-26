package com.erpnext.common.param.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.param.service.DictService;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

import com.erpnext.common.param.domain.DictType;

@RestController
@RequestMapping("/common/param/dictType")
public class DictTypeEndpoint extends BaseEndpoint {
	
	@Autowired
	private DictService dictService;
	
	@GetMapping("/list")
	public List<DictType> list(){
		return dictService.listAllDictType();
	}
	
	@PostMapping("/create")
	public String create(@RequestBody DictType dictType) {
		dictService.createDictType(dictType);
		return CREATED;
	}
	
	@PutMapping("/update")
	public String update(@RequestBody DictType dictType) {
		dictService.updateDictType(dictType);
		return UPDATED;
	}
	

}
