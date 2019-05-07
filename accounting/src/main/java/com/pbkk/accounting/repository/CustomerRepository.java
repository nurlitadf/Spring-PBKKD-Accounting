package com.pbkk.accounting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pbkk.accounting.model.Customer;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long>{
}
