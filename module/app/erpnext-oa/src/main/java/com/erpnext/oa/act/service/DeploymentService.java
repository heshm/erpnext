package com.erpnext.oa.act.service;

import org.flowable.engine.repository.Deployment;

public interface DeploymentService {
	
	Deployment deployModel(String modelId);

}
