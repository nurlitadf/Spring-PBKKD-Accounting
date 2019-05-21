package com.pbkk.accounting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.pbkk.accounting.model.Cashflow;

public interface CashflowService {
	public ArrayList<Cashflow> filterByCustomerId(ArrayList<Cashflow>listCashflow, Long customerId);
	public ArrayList<Cashflow> filterByRestaurantId(ArrayList<Cashflow>listCashflow, Long restaurantId);
	public ArrayList<Cashflow> filterByDriverId(ArrayList<Cashflow>listCashflow, Long driverId);
	public ArrayList<Cashflow> filterByDate(ArrayList<Cashflow>listCashflow, Date startDate, Date endDate);
	public Map<String, Object> getCashflow(Cashflow cashflow);
	public ArrayList<Map<String, Object>> getCashflows(ArrayList<Cashflow> cashflows);
}
