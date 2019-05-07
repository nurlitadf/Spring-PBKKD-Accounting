package com.pbkk.accounting.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbkk.accounting.model.DetailPembayaran;

@Repository
public interface DetailPembayaranRepository extends JpaRepository<DetailPembayaran, Long>{
	List<DetailPembayaran> findByTransaksiId(Long transaksi_id);
}
