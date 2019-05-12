package com.pbkk.accounting.restapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.RestController;

import com.pbkk.accounting.model.Customer;
import com.pbkk.accounting.repository.CustomerRepository;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.LinkedHashMap;
import java.util.Map;


@RestController
@RequestMapping("/")
public class HomeController {
	
}
