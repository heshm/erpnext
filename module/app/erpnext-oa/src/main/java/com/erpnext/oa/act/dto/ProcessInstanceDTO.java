package com.erpnext.oa.act.dto;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;

public class ProcessInstanceDTO {
	
	private String id;
	private String name;
	private String businessKey;
	private String processDefinitionId;
	private String tenantId;
	private Date started;
	private Date ended;
	private String startedBy;
	private String processDefinitionName;
	private String processDefinitionDescription;
	private String processDefinitionKey;
	private String processDefinitionCategory;
	private int processDefinitionVersion;
	private String processDefinitionDeploymentId;
	private boolean graphicalNotationDefined;
	private boolean startFormDefined;
    
	private List<RestVariable> variables = new ArrayList<RestVariable>();

    public ProcessInstanceDTO(ProcessInstance processInstance, ProcessDefinition processDefinition, boolean graphicalNotation,String startedBy) {
        this(processInstance, graphicalNotation, startedBy);
        mapProcessDefinition(processDefinition);
    }

    public ProcessInstanceDTO(ProcessInstance processInstance, boolean graphicalNotation, String startedBy) {
        this.id = processInstance.getId();
        this.name = processInstance.getName();
        this.businessKey = processInstance.getBusinessKey();
        this.processDefinitionId = processInstance.getProcessDefinitionId();
        this.tenantId = processInstance.getTenantId();
        this.graphicalNotationDefined = graphicalNotation;
        this.startedBy = startedBy;
    }

    public ProcessInstanceDTO(HistoricProcessInstance processInstance, ProcessDefinition processDefinition, boolean graphicalNotation, String startedBy) {
        this(processInstance, graphicalNotation, startedBy);
        mapProcessDefinition(processDefinition);
    }
    
    public ProcessInstanceDTO(HistoricProcessInstance processInstance, boolean graphicalNotation, String startedBy) {
        this.id = processInstance.getId();
        this.name = processInstance.getName();
        this.businessKey = processInstance.getBusinessKey();
        this.processDefinitionId = processInstance.getProcessDefinitionId();
        this.tenantId = processInstance.getTenantId();
        this.graphicalNotationDefined = graphicalNotation;
        this.started = processInstance.getStartTime();
        this.ended = processInstance.getEndTime();
        this.startedBy = startedBy;
    }

    protected void mapProcessDefinition(ProcessDefinition processDefinition) {
        if (processDefinition != null) {
            this.processDefinitionName = processDefinition.getName();
            this.processDefinitionDescription = processDefinition.getDescription();
            this.processDefinitionKey = processDefinition.getKey();
            this.processDefinitionCategory = processDefinition.getCategory();
            this.processDefinitionVersion = processDefinition.getVersion();
            this.processDefinitionDeploymentId = processDefinition.getDeploymentId();
        }
    }
    public ProcessInstanceDTO() {}

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public String getStartedBy() {
        return startedBy;
    }
    
    public void setStartedBy(String startedBy) {
      this.startedBy = startedBy;
  }
    
    public String getBusinessKey() {
        return businessKey;
    }
    
    public void setBusinessKey(String businessKey) {
        this.businessKey = businessKey;
    }

    public String getProcessDefinitionId() {
        return processDefinitionId;
    }

    public void setProcessDefinitionId(String processDefinitionId) {
        this.processDefinitionId = processDefinitionId;
    }

    public String getTenantId() {
        return tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId;
    }
    
    public Date getStarted() {
        return started;
    }
    
    public void setStarted(Date started) {
        this.started = started;
    }
    
    public Date getEnded() {
        return ended;
    }
    
    public void setEnded(Date ended) {
        this.ended = ended;
    }
    
    public void setName(String name) {
        this.name = name;
    }

    public String getProcessDefinitionName() {
        return processDefinitionName;
    }

    public void setProcessDefinitionName(String processDefinitionName) {
        this.processDefinitionName = processDefinitionName;
    }

    public String getProcessDefinitionDescription() {
        return processDefinitionDescription;
    }

    public void setProcessDefinitionDescription(String processDefinitionDescription) {
        this.processDefinitionDescription = processDefinitionDescription;
    }

    public String getProcessDefinitionKey() {
        return processDefinitionKey;
    }

    public void setProcessDefinitionKey(String processDefinitionKey) {
        this.processDefinitionKey = processDefinitionKey;
    }

    public String getProcessDefinitionCategory() {
        return processDefinitionCategory;
    }

    public void setProcessDefinitionCategory(String processDefinitionCategory) {
        this.processDefinitionCategory = processDefinitionCategory;
    }

    public int getProcessDefinitionVersion() {
        return processDefinitionVersion;
    }

    public void setProcessDefinitionVersion(int processDefinitionVersion) {
        this.processDefinitionVersion = processDefinitionVersion;
    }

    public String getProcessDefinitionDeploymentId() {
        return processDefinitionDeploymentId;
    }

    public void setProcessDefinitionDeploymentId(String processDefinitionDeploymentId) {
        this.processDefinitionDeploymentId = processDefinitionDeploymentId;
    }
    
    public List<RestVariable> getVariables() {
        return variables;
    }
  
    public void setVariables(List<RestVariable> variables) {
        this.variables = variables;
    }
  
    public void addVariable(RestVariable variable) {
        variables.add(variable);
    }
    
    public boolean isGraphicalNotationDefined() {
        return graphicalNotationDefined;
    }
    
    public void setGraphicalNotationDefined(boolean graphicalNotationDefined) {
        this.graphicalNotationDefined = graphicalNotationDefined;
    }

    public boolean isStartFormDefined() {
        return startFormDefined;
    }

    public void setStartFormDefined(boolean startFormDefined) {
        this.startFormDefined = startFormDefined;
    }

}
