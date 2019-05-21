package com.pbkk.accounting.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "cashflow")
public class Cashflow extends AuditModel{
	@Id
	private Long id;
	@NotNull
	private String source;
	private Long sourceId;
	@NotNull
	private String destination;
	private Long destinationId;
	@NotNull
	private Long jumlahUang;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public Long getSourceId() {
		return sourceId;
	}
	public void setSourceId(Long sourceId) {
		this.sourceId = sourceId;
	}
	public String getDestination() {
		return destination;
	}
	public void setDestination(String destination) {
		this.destination = destination;
	}
	public Long getDestinationId() {
		return destinationId;
	}
	public void setDestinationId(Long destinationId) {
		this.destinationId = destinationId;
	}
	public Long getJumlahUang() {
		return jumlahUang;
	}
	public void setJumlahUang(Long jumlahUang) {
		this.jumlahUang = jumlahUang;
	}
}
