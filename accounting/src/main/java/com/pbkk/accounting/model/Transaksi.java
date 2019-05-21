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
	private Long transaksiId;
	
	@NotNull
	@Size(max = 20)
	private String transaksiStatus;
	
	private Long restaurantId;
	
	private Long customerId;
	
	private String kodePromo;
	
	private Long potongan;

	public String getKodePromo() {
		return kodePromo;
	}

	public void setKodePromo(String kodePromo) {
		this.kodePromo = kodePromo;
	}

	public Long getPotongan() {
		return potongan;
	}

	public void setPotongan(Long potongan) {
		this.potongan = potongan;
	}

	public Long getTransaksiId() {
		return transaksiId;
	}

	public void setTransaksiId(Long transaksiId) {
		this.transaksiId = transaksiId;
	}

	public String getTransaksiStatus() {
		return transaksiStatus;
	}

	public void setTransaksiStatus(String transaksiStatus) {
		this.transaksiStatus = transaksiStatus;
	}

	public Long getRestaurantId() {
		return restaurantId;
	}

	public void setRestaurantId(Long restaurantId) {
		this.restaurantId = restaurantId;
	}

	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	


}
