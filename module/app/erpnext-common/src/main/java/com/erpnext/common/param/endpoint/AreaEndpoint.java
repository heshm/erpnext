package com.erpnext.common.param.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.param.dto.AreaDTO;
import com.erpnext.common.param.service.AreaService;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

@RestController
@RequestMapping("/common/param/area")
public class AreaEndpoint extends BaseEndpoint {
	@Autowired
	private AreaService areaService;
	
	@GetMapping("/tree/{id}")
	public AreaDTO tree(@PathVariable("id") String id){
		return areaService.getNestedArea(id);
	}

}
