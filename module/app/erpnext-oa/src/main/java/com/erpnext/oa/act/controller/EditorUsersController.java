package com.erpnext.oa.act.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.common.authority.dto.AdminUserDTO;
import com.erpnext.common.authority.service.AdminUserService;
import com.erpnext.oa.act.dto.ResultListDataRepresentation;
import com.erpnext.oa.flowable.editor.dto.UserRepresentation;

@RestController
@RequestMapping(value = "/oa/act/app")
public class EditorUsersController {
	
	@Autowired
	private AdminUserService adminUserService;
	
	@GetMapping("/rest/editor-users")
    public ResultListDataRepresentation getUsers(@RequestParam(value = "filter", required = false) String filter) {
		List<AdminUserDTO> matchingUsers = adminUserService.getUsers(filter);
		List<UserRepresentation> userRepresentations = new ArrayList<>(matchingUsers.size());
        for (AdminUserDTO user : matchingUsers) {
            userRepresentations.add(new UserRepresentation(user));
        }
        return new ResultListDataRepresentation(userRepresentations);
    }

}
