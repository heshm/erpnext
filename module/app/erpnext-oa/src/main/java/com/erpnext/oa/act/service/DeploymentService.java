package com.erpnext.oa.act.service;

import org.activiti.engine.repository.Deployment;

public interface DeploymentService {
	
	Deployment deployModel(String modelId);

}
