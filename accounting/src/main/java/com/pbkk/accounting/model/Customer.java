package com.pbkk.accounting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "customer")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Customer extends AuditModel {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long customerId;
	@NotNull
    @Size(max = 100)
	private String customerName;
	@NotNull
    @Size(max = 100)
	private String customerAddress;
	@NotNull
	@Size(max = 20)
	private String customerPhone;
	public Long getCustomerId() {
		return customerId;
	}
	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getCustomerAddress() {
		return customerAddress;
	}
	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	public String getCustomerPhone() {
		return customerPhone;
	}
	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	
}
