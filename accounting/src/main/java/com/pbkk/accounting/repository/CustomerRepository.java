package com.pbkk.accounting.repository;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.pbkk.accounting.model.Customer;

@Repository

public interface CustomerRepository extends JpaRepository<Customer, Long>{

}
