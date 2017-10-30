/*
 * Copyright 2008-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.erpnext.common.param.endpoint;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.HttpStatus;

import com.erpnext.common.param.domain.Unit;
import com.erpnext.common.param.service.UnitService;
import com.erpnext.framework.web.endpoint.BaseEndpoint;
/**
 * Created by Heshm on 2016/11/11.
 */

@RestController
@RequestMapping(value = "/common/param/unit")
public class UnitEndpoint extends BaseEndpoint{
	
	@Autowired
	private UnitService unitService;
	
	@GetMapping("/getAllUnit")
	public List<Unit> getAllUnit() throws InterruptedException{
		List<Unit>  unitList = unitService.readAllUnit();
		return unitList;
	}
	
	@GetMapping("/getPageUnit")
	public Page<Unit> getPageUnit(@PageableDefault(size=10, page=0)Pageable pageable){
		//Pageable pageable = new PageRequest(page_no,10);
		Page<Unit> page = unitService.readPageUnit(pageable);
		return page;
	}
	
	@GetMapping("/getOneUnit/{unitId}")
	@ResponseStatus(HttpStatus.OK)
	public Unit getOneUnit(@PathVariable("unitId") String unitId){
		Unit unit = unitService.readOneUnit(unitId);
		return unit;
	}
	
	@PostMapping("/createOneUnit")
	@ResponseStatus(HttpStatus.CREATED)
	public String createOneUnit(@Valid @RequestBody Unit unit){
		unitService.saveUnit(unit);
		return CREATED;
	}
	
	@PutMapping("/updateOneUnit")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public String updateOneUnit(@Valid @RequestBody Unit unit){
		unitService.updateUnit(unit);
		return UPDATED;
	}
	
	@DeleteMapping("/deleteOneUnit/{unitId}")
	@ResponseStatus(HttpStatus.OK)
	public String deleteOneUnit(@PathVariable("unitId") String unitId) {
		unitService.deleteUnit(unitId);
		return DELETED;
	}

}
