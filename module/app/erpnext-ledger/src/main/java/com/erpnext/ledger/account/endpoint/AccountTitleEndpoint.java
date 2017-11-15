package com.erpnext.ledger.account.endpoint;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.erpnext.framework.web.endpoint.BaseEndpoint;
import com.erpnext.ledger.account.domain.AccountTitle;
import com.erpnext.ledger.account.service.AccountTitleService;

@RestController
@RequestMapping(value = "/ledger/account/accountTitle")
public class AccountTitleEndpoint extends BaseEndpoint {
	
	@Autowired
	private AccountTitleService accountTitleService;
	
	@GetMapping("/listOne/{id}")
	public AccountTitle listOneAccountTitle(@PathVariable("id")  String id){
		return accountTitleService.readOneAccountTitle(id);
	}
	
	@GetMapping("/listPage")
	public Page<AccountTitle> listPageAccountTitle(@RequestParam Map<String, Object> params,
			@PageableDefault(size=10, page=0)Pageable pageable){
		return accountTitleService.readPageAccountTitle(pageable, params);
	}

}
