package com.pbkk.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbkk.accounting.model.Transaksi;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long>{
	List<Transaksi> findByTransaksiStatus(String status);
	List<Transaksi> findByCustomerId(Long customerId);
	List<Transaksi> findByTransaksiStatusAndCustomerId(String status, Long customerId);
	List<Transaksi> findByCustomerIdAndRestaurantId(String status, Long customerId);
}
