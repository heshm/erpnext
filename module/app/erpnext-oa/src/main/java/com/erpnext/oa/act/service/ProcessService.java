package com.erpnext.oa.act.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.erpnext.oa.act.dto.ProcessQueryDTO;

public interface ProcessService {
	
	Page<ProcessQueryDTO> getPageProcessDefinitionList(Pageable pageable,String category);

}
