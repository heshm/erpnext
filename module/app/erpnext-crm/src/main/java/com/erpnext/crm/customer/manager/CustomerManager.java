package com.erpnext.crm.customer.manager;

import com.erpnext.crm.customer.domain.Customer;

/**
 * Created by Lenovo on 2017/6/20.
 */
public interface CustomerManager {
    Customer getOneCustomerById(String customerId);
}
