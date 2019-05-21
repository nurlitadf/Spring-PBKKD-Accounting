package com.pbkk.accounting.restapp;

import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.google.gson.Gson;
import com.pbkk.accounting.aop.TokenRequired;
import com.pbkk.accounting.model.Customer;
import com.pbkk.accounting.model.Delivery;
import com.pbkk.accounting.model.DetailPembayaran;
import com.pbkk.accounting.model.Menu;
import com.pbkk.accounting.model.Receipt;
import com.pbkk.accounting.model.Restaurant;
import com.pbkk.accounting.model.Transaksi;
import com.pbkk.accounting.repository.CustomerRepository;
import com.pbkk.accounting.repository.DeliveryRepository;
import com.pbkk.accounting.repository.DetailPembayaranRepository;
import com.pbkk.accounting.repository.MenuRepository;
import com.pbkk.accounting.repository.RestaurantRepository;
import com.pbkk.accounting.repository.TransaksiRepository;
import com.pbkk.accounting.service.ReceiptService;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
	
	@Autowired
    private TransaksiRepository transaksiRepository;
	
	@Autowired
    private DetailPembayaranRepository detailPembayaranRepository;
	
	@Autowired
	private ReceiptService receiptService;
	
	@ResponseBody
	@RequestMapping(value = "", method = RequestMethod.GET)
	@TokenRequired
	public ArrayList<Map<String,Object> > getReceiptByIdCustomerTime(
			@RequestParam(required = false) Long customerId,
			@RequestParam(required = false) Long restaurantId,
			@RequestParam(required = false) Long driverId,
			@RequestParam(required = false) String dateStart, 
			@RequestParam(required = false) String dateEnd) throws ParseException{
		ArrayList<Transaksi> listTransaksi = new ArrayList<Transaksi>();
		listTransaksi=(ArrayList<Transaksi>) transaksiRepository.findAll();
		if(customerId!=null) {
			listTransaksi=receiptService.filterByCustomerId(listTransaksi, customerId);
		}
		if(restaurantId!=null) {
			listTransaksi=receiptService.filterByRestaurantId(listTransaksi, restaurantId);
		}
		if(driverId!=null) {
			listTransaksi=receiptService.filterByDriverId(listTransaksi, driverId);
		}
		if(dateStart!=null&&dateEnd!=null) {
			SimpleDateFormat inFormat = new SimpleDateFormat("yyyy-MM-dd/HH:mm:ss");
			Date start = inFormat.parse(dateStart);
			Date end = inFormat.parse(dateEnd);
			listTransaksi=receiptService.filterByDate(listTransaksi, start, end);
		}
		ArrayList<Map<String,Object> > listReceipts = receiptService.getReceipts(listTransaksi);
		return listReceipts;
	}
	@ResponseBody
	@RequestMapping("/{id}")
	@TokenRequired
	public Map<String, Object> getReceipt(@PathVariable(value = "id")Long receiptId) {
		Optional<Transaksi> optionalTransaksi = transaksiRepository.findById(receiptId);
		Transaksi transaksi = optionalTransaksi.get();
		return receiptService.getReceipt(transaksi,null);
	}
	
	@ResponseBody
	@RequestMapping(value="/create", method = RequestMethod.POST)
	@TokenRequired
	public Receipt createReceipt(@RequestParam String receiptDetail) {
		Gson gson = new Gson();
		Receipt receipt = gson.fromJson(receiptDetail, Receipt.class);
		transaksiRepository.save(receipt.getTransaksi());
		Iterator iterator = receipt.getDetailPembayaran().iterator();
		while(iterator.hasNext()) {
			DetailPembayaran detailPembayaran=(DetailPembayaran) iterator.next();
			detailPembayaranRepository.save(detailPembayaran);
		}
		return receipt;
	}
}
