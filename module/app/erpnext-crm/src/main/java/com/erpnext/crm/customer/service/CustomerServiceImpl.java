package com.erpnext.crm.customer.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.crm.customer.domain.CustomerGroup;
import com.erpnext.crm.customer.dto.CustomerGroupDTO;
import com.erpnext.crm.customer.mapper.CustomerGroupMapper;
import com.erpnext.crm.customer.mapper.CustomerMapper;
import com.erpnext.framework.util.IDUtils;
import com.erpnext.framework.web.util.AuthenticationUtils;

@Service
@Transactional(readOnly=true)
public class CustomerServiceImpl implements CustomerService {
	
	@Autowired
	private CustomerGroupMapper customerGroupMapper;
	@Autowired
	private CustomerMapper customerMapper;

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

	@Override
	@Transactional
	public void updateCustomerGroup(CustomerGroupDTO dto) {
		CustomerGroup record = customerGroupMapper.selectByPrimaryKey(dto.getId());
		record.setName(dto.getName());
		record.setIsGroup(dto.getIsGroup());
		record.setModifyBy(AuthenticationUtils.getPrincipal().getUsername());
		record.setModifyTime(new Date());
		record.setStatus(dto.getStatus());
		customerGroupMapper.updateByPrimaryKey(record);
		
	}

	@Override
	@Transactional
	public void createCustomerGroup(CustomerGroupDTO dto) {
		CustomerGroup customerGroup = new CustomerGroup();
		BeanUtils.copyProperties(dto, customerGroup);
		customerGroup.setId(IDUtils.uuid());
		customerGroup.setCreateTime(new Date());
		customerGroup.setModifyBy(AuthenticationUtils.getPrincipal().getUsername());
		customerGroup.setModifyTime(new Date());
		customerGroupMapper.insert(customerGroup);
		
	}


}
