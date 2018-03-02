package com.erpnext.oa.act.service;

import java.io.ByteArrayInputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.bpmn.model.BpmnModel;
import org.activiti.bpmn.model.FlowElement;
import org.activiti.bpmn.model.Process;
import org.activiti.bpmn.model.StartEvent;
import org.activiti.bpmn.model.SubProcess;
import org.activiti.bpmn.model.UserTask;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.DeploymentBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.erpnext.oa.act.domain.Model;

@Service
@Transactional(readOnly = true)
public class DeploymentServiceImpl implements DeploymentService {

	@Resource
	private ModelService modelService;
	
	@Autowired
	private RepositoryService repositoryService;

	@Override
	@Transactional
	public Deployment deployModel(String modelId) {
		Model model = modelService.getModel(modelId);
		Deployment deployment = null;
		DeploymentBuilder deploymentBuilder = repositoryService.createDeployment()
		          .name(model.getName())
		          .key(model.getModelKey());
		
		BpmnModel bpmnModel = modelService.getBpmnModel(model);
		Map<String, StartEvent> startEventMap = processNoneStartEvents(bpmnModel);
		for (Process process : bpmnModel.getProcesses()) {
			processUserTasks(process.getFlowElements(), process, startEventMap);
	    }
		byte[] modelXML = modelService.getBpmnXML(bpmnModel);
		deploymentBuilder.addInputStream(model.getModelKey().replaceAll(" ", "") + ".bpmn", new ByteArrayInputStream(modelXML));
		deployment = deploymentBuilder.deploy();
		
		return deployment;
	}

	private Map<String, StartEvent> processNoneStartEvents(BpmnModel bpmnModel) {
		Map<String, StartEvent> startEventMap = new HashMap<String, StartEvent>();
		for (Process process : bpmnModel.getProcesses()) {
			for (FlowElement flowElement : process.getFlowElements()) {
				if (flowElement instanceof StartEvent) {
					StartEvent startEvent = (StartEvent) flowElement;
					if (CollectionUtils.isEmpty(startEvent.getEventDefinitions())) {
						if (StringUtils.isEmpty(startEvent.getInitiator())) {
							startEvent.setInitiator("initiator");
						}
						startEventMap.put(process.getId(), startEvent);
						break;
					}
				}
			}
		}
		return startEventMap;
	}

	private void processUserTasks(Collection<FlowElement> flowElements, Process process,
			Map<String, StartEvent> startEventMap) {

		for (FlowElement flowElement : flowElements) {
			if (flowElement instanceof UserTask) {
				UserTask userTask = (UserTask) flowElement;
				if ("$INITIATOR".equals(userTask.getAssignee())) {
					if (startEventMap.get(process.getId()) != null) {
						userTask.setAssignee("${" + startEventMap.get(process.getId()).getInitiator() + "}");
					}
				}

			} else if (flowElement instanceof SubProcess) {
				processUserTasks(((SubProcess) flowElement).getFlowElements(), process, startEventMap);
			}
		}
	}

}
