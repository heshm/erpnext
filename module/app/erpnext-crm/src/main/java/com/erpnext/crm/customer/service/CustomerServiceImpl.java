package com.erpnext.crm.customer.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.crm.customer.domain.CustomerGroup;
import com.erpnext.crm.customer.dto.CustomerGroupDTO;
import com.erpnext.crm.customer.mapper.CustomerGroupMapper;
import com.erpnext.crm.customer.mapper.CustomerMapper;

@Service
@Transactional(readOnly=true)
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerGroupMapper customerGroupMapper;

	@Override
	public CustomerGroupDTO getCustomerGroupTree(String id) {
		CustomerGroup customerGroup = customerGroupMapper.selectByPrimaryKey(id);
		if (customerGroup == null)
			return null;
		CustomerGroupDTO dto = new CustomerGroupDTO(customerGroup);
		List<CustomerGroup> children = customerGroupMapper.selectChildren(id);
		if(children != null){
			List<CustomerGroupDTO> dtoList = new ArrayList<>();
			for(CustomerGroup child : children){
				dtoList.add(getCustomerGroupTree(child.getId()));
			}
			dto.setChildren(dtoList);
		}
		return dto;
	}
	
	@Override
	public List<CustomerGroupDTO> getCustomerGroupTreeList(String id) {
		List<CustomerGroupDTO> resultList = new ArrayList<>(500);
		CustomerGroup customerGroup = customerGroupMapper.selectByPrimaryKey(id);
		if (customerGroup != null){
			CustomerGroupDTO dto = new CustomerGroupDTO(customerGroup);
			resultList.add(dto);
			List<CustomerGroup> children = customerGroupMapper.selectChildren(id);
			if(children != null){
    			for(CustomerGroup child : children){
    				resultList.addAll(getCustomerGroupTreeList(child.getId()));
    			}
    		}
		}
		return resultList;
	}

}
