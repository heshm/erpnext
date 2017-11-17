package com.erpnext.crm.customer.manager;

import com.erpnext.crm.customer.domain.Customer;
import com.erpnext.crm.customer.mapper.CustomerMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Lenovo on 2017/6/20.
 */
@Component
@Transactional(readOnly = true)
public class CustomerManagerImpl implements CustomerManager{
    @Autowired
    private CustomerMapper customerMapper;
    @Override
    public Customer getOneCustomerById(String customerId) {
        return customerMapper.selectByPrimaryKey(customerId);
    }
}
