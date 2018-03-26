package com.erpnext.oa.act.dto;

import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;

public class ProcessQueryDTO {
	
	private ProcessDefinition processDefinition;
	
	private Deployment deployment;
	
	public ProcessQueryDTO(){
		
	}
	
	public ProcessQueryDTO(ProcessDefinition processDefinition,Deployment deployment){
		this.processDefinition = processDefinition;
		this.deployment = deployment;
	}

	public ProcessDefinition getProcessDefinition() {
		return processDefinition;
	}

	public void setProcessDefinition(ProcessDefinition processDefinition) {
		this.processDefinition = processDefinition;
	}

	public Deployment getDeployment() {
		return deployment;
	}

	public void setDeployment(Deployment deployment) {
		this.deployment = deployment;
	}
}
