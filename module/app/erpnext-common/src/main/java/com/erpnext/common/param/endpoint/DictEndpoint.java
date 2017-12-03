package com.erpnext.common.param.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.param.domain.Dict;
import com.erpnext.common.param.service.DictService;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

@RestController
@RequestMapping("/common/param/dict")
public class DictEndpoint extends BaseEndpoint {
	
	@Autowired
	private DictService dictService;
	
	@GetMapping("/pageList")
	public Page<Dict> listPage(@RequestParam("dictType") String dictType,
			@PageableDefault(size=10, page=0)Pageable pageable){
		return dictService.listPageDict(dictType, pageable);
	}

}
