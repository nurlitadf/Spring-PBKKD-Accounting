package com.pbkk.accounting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.pbkk.accounting.model.Delivery;

public interface DeliveryService {
	Map<String,Object> getDelivery(Delivery delivery);
	ArrayList<Map<String,Object>> getDeliveries(ArrayList<Delivery>deliveries);
	ArrayList<Delivery> filterByDriverId(ArrayList<Delivery>deliveries, Long driverId);
	ArrayList<Delivery> filterByDate(ArrayList<Delivery>deliveries, Date start, Date end);
}
