package com.erpnext.crm.customer.service;

import java.util.List;

import com.erpnext.crm.customer.dto.CustomerGroupDTO;

public interface CustomerService {
	
	CustomerGroupDTO getCustomerGroupTree(String id);
	
	List<CustomerGroupDTO> getCustomerGroupTreeList(String id);

}
