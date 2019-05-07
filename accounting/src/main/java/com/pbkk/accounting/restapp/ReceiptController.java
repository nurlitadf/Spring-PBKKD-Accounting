package com.pbkk.accounting.restapp;

import java.sql.Time;
import java.util.Date;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pbkk.accounting.model.Customer;
import com.pbkk.accounting.model.DetailPembayaran;
import com.pbkk.accounting.model.Restaurant;
import com.pbkk.accounting.model.Transaksi;
import com.pbkk.accounting.repository.DetailPembayaranRepository;
import com.pbkk.accounting.repository.TransaksiRepository;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
	@Autowired
    private TransaksiRepository transaksiRepository;
	@Autowired
    private DetailPembayaranRepository detailPembayaranRepository;
	@ResponseBody
	@RequestMapping("/{id}")
	public Map<String, Object> getReceipt(@PathVariable(value = "id")Long receiptId) {
		Optional<Transaksi> optionalTransaksi = transaksiRepository.findById(receiptId);
		Transaksi transaksi = optionalTransaksi.get();
		Map<String, Object> map = new LinkedHashMap<>();
		Time time = transaksi.getTime();
		Date date = transaksi.getDate();
		Restaurant restaurant = transaksi.getRestaurant();
		String restaurant_name = restaurant.getRestaurant_name();
		Customer customer = transaksi.getCustomer();
		String customer_name = customer.getCustomer_name();
		Long id_transaksi = transaksi.getTransaksi_id();
		String customer_address = customer.getCustomer_address();
		List<DetailPembayaran> listDetailPembayaran = detailPembayaranRepository.findByTransaksiId(id_transaksi);
		String jenis_pembayaran = transaksi.getJenis_pembayaran();
		map.put("time",time);
		map.put("date",date);
		map.put("nama restoran",restaurant_name);
		map.put("nama pelanggan",customer_name);
		map.put("id transaksi",id_transaksi);
		map.put("alamat_pelanggan",customer_address);
		map.put("detail harga",listDetailPembayaran);
		map.put("jenis pembayaran", jenis_pembayaran);
		return map;
	}
//	@ResponseBody
//	@RequestMapping("")
//	public Map<String, Object> getAllReceipts(Pageable pageable) {
//		Map<String, Object> map = new LinkedHashMap<>();
//		Page<Transaksi> = transaksiRepository.findAll(pageable);
//    }
}
