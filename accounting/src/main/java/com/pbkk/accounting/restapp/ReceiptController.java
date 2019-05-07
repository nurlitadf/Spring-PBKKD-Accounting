package com.pbkk.accounting.restapp;

import java.sql.Time;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.pbkk.accounting.model.Customer;
import com.pbkk.accounting.model.DetailPembayaran;
import com.pbkk.accounting.model.Menu;
import com.pbkk.accounting.model.Restaurant;
import com.pbkk.accounting.model.Transaksi;
import com.pbkk.accounting.repository.DetailPembayaranRepository;
import com.pbkk.accounting.repository.MenuRepository;
import com.pbkk.accounting.repository.TransaksiRepository;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
	
	@Autowired
    private TransaksiRepository transaksiRepository;
	
	@Autowired
    private DetailPembayaranRepository detailPembayaranRepository;
	
	@Autowired
    private MenuRepository menuRepository;
	
	public Map<String,Object> getReceipt(Transaksi transaksi) {
		Map<String, Object> map = new LinkedHashMap<>();
		Restaurant restaurant = transaksi.getRestaurant();
		Customer customer = transaksi.getCustomer();
		
		Time time = transaksi.getTime();
		Date date = transaksi.getDate();
		String restaurant_name = restaurant.getRestaurant_name();
		String customer_name = customer.getCustomer_name();
		Long id_transaksi = transaksi.getTransaksi_id();
		String customer_address = customer.getCustomer_address();
		List<DetailPembayaran> listDetailPembayaran = detailPembayaranRepository.findByTransaksiId(id_transaksi);
		ArrayList<Map<String,Object> > listNamaHarga = new ArrayList<Map<String,Object> >();
		String jenis_pembayaran = transaksi.getJenis_pembayaran();
		
		Iterator iterator = listDetailPembayaran.iterator();
		int total_cost=0;
		while(iterator.hasNext()) {
			DetailPembayaran detail=(DetailPembayaran) iterator.next();
			Long id_menu = detail.getMenuId();
			Map<String, Object> mep = new LinkedHashMap<>();
			Optional<Menu> optionalMenu = menuRepository.findById(id_menu);
			Menu menu = optionalMenu.get();
			mep.put("nama menu", menu.getMenu_name());
			mep.put("harga menu",menu.getMenu_price());
			total_cost=total_cost+menu.getMenu_price();
			listNamaHarga.add(mep);
	    }
		map.put("time",time);
		map.put("date",date);
		map.put("nama restoran",restaurant_name);
		map.put("nama pelanggan",customer_name);
		map.put("id transaksi",id_transaksi);
		map.put("alamat pelanggan",customer_address);
		map.put("detail harga",listNamaHarga);
		map.put("total harga",total_cost);
		map.put("jenis pembayaran", jenis_pembayaran);
		return map;
	}
	
	@ResponseBody
	@RequestMapping("")
	public ArrayList<Map<String,Object> > getAllReceipts() {
		
		ArrayList<Map<String,Object> > listReceipts = new ArrayList<Map<String,Object> >();
		List<Transaksi> listTransaksi = transaksiRepository.findAll();
		Iterator iterators = listTransaksi.iterator();
		
		while(iterators.hasNext()) {
			
			Transaksi transaksi = (Transaksi) iterators.next();
			
			Map<String, Object> map = getReceipt(transaksi);
			
			listReceipts.add(map);
		}
		return listReceipts;
    }
	public ArrayList<Map<String,Object> > getReceiptsByStatus(@RequestParam int status){
		
		ArrayList<Map<String,Object> > listReceipts = new ArrayList<Map<String,Object> >();
		List<Transaksi> listTransaksi = transaksiRepository.findByTransaksiStatus(status);
		Iterator iterators = listTransaksi.iterator();
		while(iterators.hasNext()) {
			
			Transaksi transaksi = (Transaksi) iterators.next();
			
			Map<String, Object> map = getReceipt(transaksi);
			
			listReceipts.add(map);
		}
		return listReceipts;
	}
	
	@ResponseBody
	@RequestMapping("/{id}")
	
	public Map<String, Object> getReceiptById(@PathVariable(value = "id")Long receiptId) {
		Optional<Transaksi> optionalTransaksi = transaksiRepository.findById(receiptId);
		Transaksi transaksi = optionalTransaksi.get();
		
		Map<String, Object> map = getReceipt(transaksi);
		return map;
	}
}
