package com.pbkk.accounting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@Table(name = "customer")
public class Customer extends AuditModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int customer_id;
	@NotNull
    @Size(max = 100)
	private String customer_name;
	@NotNull
    @Size(max = 100)
	private String customer_address;
	@NotNull
	@Size(max = 20)
	private String customer_phone;
	
	
	public int getCustomer_id() {
		return customer_id;
	}
	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}
	public String getCustomer_name() {
		return customer_name;
	}
	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}
	public String getCustomer_address() {
		return customer_address;
	}
	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}
	public String getCustomer_phone() {
		return customer_phone;
	}
	public void setCustomer_phone(String customer_phone) {
		this.customer_phone = customer_phone;
	}
	
	
}
