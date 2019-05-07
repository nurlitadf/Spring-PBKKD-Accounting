package com.pbkk.accounting.model;

import java.sql.Time;
import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "transaksi")
public class Receipt extends AuditModel{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int transaksi_id;
	
	@NotNull
	private Date date;
	
	@NotNull
	private Time time;
	
	@NotNull
	@Size(max = 20)
	private String transaksi_status;
	
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

}
