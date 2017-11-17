package com.erpnext.crm.customer.mapper;

import com.erpnext.crm.customer.domain.Customer;

import java.util.List;
import java.util.Map;

public interface CustomerMapper {
    int deleteByPrimaryKey(String customerNo);

    int insert(Customer record);

    Customer selectByPrimaryKey(String customerNo);

    List<Customer> selectAll();

    int updateByPrimaryKey(Customer record);
    
    int updateByPrimaryKeySelective(Customer record);
    
    List<Customer> selectList(Map<String,Object> map);
    
    int countByExample(Map<String,Object> map);
}