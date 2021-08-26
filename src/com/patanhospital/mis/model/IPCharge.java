package com.patanhospital.mis.model;

import java.sql.Date;

public class IPCharge {
	private int fldChargeID;
	private int fldAdmissionNo;
	private int fldChargeHeadingNo;
	private String fldChargeHeadingRemark;
	private Date fldChargeDate;
	private Short fldChargeYear;
	private Short fldChargeMonth;
	private Short fldChargeDay;
	private double fldAmount;
	private String fldUserID;
	private int receiptNo;
	public int getFldChargeID() {
		return fldChargeID;
	}
	public void setFldChargeID(int fldChargeID) {
		this.fldChargeID = fldChargeID;
	}
	public int getFldAdmissionNo() {
		return fldAdmissionNo;
	}
	public void setFldAdmissionNo(int fldAdmissionNo) {
		this.fldAdmissionNo = fldAdmissionNo;
	}
	public int getFldChargeHeadingNo() {
		return fldChargeHeadingNo;
	}
	public void setFldChargeHeadingNo(int fldChargeHeadingNo) {
		this.fldChargeHeadingNo = fldChargeHeadingNo;
	}
	public String getFldChargeHeadingRemark() {
		return fldChargeHeadingRemark;
	}
	public void setFldChargeHeadingRemark(String fldChargeHeadingRemark) {
		this.fldChargeHeadingRemark = fldChargeHeadingRemark;
	}
	public Date getFldChargeDate() {
		return fldChargeDate;
	}
	public void setFldChargeDate(Date fldChargeDate) {
		this.fldChargeDate = fldChargeDate;
	}
	public Short getFldChargeYear() {
		return fldChargeYear;
	}
	public void setFldChargeYear(Short fldChargeYear) {
		this.fldChargeYear = fldChargeYear;
	}
	public Short getFldChargeMonth() {
		return fldChargeMonth;
	}
	public void setFldChargeMonth(Short fldChargeMonth) {
		this.fldChargeMonth = fldChargeMonth;
	}
	public Short getFldChargeDay() {
		return fldChargeDay;
	}
	public void setFldChargeDay(Short fldChargeDay) {
		this.fldChargeDay = fldChargeDay;
	}
	public double getFldAmount() {
		return fldAmount;
	}
	public void setFldAmount(double fldAmount) {
		this.fldAmount = fldAmount;
	}
	public String getFldUserID() {
		return fldUserID;
	}
	public void setFldUserID(String fldUserID) {
		this.fldUserID = fldUserID;
	}
	public int getReceiptNo() {
		return receiptNo;
	}
	public void setReceiptNo(int receiptNo) {
		this.receiptNo = receiptNo;
	}
	
	
	
	

}
