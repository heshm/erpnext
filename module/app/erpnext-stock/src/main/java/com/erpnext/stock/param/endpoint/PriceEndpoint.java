package com.erpnext.stock.param.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.stock.param.domain.Price;
import com.erpnext.stock.param.service.PriceService;

@RestController
@RequestMapping(value = "/stock/param/price")
public class PriceEndpoint extends BaseEndpoint {
	
	@Autowired
	private PriceService priceService;
	
	@GetMapping("/list")
	public List<Price> list(){
		return priceService.getAllPriceList();
	}

}
