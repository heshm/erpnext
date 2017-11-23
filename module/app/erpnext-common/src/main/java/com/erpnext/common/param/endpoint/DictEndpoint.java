package com.erpnext.common.param.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.param.domain.Dict;
import com.erpnext.common.param.service.DictService;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

@RestController
@RequestMapping("/common/param/dict")
public class DictEndpoint extends BaseEndpoint {
	
	@Autowired
	private DictService dictService;
	
	@GetMapping("/list")
	public List<Dict> list(){
		return dictService.listAllDict();
	}

}
