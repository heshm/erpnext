package com.erpnext.developer.service;

import java.util.List;

import com.erpnext.developer.dto.ApplicationDTO;

public interface ApplicationService {
	
	ApplicationDTO readOneApplication(String id);
	
	List<ApplicationDTO> readAllApplication();

}
