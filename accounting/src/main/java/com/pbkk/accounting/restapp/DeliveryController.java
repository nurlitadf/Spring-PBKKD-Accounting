package com.pbkk.accounting.restapp;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.pbkk.accounting.aop.TokenRequired;
import com.pbkk.accounting.model.Delivery;
import com.pbkk.accounting.repository.DeliveryRepository;
import com.pbkk.accounting.service.DeliveryService;

@RestController
@RequestMapping("/delivery")

public class DeliveryController {
	@Autowired
	DeliveryRepository deliveryRepository;
	@Autowired
	DeliveryService deliveryService;
	@ResponseBody
	@RequestMapping("")
	@TokenRequired
	public ArrayList<Map<String,Object>> getAllDelivery(
			@RequestParam(required = false) String dateStart,
			@RequestParam(required = false) String dateEnd, 
			@RequestParam(required = false) Long driverId ) throws ParseException{
		ArrayList<Delivery> deliveries=(ArrayList<Delivery>) deliveryRepository.findAll();
		if(driverId!=null) {
			deliveries=deliveryService.filterByDriverId(deliveries, driverId);
		}
		if(dateStart!=null&&dateEnd!=null) {
			SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
			Date start = inFormat.parse(dateStart);
			Date end = inFormat.parse(dateEnd);
			deliveries=deliveryService.filterByDate(deliveries, start, end);
		}
		return deliveryService.getDeliveries(deliveries);
	}
	@ResponseBody
	@RequestMapping("/{id}")
	@TokenRequired
	public Map<String,Object> getDeliveryById(@PathVariable(value = "id") Long id){
		Delivery delivery=deliveryRepository.findById(id).get(); 
		return deliveryService.getDelivery(delivery);
	}
	@ResponseBody
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	@TokenRequired
	public Delivery createDelivery(@RequestParam(value="id") Long id, @RequestParam(value="driverId") Long driverId,
			@RequestParam(value="transaksiId") Long transaksiId, @RequestParam(value="ongkir") Long ongkir){
		Delivery delivery=new Delivery();
		delivery.setId(id);
		delivery.setDriverId(driverId);
		delivery.setTransaksiId(transaksiId);
		delivery.setOngkir(ongkir);
		return deliveryRepository.save(delivery);
	}
}
