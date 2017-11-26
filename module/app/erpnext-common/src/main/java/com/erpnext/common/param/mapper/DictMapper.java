package com.erpnext.common.param.mapper;

import com.erpnext.common.param.domain.Dict;
import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.springframework.data.domain.Pageable;

public interface DictMapper {
    int deleteByPrimaryKey(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

    int insert(Dict record);

    Dict selectByPrimaryKey(@Param("dictType") String dictType, @Param("dictValue") String dictValue);

    List<Dict> selectAll();

    int updateByPrimaryKey(Dict record);
    
    List<Dict> selectList(@Param("dictType") String dictType, @Param("pageable")Pageable pageable);
    
    int countByExample(@Param("dictType") String dictType);
    
}