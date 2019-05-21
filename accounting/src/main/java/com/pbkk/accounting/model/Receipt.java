package com.pbkk.accounting.model;

import java.util.ArrayList;

public class Receipt {
	private Transaksi transaksi;
	private ArrayList<DetailPembayaran> detailPembayaran;
	private Customer customer;
	public Transaksi getTransaksi() {
		return transaksi;
	}
	public void setTransaksi(Transaksi transaksi) {
		this.transaksi = transaksi;
	}
	public ArrayList<DetailPembayaran> getDetailPembayaran() {
		return detailPembayaran;
	}
	public void setDetailPembayaran(ArrayList<DetailPembayaran> detailPembayaran) {
		this.detailPembayaran = detailPembayaran;
	}
	
}
