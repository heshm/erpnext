package com.erpnext.oa.act.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.activiti.engine.HistoryService;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.history.HistoricProcessInstance;
import org.activiti.engine.history.HistoricProcessInstanceQuery;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.erpnext.oa.act.dto.ProcessInstanceDTO;


@Service
@Transactional(readOnly=true)
public class ProcessInstanceServiceImpl implements ProcessInstanceService {
	
	@Autowired
	private HistoryService historyService;
	
	@Autowired
	private RepositoryService repositoryService;


	@Override
	public List<ProcessInstanceDTO> listProcessInstanceDTO(String userId, String state) {
		HistoricProcessInstanceQuery instanceQuery = historyService.createHistoricProcessInstanceQuery();
		instanceQuery.involvedUser(userId);
		if(StringUtils.isNotEmpty(state)) {
			switch(state) {
				case STATE_RUNNING:
					instanceQuery.unfinished();
					break;
				case STATE_COMPLETED:
					instanceQuery.finished();
					break;
				default:
					instanceQuery.unfinished();
					break;
					
			}
		}
		instanceQuery.orderByProcessInstanceStartTime().desc();
		List<HistoricProcessInstance> instances = instanceQuery.list();
		List<ProcessInstanceDTO> result = new LinkedList<>();
		Optional.ofNullable(instances).ifPresent(list -> {
			list.forEach(instance -> {
				ProcessDefinitionEntity procDef = (ProcessDefinitionEntity) repositoryService.getProcessDefinition(instance.getProcessDefinitionId());
				ProcessInstanceDTO dto = new ProcessInstanceDTO(instance, procDef, procDef.isGraphicalNotationDefined(), userId);
				result.add(dto);
			});
		});
		
		return result;
	}

}
