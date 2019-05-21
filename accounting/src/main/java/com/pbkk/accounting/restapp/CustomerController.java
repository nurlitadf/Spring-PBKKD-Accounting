package com.pbkk.accounting.restapp;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbkk.accounting.aop.TokenRequired;
import com.pbkk.accounting.exception.ResourceNotFoundException;
import com.pbkk.accounting.model.Customer;
import com.pbkk.accounting.model.Customers;
import com.pbkk.accounting.repository.CustomerRepository;
import com.pbkk.accounting.service.SendAPI;

@RestController
@RequestMapping("/customer")

public class CustomerController {
	@Autowired
    private CustomerRepository customerRepository;
	@Autowired
	SendAPI sendAPI;
	@ResponseBody
	@RequestMapping("")
	public List<Customer> getCustomer(){
		return customerRepository.findAll();
	}
	@ResponseBody
	@RequestMapping("/{id}")
	@TokenRequired
	public Customers getCustomerById(@PathVariable(value = "id") Long id){
		String token=sendAPI.getToken();
		return sendAPI.getCustomerData(id,token);
	}
//	@ResponseBody
//	@RequestMapping(value = "/create", method = RequestMethod.POST)
//	public Customer createCustomer(@RequestParam(value="id") Long id, @RequestParam(value="name") String name,
//			@RequestParam(value="address") String address, @RequestParam(value="phone") String phone){
//		Customer customer=new Customer();
//		customer.setCustomerId(id);
//		customer.setCustomerName(name);
//		customer.setCustomerAddress(address);
//		customer.setCustomerPhone(phone);
//		return customerRepository.save(customer);
//	}
//	
//	@ResponseBody
//	@RequestMapping(value = "/update/{id}", method = RequestMethod.PUT)
//	public Customer updateCustomer(@PathVariable(value = "id") Long id,
//			@RequestParam(value="name") String name,
//			@RequestParam(value="address") String address, @RequestParam(value="phone") String phone) {
//		Customer customer = customerRepository.findById(id)
//	            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
//		if(name!=null&&name!="") customer.setCustomerName(name);
//		if(address!=null&&address!="") customer.setCustomerAddress(address);
//		if(phone!=null&&phone!="") customer.setCustomerPhone(phone);
//	    Customer updatedCustomer = customerRepository.save(customer);
//	    return updatedCustomer;
//	}
//	@ResponseBody
//	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
//	public ResponseEntity<?> deleteCustomer(@PathVariable(value = "id") Long id) {
//	    Customer customer = customerRepository.findById(id)
//	            .orElseThrow(() -> new ResourceNotFoundException("Customer", "id", id));
//
//	    customerRepository.delete(customer);
//
//	    return ResponseEntity.ok().build();
//	}

}
