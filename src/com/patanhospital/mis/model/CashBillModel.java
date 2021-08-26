package com.patanhospital.mis.model;

public class CashBillModel {

	private int paymentNo;
	private String paymentHeading;
	private String headingGrp;
	private String remark;
	private double amount;
	
	public int getPaymentNo() {
		return paymentNo;
	}
	public void setPaymentNo(int paymentNo) {
		this.paymentNo = paymentNo;
	}
	public String getPaymentHeading() {
		return paymentHeading;
	}
	public void setPaymentHeading(String paymentHeading) {
		this.paymentHeading = paymentHeading;
	}
	public String getHeadingGrp() {
		return headingGrp;
	}
	public void setHeadingGrp(String headingGrp) {
		this.headingGrp = headingGrp;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	
	
	
}
