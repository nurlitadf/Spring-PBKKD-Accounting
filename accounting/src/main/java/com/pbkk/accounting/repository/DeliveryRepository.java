package com.pbkk.accounting.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pbkk.accounting.model.Delivery;

public interface DeliveryRepository extends JpaRepository<Delivery, Long>{
	public Delivery findByTransaksiId(Long transaksiId);
	public ArrayList<Delivery> findByDriverId(Long driverId);
}
