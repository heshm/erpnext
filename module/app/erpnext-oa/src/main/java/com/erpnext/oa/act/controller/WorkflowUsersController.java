package com.erpnext.oa.act.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.activiti.engine.IdentityService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.identity.User;
import org.activiti.engine.identity.UserQuery;
import org.activiti.engine.task.IdentityLink;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.controller.BaseController;
import com.erpnext.oa.act.dto.ResultListDataRepresentation;
import com.erpnext.oa.act.dto.UserDTO;

@RestController
@RequestMapping(value = "/oa/act/app")
public class WorkflowUsersController extends BaseController {

	private static final int MAX_PEOPLE_SIZE = 50;

	@Autowired
	private IdentityService identityService;

	@Autowired
	private RuntimeService runtimeService;

	@Autowired
	private TaskService taskService;

	@GetMapping("/rest/workflow-users")
	public ResultListDataRepresentation getUsers(@RequestParam(value = "filter", required = false) String filter,
			@RequestParam(value = "email", required = false) String email,
			@RequestParam(value = "externalId", required = false) String externalId,
			@RequestParam(value = "excludeTaskId", required = false) String excludeTaskId,
			@RequestParam(value = "excludeProcessId", required = false) String excludeProcessId,
			@RequestParam(value = "groupId", required = false) Long groupId,
			@RequestParam(value = "tenantId", required = false) Long tenantId) {
		// Actual query
		int page = 0;
		int pageSize = MAX_PEOPLE_SIZE;

		UserQuery userQuery = identityService.createUserQuery();
		if (StringUtils.isNotEmpty(filter)) {
			userQuery.userFullNameLike("%" + filter + "%");
		}
		List<User> matchingUsers = userQuery.listPage(page, pageSize);
		// Filter out users already part of the task/process of which the ID has been
		// passed
		if (excludeTaskId != null) {
			filterUsersInvolvedInTask(excludeTaskId, matchingUsers);
		} else if (excludeProcessId != null) {
			filterUsersInvolvedInProcess(excludeProcessId, matchingUsers);
		}

		List<UserDTO> userRepresentations = new ArrayList<UserDTO>(matchingUsers.size());
		for (User user : matchingUsers) {
			userRepresentations.add(new UserDTO(user));
		}
		ResultListDataRepresentation result = new ResultListDataRepresentation(userRepresentations);

		if (page != 0 || (page == 0 && matchingUsers.size() == pageSize)) {
			// Total differs from actual result size, need to fetch it
			result.setTotal(userQuery.count());
		}
		return result;
	}

	private void filterUsersInvolvedInProcess(String excludeProcessId, List<User> matchingUsers) {
		Set<String> involvedUsers = getInvolvedUsersAsSet(
				runtimeService.getIdentityLinksForProcessInstance(excludeProcessId));
		removeinvolvedUsers(matchingUsers, involvedUsers);
	}

	private void filterUsersInvolvedInTask(String excludeTaskId, List<User> matchingUsers) {
		Set<String> involvedUsers = getInvolvedUsersAsSet(taskService.getIdentityLinksForTask(excludeTaskId));
		removeinvolvedUsers(matchingUsers, involvedUsers);
	}

	private Set<String> getInvolvedUsersAsSet(List<IdentityLink> involvedPeople) {
		Set<String> involved = null;
		if (involvedPeople.size() > 0) {
			involved = new HashSet<String>();
			for (IdentityLink link : involvedPeople) {
				if (link.getUserId() != null) {
					involved.add(link.getUserId());
				}
			}
		}
		return involved;
	}

	private void removeinvolvedUsers(List<User> matchingUsers, Set<String> involvedUsers) {
		if (involvedUsers != null) {
			// Using iterator to be able to remove without ConcurrentModExceptions
			Iterator<User> userIt = matchingUsers.iterator();
			while (userIt.hasNext()) {
				if (involvedUsers.contains(userIt.next().getId().toString())) {
					userIt.remove();
				}
			}
		}
	}

}
