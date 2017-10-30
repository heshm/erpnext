package com.erpnext.developer.endpoint;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.developer.dto.ApplicationDTO;
import com.erpnext.developer.service.ApplicationService;
import com.erpnext.framework.web.endpoint.BaseEndpoint;

@RestController
@RequestMapping(value = "/developer/application")
public class DevApplicationEndpoint extends BaseEndpoint {
	
	@Autowired
	private ApplicationService applicationService;
	
	@GetMapping("/getOneApplication/{id}")
	public ApplicationDTO getOneApplication(@PathVariable("id")String id){
		return applicationService.readOneApplication(id);
	}
	
	@GetMapping("/getAllApplication")
	public List<ApplicationDTO> getAllApplication(){
		return applicationService.readAllApplication();
	}

}
