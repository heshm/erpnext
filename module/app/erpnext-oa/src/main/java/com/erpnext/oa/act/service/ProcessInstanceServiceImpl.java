package com.erpnext.oa.act.service;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import com.erpnext.framework.web.util.AuthenticationUtils;
import org.flowable.engine.HistoryService;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.history.HistoricProcessInstanceQuery;
import org.flowable.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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

	@Autowired
	protected RuntimeService runtimeService;

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

	@Override
	@Transactional
	public void deleteProcessInstance(String processInstanceId){
		String userId = AuthenticationUtils.getUserId();
		HistoricProcessInstance processInstance = historyService.createHistoricProcessInstanceQuery()
				.processInstanceId(processInstanceId)
				.startedBy(userId) // Permission
				.singleResult();

		if (processInstance.getEndTime() != null){
			historyService.deleteHistoricProcessInstance(processInstanceId);
		}else {
			runtimeService.deleteProcessInstance(processInstanceId, "Cancelled by " + userId);
		}

	}

	@Override
	public Page<HistoricProcessInstance> listHisProcessInstance(Pageable pageable,Map<String, Object> filter) {
		HistoricProcessInstanceQuery query = historyService.createHistoricProcessInstanceQuery();
		query.involvedUser(AuthenticationUtils.getUserId());
		if(filter.containsKey("startDate")) {
			query.startedAfter((Date)filter.get("startDate"));
		}
		if(filter.containsKey("endDate")) {
			query.finishedBefore((Date)filter.get("endDate"));
		}
		int total = (int)query.count();
		List<HistoricProcessInstance> list = query.listPage(pageable.getOffset(), pageable.getPageSize());
		return new PageImpl<>(list, pageable, total);
	}

}
