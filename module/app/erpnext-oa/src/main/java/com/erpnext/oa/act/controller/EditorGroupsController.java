package com.erpnext.oa.act.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.controller.BaseController;
import com.erpnext.oa.act.dto.ResultListDataDTO;

import java.util.List;

import org.activiti.engine.IdentityService;
import org.activiti.engine.identity.Group;

@RestController
@RequestMapping(value = "/oa/act/app")
public class EditorGroupsController extends BaseController {
	
	private final static String TYPE_ASSIGNMENT = "assignment";
	
	@Autowired
	private IdentityService identityService;

	@GetMapping("/rest/editor-groups")
	public ResultListDataDTO getGroups(@RequestParam(required = false, value = "filter") String filter) {
		String groupNameFilter = filter;
		if (StringUtils.isEmpty(groupNameFilter)) {
			groupNameFilter = "%";
		} else {
			groupNameFilter = "%" + groupNameFilter + "%";
		}
		List<Group> matchingGroups = identityService.createGroupQuery().groupNameLike(groupNameFilter)
				.groupType(TYPE_ASSIGNMENT).list();

		ResultListDataDTO result = new ResultListDataDTO(matchingGroups);
		return result;
	}

}
