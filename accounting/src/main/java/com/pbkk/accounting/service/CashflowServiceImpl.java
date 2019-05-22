package com.pbkk.accounting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbkk.accounting.model.Cashflow;
import com.pbkk.accounting.model.Customers;
import com.pbkk.accounting.model.Drivers;
import com.pbkk.accounting.model.Restaurants;
import com.pbkk.accounting.model.Transaksi;

@Service
public class CashflowServiceImpl implements CashflowService{
	@Autowired
	SendAPI sendAPI;
	@Override
	public ArrayList<Cashflow> filterByCustomerId(ArrayList<Cashflow> listCashflow, Long customerId) {
		ArrayList<Cashflow> filteredListCashflow=new ArrayList<Cashflow>();
		Iterator iterator=listCashflow.iterator();
		while(iterator.hasNext()) {
			Cashflow cashflow=(Cashflow) iterator.next();
			if(cashflow.getSource()=="customer"&&cashflow.getSourceId()==customerId||
					cashflow.getDestination()=="customer"&&cashflow.getDestinationId()==customerId) {
				filteredListCashflow.add(cashflow);
			}
		}
		return filteredListCashflow;
	}

	@Override
	public ArrayList<Cashflow> filterByRestaurantId(ArrayList<Cashflow> listCashflow, Long restaurantId) {
		ArrayList<Cashflow> filteredListCashflow=new ArrayList<Cashflow>();
		Iterator iterator=listCashflow.iterator();
		while(iterator.hasNext()) {
			Cashflow cashflow=(Cashflow) iterator.next();
			if(cashflow.getSource()=="restaurant"&&cashflow.getSourceId()==restaurantId||
					cashflow.getDestination()=="restaurant"&&cashflow.getDestinationId()==restaurantId) {
				filteredListCashflow.add(cashflow);
			}
		}
		return filteredListCashflow;
	}

	@Override
	public ArrayList<Cashflow> filterByDate(ArrayList<Cashflow> listCashflow, Date startDate, Date endDate) {
		ArrayList<Cashflow> filteredListCashflow=new ArrayList<Cashflow>();
		Iterator iterator=listCashflow.iterator();
		while(iterator.hasNext()) {
			Cashflow cashflow=(Cashflow) iterator.next();
			Date cashflowDate=cashflow.getCreatedAt();
			if((cashflowDate.after(startDate)||cashflowDate.equals(startDate))&&
					(cashflowDate.before(endDate)||cashflowDate.equals(endDate))) {
				filteredListCashflow.add(cashflow);
			}
		}
		return filteredListCashflow;
	}

	@Override
	public ArrayList<Cashflow> filterByDriverId(ArrayList<Cashflow> listCashflow, Long driverId) {
		ArrayList<Cashflow> filteredListCashflow=new ArrayList<Cashflow>();
		Iterator iterator=listCashflow.iterator();
		while(iterator.hasNext()) {
			Cashflow cashflow=(Cashflow) iterator.next();
			if(cashflow.getSource()=="driver"&&cashflow.getSourceId()==driverId||
					cashflow.getDestination()=="driver"&&cashflow.getDestinationId()==driverId) {
				filteredListCashflow.add(cashflow);
			}
		}
		return filteredListCashflow;
	}

	@Override
	public Map<String, Object> getCashflow(Cashflow cashflow) {
		Map<String, Object> map=new LinkedHashMap<>();
		String source=cashflow.getSource();
		Long sourceId=cashflow.getSourceId();
		String destination=cashflow.getDestination();
		Long destinationId=cashflow.getDestinationId();
		Long jumlahUang=cashflow.getJumlahUang();
		Date date=cashflow.getCreatedAt();
		String token=sendAPI.getToken();
		Map<String,Object> data = new LinkedHashMap();
		data.put("id",cashflow.getId());
		data.put("date", date);
		if(source.equals("customer")) {
			Customers customer=sendAPI.getCustomerData(sourceId, token);
			data.put("from", customer.getName());
		}
		if(source.equals("restaurant")) {
			Restaurants restaurant=sendAPI.getRestaurantData(sourceId, token);
			data.put("from", restaurant.getName());
		}
		if(source.equals("driver")) {
			Drivers driver=sendAPI.getDriverData(sourceId, token);
			data.put("from", driver.getName());
		}
		if(destination.equals("customer")) {
			Customers customer=sendAPI.getCustomerData(destinationId, token);
			data.put("to", customer.getName());
		}
		if(destination.equals("restaurant")) {
			Restaurants restaurant=sendAPI.getRestaurantData(destinationId, token);
			data.put("to", restaurant.getName());
		}
		if(destination.equals("driver")) {
			Drivers driver=sendAPI.getDriverData(destinationId, token);
			data.put("to", driver.getName());
		}
		data.put("jumlah_uang",jumlahUang);
		return data;
	}

	@Override
	public ArrayList<Map<String, Object>> getCashflows(ArrayList<Cashflow> cashflows) {
		Iterator iterator=cashflows.iterator();
		ArrayList<Map<String, Object>> listCashflow=new ArrayList<Map<String, Object>>();
		while(iterator.hasNext()) {
			Cashflow cashflow=(Cashflow) iterator.next();
			listCashflow.add(this.getCashflow(cashflow));
		}
		return listCashflow;
	}
}
