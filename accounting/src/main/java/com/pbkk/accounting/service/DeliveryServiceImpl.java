package com.pbkk.accounting.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbkk.accounting.model.Delivery;
import com.pbkk.accounting.model.Drivers;
import com.pbkk.accounting.model.Transaksi;
import com.pbkk.accounting.repository.TransaksiRepository;

@Service
public class DeliveryServiceImpl implements DeliveryService {
	@Autowired
	TransaksiRepository transaksiRepository;
	@Autowired
	SendAPI sendAPI;
	@Autowired
	ReceiptService receiptService;
	@Override
	public Map<String, Object> getDelivery(Delivery delivery) {
		String token=sendAPI.getToken();
		Map<String, Object> map = new LinkedHashMap(); 
		Long idTransaksi = delivery.getTransaksiId();
		Transaksi transaksi=transaksiRepository.findById(idTransaksi).get();
		Drivers driver=sendAPI.getDriverData(delivery.getDriverId(), token);
		map.put("nama_driver", driver.getName());
		map.put("receipt", receiptService.getReceipt(transaksi,token));
		return map;
	}

	@Override
	public ArrayList<Map<String, Object>> getDeliveries(ArrayList<Delivery> deliveries) {
		ArrayList<Map<String, Object>> listDelivery=new ArrayList<Map<String, Object>>();
		Iterator iterator=deliveries.iterator();
		while(iterator.hasNext()) {
			Delivery delivery=(Delivery) iterator.next();
			listDelivery.add(this.getDelivery(delivery));
		}
		return listDelivery;
	}

	@Override
	public ArrayList<Delivery> filterByDriverId(ArrayList<Delivery> deliveries, Long driverId) {
		ArrayList<Delivery> filteredDeliveries=new ArrayList<Delivery>();
		Iterator iterator=deliveries.iterator();
		while(iterator.hasNext()) {
			Delivery delivery=(Delivery) iterator.next();
			if(delivery.getDriverId()==driverId) filteredDeliveries.add(delivery);
		}
		return filteredDeliveries;
	}

	@Override
	public ArrayList<Delivery> filterByDate(ArrayList<Delivery> deliveries, Date start, Date end) {
		ArrayList<Delivery> filteredDeliveries=new ArrayList<Delivery>();
		Iterator iterator=deliveries.iterator();
		while(iterator.hasNext()) {
			Delivery delivery=(Delivery) iterator.next();
			if(delivery.getCreatedAt().after(start)&&delivery.getCreatedAt().before(end)) filteredDeliveries.add(delivery);
		}
		return filteredDeliveries;
	}
	
}
