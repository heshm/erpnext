package com.erpnext.stock.param.mapper;

import com.erpnext.stock.param.domain.Price;
import java.util.List;

public interface PriceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Price record);

    Price selectByPrimaryKey(String id);

    List<Price> selectAll();

    int updateByPrimaryKey(Price record);
}