package com.pbkk.accounting.repository;

import java.util.ArrayList;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbkk.accounting.model.Cashflow;

@Repository

public interface CashflowRepository extends JpaRepository<Cashflow, Long>{
	public ArrayList<Cashflow> findBySource(String source);
	public ArrayList<Cashflow> findByDestination(String destination);
	public ArrayList<Cashflow> findBySourceAndSourceId(String source, Long sourceId);
	public ArrayList<Cashflow> findByDestinationAndDestinationId(String destination,Long destinationId);
}
