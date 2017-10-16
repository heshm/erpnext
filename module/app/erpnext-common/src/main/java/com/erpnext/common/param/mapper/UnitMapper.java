package com.erpnext.common.param.mapper;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.erpnext.common.param.domain.Unit;

public interface UnitMapper {
    int deleteByPrimaryKey(String id);

    int insert(Unit record);

    Unit selectByPrimaryKey(String id);

    List<Unit> selectAll();
    
    List<Unit> selectAll(Pageable pageable);

    int updateByPrimaryKey(Unit record);
    
    int countByExample();
}