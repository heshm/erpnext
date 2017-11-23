package com.erpnext.common.setup.mapper;

import com.erpnext.common.setup.domain.DepartmentUserXref;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface DepartmentUserXrefMapper {
    int deleteByPrimaryKey(@Param("departmentId") String departmentId, @Param("userId") String userId);

    int insert(DepartmentUserXref record);

    DepartmentUserXref selectByPrimaryKey(@Param("departmentId") String departmentId, @Param("userId") String userId);

    List<DepartmentUserXref> selectAll();

    int updateByPrimaryKey(DepartmentUserXref record);
}