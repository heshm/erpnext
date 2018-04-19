package com.erpnext.stock.param.manager;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.stock.param.domain.Price;
import com.erpnext.stock.param.mapper.PriceMapper;

@Component
@Transactional(readOnly=true) 
public class PriceManagerImpl implements PriceManager {
	@Autowired
	private PriceMapper priceListMapper;

	@Override
	public List<Price> getAllPriceList() {
		return priceListMapper.selectAll();
	}

}
