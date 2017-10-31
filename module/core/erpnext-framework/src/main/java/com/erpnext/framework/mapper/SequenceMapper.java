package com.erpnext.framework.mapper;

import com.erpnext.framework.domain.Sequence;
import java.util.List;

public interface SequenceMapper {
    int deleteByPrimaryKey(String id);

    int insert(Sequence record);

    Sequence selectByPrimaryKey(String id);

    List<Sequence> selectAll();

    int updateByPrimaryKey(Sequence record);
}