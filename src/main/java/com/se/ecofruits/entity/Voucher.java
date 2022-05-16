package com.se.ecofruits.entity;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Vouchers")
public class Voucher implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int voucherID;
	private double discount;
	private double minTotal;
	private String code;
	private int amount;
	private Date startDate;
	
	public Voucher(int voucherID, double discount, double minTotal, String code, int amount, Date startDate) {
		super();
		this.voucherID = voucherID;
		this.discount = discount;
		this.minTotal = minTotal;
		this.code = code;
		this.amount = amount;
		this.startDate = startDate;
	}
	public Voucher() {
		super();
	}
	public int getVoucherID() {
		return voucherID;
	}
	public void setVoucherID(int voucherID) {
		this.voucherID = voucherID;
	}
	public double getDiscount() {
		return discount;
	}
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public int getAmount() {
		return amount;
	}
	public void setAmount(int amount) {
		this.amount = amount;
	}
	public double getMinTotal() {
		return minTotal;
	}
	public void setMinTotal(double minTotal) {
		this.minTotal = minTotal;
	}
	public Date getStartDate() {
		return startDate;
	}
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	
	
	
	
}
