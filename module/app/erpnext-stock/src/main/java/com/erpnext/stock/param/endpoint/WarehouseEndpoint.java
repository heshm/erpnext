package com.erpnext.stock.param.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.stock.param.service.WarehouseService;
import com.erpnext.stock.param.domain.Warehouse;;

@RestController
@RequestMapping(value = "/stock/param/warehouse")
public class WarehouseEndpoint extends BaseEndpoint {
	
	@Autowired
	private WarehouseService warehouseService;
	
	@GetMapping("/list")
	public List<Warehouse> list(){
		return warehouseService.readAllWarehouseListWithoutChildren();
	}
	
	@GetMapping("/listOne/{id}")
	public Warehouse listOne(@PathVariable("id") String id){
		return warehouseService.getOneWarehouseWithChildren(id);
	}

}
