package com.pbkk.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbkk.accounting.model.Transaksi;

@Repository
public interface TransaksiRepository extends JpaRepository<Transaksi, Long>{
	List<Transaksi> findByTransaksiStatus(int transaksi_Status);
}
