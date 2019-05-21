package com.pbkk.accounting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import com.pbkk.accounting.model.Transaksi;

public interface ReceiptService {
	public Map<String,Object> getReceipt(Transaksi transaksi, String token);
	public ArrayList<Map<String,Object> > getReceipts(ArrayList<Transaksi> listTransaksi);
	public ArrayList<Map<String,Object> > getAllReceipts();
	public ArrayList<Transaksi> filterByCustomerId(ArrayList<Transaksi> listTransaksi, Long customerId);
	public ArrayList<Transaksi> filterByRestaurantId(ArrayList<Transaksi> listTransaksi, Long restaurantId);
	public ArrayList<Transaksi> filterByDriverId(ArrayList<Transaksi> listTransaksi, Long driverId);
	public ArrayList<Transaksi> filterByDate(ArrayList<Transaksi> listTransaksi, Date startDate, Date endDate);
	
	
}
