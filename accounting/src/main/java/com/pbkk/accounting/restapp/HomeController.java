package com.pbkk.accounting.restapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/")

public class HomeController {
	@Autowired
	@ResponseBody
	@RequestMapping("/transactions")
	public Map<String, Object> generateTransactions(){
		Map<String, Object> map = new LinkedHashMap<>();
		return map;
	}
}
