package com.pbkk.accounting.model;

import java.sql.Time;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transaksi")
public class Transaksi extends AuditModel{
	@Id
	private Long transaksi_id;
	
	@NotNull
	private Date date;
	
	@NotNull
	private Time time;
	
	@NotNull
	@Size(max = 20)
	private String transaksiStatus;
	
	@NotNull
	@Size(max = 20)
	private String jenis_pembayaran;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "restaurant_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Restaurant restaurant;
	
	@ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "customer_id", nullable = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Customer customer;

	public Long getTransaksi_id() {
		return transaksi_id;
	}

	public void setTransaksi_id(Long transaksi_id) {
		this.transaksi_id = transaksi_id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Time getTime() {
		return time;
	}

	public void setTime(Time time) {
		this.time = time;
	}

	public String getTransaksiStatus() {
		return transaksiStatus;
	}

	public void setTransaksiStatus(String transaksiStatus) {
		this.transaksiStatus = transaksiStatus;
	}

	public String getJenis_pembayaran() {
		return jenis_pembayaran;
	}

	public void setJenis_pembayaran(String jenis_pembayaran) {
		this.jenis_pembayaran = jenis_pembayaran;
	}

	public Restaurant getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(Restaurant restaurant) {
		this.restaurant = restaurant;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

}
