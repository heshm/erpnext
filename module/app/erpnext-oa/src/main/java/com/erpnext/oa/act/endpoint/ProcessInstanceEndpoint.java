package com.erpnext.oa.act.endpoint;

import java.util.List;
import java.util.Map;

import org.flowable.engine.history.HistoricProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.framework.web.util.AuthenticationUtils;
import com.erpnext.oa.act.dto.ProcessInstanceDTO;
import com.erpnext.oa.act.service.ProcessInstanceService;

@RestController
@RequestMapping(value = "/oa/act/processInst")
public class ProcessInstanceEndpoint extends BaseEndpoint {
	
	@Autowired
	private ProcessInstanceService processInstanceService;

	@GetMapping("/list/{state}")
	public List<ProcessInstanceDTO> list(@PathVariable String state){
		String userId = AuthenticationUtils.getUserId();
		return processInstanceService.listProcessInstanceDTO(userId, state);
	}

	@DeleteMapping("/delete/{processInstanceId}")
	public String deleteProcessInstance(@PathVariable String processInstanceId){
		processInstanceService.deleteProcessInstance(processInstanceId);
		return DELETED;
	}
	
	@GetMapping("/pageListHis")
	public Page<HistoricProcessInstance> pageListHistoricProcessInstance(
			@PageableDefault(size=10, page=0)Pageable pageable,
			@RequestParam Map<String, Object> filter){
		return processInstanceService.listHisProcessInstance(pageable, filter);
		
	}

}
 