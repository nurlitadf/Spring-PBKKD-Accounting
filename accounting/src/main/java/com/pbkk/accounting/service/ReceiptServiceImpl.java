package com.pbkk.accounting.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pbkk.accounting.model.Customer;
import com.pbkk.accounting.model.Customers;
import com.pbkk.accounting.model.Delivery;
import com.pbkk.accounting.model.DetailPembayaran;
import com.pbkk.accounting.model.Menu;
import com.pbkk.accounting.model.Restaurant;
import com.pbkk.accounting.model.Restaurants;
import com.pbkk.accounting.model.Transaksi;
import com.pbkk.accounting.repository.CustomerRepository;
import com.pbkk.accounting.repository.DeliveryRepository;
import com.pbkk.accounting.repository.DetailPembayaranRepository;
import com.pbkk.accounting.repository.MenuRepository;
import com.pbkk.accounting.repository.RestaurantRepository;
import com.pbkk.accounting.repository.TransaksiRepository;

@Service
public class ReceiptServiceImpl implements ReceiptService {
	@Autowired
    private TransaksiRepository transaksiRepository;
	
	@Autowired
    private DetailPembayaranRepository detailPembayaranRepository;
	
	@Autowired
    private DeliveryRepository deliveryRepository;
	
	@Autowired
    private SendAPI sendAPI;
	@Override
	public Map<String,Object> getReceipt(Transaksi transaksi, String token) {
		Map<String, Object> map = new LinkedHashMap<>();
		Long restaurant_id = transaksi.getRestaurantId();
		Long customer_id = transaksi.getCustomerId();
		if(token==null)token=sendAPI.getToken();
		Customers customer=sendAPI.getCustomerData(customer_id,token);
		Restaurants restaurant=sendAPI.getRestaurantData(restaurant_id,token);
		Date date=transaksi.getCreatedAt();
		String restaurant_name = restaurant.getName();
		String customer_name = customer.getName();
		Long id_transaksi = transaksi.getTransaksiId();
		List<DetailPembayaran> listDetailPembayaran = detailPembayaranRepository.findByTransaksiId(id_transaksi);
		ArrayList<Map<String,Object> > listNamaHarga = new ArrayList<Map<String,Object> >();
		Delivery delivery=deliveryRepository.findByTransaksiId(transaksi.getTransaksiId());
		
		Iterator iterator = listDetailPembayaran.iterator();
		int total_cost=0;
		while(iterator.hasNext()) {
			DetailPembayaran detail=(DetailPembayaran) iterator.next();
			Map<String, Object> mep = new LinkedHashMap<>();
			mep.put("nama menu", detail.getNamaMenu());
			mep.put("jumlah_beli", detail.getJumlahMenu());
			mep.put("harga_satuan",detail.getHargaMenu());
			Long total=detail.getJumlahMenu()*detail.getHargaMenu();
			if(transaksi.getKodePromo()!=null) {
				mep.put("kode_promo", transaksi.getKodePromo());
				mep.put("potongan", transaksi.getPotongan());
				total=total-transaksi.getPotongan();
			}
			mep.put("harga_total", total);
			
			total_cost=total_cost+(int)(detail.getJumlahMenu()*detail.getHargaMenu());
			listNamaHarga.add(mep);
	    }
		total_cost+=delivery.getOngkir();
		map.put("date",date);
		map.put("nama_restoran",restaurant_name);
		map.put("nama_pelanggan",customer_name);
		map.put("id_transaksi",id_transaksi);
		map.put("detail_pembelian",listNamaHarga);
		map.put("ongkos_kirim", delivery.getOngkir());
		map.put("total_harga",total_cost);
		return map;
	}
	@Override
	public ArrayList<Map<String, Object>> getAllReceipts() {
		ArrayList<Map<String, Object>> listReceipts=new ArrayList<Map<String, Object>>();
		ArrayList<Transaksi> transaksi=(ArrayList<Transaksi>) transaksiRepository.findAll();
		Iterator iterator = transaksi.iterator();
		String token=sendAPI.getToken();
		while(iterator.hasNext()) {
			listReceipts.add(this.getReceipt((Transaksi)iterator.next(),token));
		}
		return listReceipts;
	}
	@Override
	public ArrayList<Transaksi> filterByCustomerId(ArrayList<Transaksi> listTransaksi, Long customerId) {
		ArrayList<Transaksi> filteredListTransaksi=new ArrayList<Transaksi>();
		Iterator iterator= listTransaksi.iterator();
		while(iterator.hasNext()) {
			Transaksi transaksi=(Transaksi) iterator.next();
			if(transaksi.getCustomerId()==customerId) {
				filteredListTransaksi.add(transaksi);
			}
		}
		return filteredListTransaksi;
	}
	@Override
	public ArrayList<Transaksi> filterByRestaurantId(ArrayList<Transaksi> listTransaksi, Long restaurantId) {
		ArrayList<Transaksi> filteredListTransaksi=new ArrayList<Transaksi>();
		Iterator iterator= listTransaksi.iterator();
		while(iterator.hasNext()) {
			Transaksi transaksi=(Transaksi) iterator.next();
			if(transaksi.getRestaurantId()==restaurantId) {
				filteredListTransaksi.add(transaksi);
			}
		}
		return filteredListTransaksi;
	}
	@Override
	public ArrayList<Transaksi> filterByDriverId(ArrayList<Transaksi> listTransaksi, Long driverId) {
		ArrayList<Transaksi> filteredListTransaksi=new ArrayList<Transaksi>();
		ArrayList<Delivery> deliveries=deliveryRepository.findByDriverId(driverId);
		ArrayList<Long> idTransaksi=new ArrayList<Long>();
		Iterator iterator= deliveries.iterator();
		while(iterator.hasNext()) {
			Delivery delivery=(Delivery) iterator.next();
			idTransaksi.add(delivery.getTransaksiId());
		}
		iterator=listTransaksi.iterator();
		while(iterator.hasNext()) {
			Transaksi transaksi=(Transaksi) iterator.next();
			if(idTransaksi.contains(transaksi.getTransaksiId())) {
				filteredListTransaksi.add(transaksi);
			}
		}
		return filteredListTransaksi;
	}
	@Override
	public ArrayList<Transaksi> filterByDate(ArrayList<Transaksi> listTransaksi, Date startDate, Date endDate) {
		ArrayList<Transaksi> filteredListTransaksi=new ArrayList<Transaksi>();
		Iterator iterator=listTransaksi.iterator();
		while(iterator.hasNext()) {
			Transaksi transaksi=(Transaksi) iterator.next();
			Date transaksiDate=transaksi.getCreatedAt();
			if((transaksiDate.after(startDate)||transaksiDate.equals(startDate))&&
					(transaksiDate.before(endDate)||transaksiDate.equals(endDate))) {
				filteredListTransaksi.add(transaksi);
			}
		}
		return filteredListTransaksi;
	}
	@Override
	public ArrayList<Map<String, Object>> getReceipts(ArrayList<Transaksi> listTransaksi) {
		ArrayList<Map<String, Object>> listReceipts=new ArrayList<Map<String, Object>>();
		Iterator iterator=listTransaksi.iterator();
		String token=sendAPI.getToken();
		while(iterator.hasNext()) {
			Transaksi transaksi=(Transaksi) iterator.next();
			listReceipts.add(this.getReceipt(transaksi,token));
		}
		return listReceipts;
	}
	

}
