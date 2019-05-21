package com.pbkk.accounting.service;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.pbkk.accounting.model.Customers;
import com.pbkk.accounting.model.Drivers;
import com.pbkk.accounting.model.Restaurants;

public interface SendAPI {
	Customers getCustomerData(Long idCustomer, String token);
	Restaurants getRestaurantData(Long idRestaurants, String token);
	Drivers getDriverData(Long idDriver, String token);
	String getToken();
}
