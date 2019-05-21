package com.pbkk.accounting.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "detail_pembayaran")
public class DetailPembayaran extends AuditModel{
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@NotNull
	private Long transaksiId;
	@NotNull
	private String namaMenu;
	private Long hargaMenu;
	private Long jumlahMenu;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getTransaksiId() {
		return transaksiId;
	}
	public void setTransaksiId(Long transaksiId) {
		this.transaksiId = transaksiId;
	}
	public Long getJumlahMenu() {
		return jumlahMenu;
	}
	public void setJumlahMenu(Long jumlahMenu) {
		this.jumlahMenu = jumlahMenu;
	}
	public String getNamaMenu() {
		return namaMenu;
	}
	public void setNamaMenu(String namaMenu) {
		this.namaMenu = namaMenu;
	}
	public Long getHargaMenu() {
		return hargaMenu;
	}
	public void setHargaMenu(Long hargaMenu) {
		this.hargaMenu = hargaMenu;
	}
	
}
