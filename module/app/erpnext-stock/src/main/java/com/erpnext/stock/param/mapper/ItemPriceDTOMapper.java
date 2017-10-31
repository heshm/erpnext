package com.erpnext.stock.param.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.erpnext.stock.param.dto.ItemPriceDTO;

public interface ItemPriceDTOMapper {
	
	List<ItemPriceDTO> selectAll();
	
	ItemPriceDTO selectOne(@Param("itemId") String itemId, @Param("priceId") String priceId, @Param("currencyId") String currencyId);

	List<ItemPriceDTO> selectbyItemId(@Param("itemId") String itemId);
}
