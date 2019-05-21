package com.pbkk.accounting.model;

import javax.persistence.*;

@Entity
@Table(name="delivery")
public class Delivery extends AuditModel{
	@Id
	private Long id;
	private Long driverId;
	private Long transaksiId;
	private Long ongkir;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getDriverId() {
		return driverId;
	}
	public void setDriverId(Long driverId) {
		this.driverId = driverId;
	}
	public Long getTransaksiId() {
		return transaksiId;
	}
	public void setTransaksiId(Long transaksiId) {
		this.transaksiId = transaksiId;
	}
	public Long getOngkir() {
		return ongkir;
	}
	public void setOngkir(Long ongkir) {
		this.ongkir = ongkir;
	}
	
}
