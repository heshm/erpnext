package com.erpnext.stock.param.mapper;

import com.erpnext.stock.param.domain.ItemPrice;
import java.util.List;

import org.apache.ibatis.annotations.Param;

public interface ItemPriceMapper {
    int deleteByPrimaryKey(@Param("itemId") String itemId, @Param("priceId") String priceId, @Param("currencyId") String currencyId);

    int insert(ItemPrice record);

    ItemPrice selectByPrimaryKey(@Param("itemId") String itemId, @Param("priceId") String priceId, @Param("currencyId") String currencyId);

    List<ItemPrice> selectAll();

    int updateByPrimaryKey(ItemPrice record);
}