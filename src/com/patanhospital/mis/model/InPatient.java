package com.patanhospital.mis.model;

public class InPatient {

	private int fldRegistrationID;
	private int fldPatientId;
	private int fldAdmissionNo;
	private String fldAdmissionDate;
	private short fldAdmissionYear;
	private short fldAdmissionMonth;
	private int fldAdmissionDay;
	private short fldAge;
	private String fldWardID;
	private short fldBedNo;
	private String fldDishargeDate;
	private short fldDischargeYear;
	private short fldDischargeMonth;
	private int fldDischargeDay;
	private int fldBillNo;
	private Double fldEstimatedAmt;
	private boolean fldDischarged;

	private Patient patient;
	private WardType ward;
	
	
	
	
	public WardType getWard() {
		return ward;
	}

	public void setWard(WardType ward) {
		this.ward = ward;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	@Override
	public String toString() {
		return "InPatient [fldRegistrationID=" + fldRegistrationID + ", fldPatientId=" + fldPatientId
				+ ", fldAdmissionNo=" + fldAdmissionNo + ", fldAdmissionDate=" + fldAdmissionDate
				+ ", fldAdmissionYear=" + fldAdmissionYear + ", fldAdmissionMonth=" + fldAdmissionMonth
				+ ", fldAdmissionDay=" + fldAdmissionDay + ", fldAge=" + fldAge + ", fldWardID=" + fldWardID
				+ ", fldBedNo=" + fldBedNo + ", fldDishargeDate=" + fldDishargeDate + ", fldDischargeYear="
				+ fldDischargeYear + ", fldDischargeMonth=" + fldDischargeMonth + ", fldDischargeDay=" + fldDischargeDay
				+ ", fldBillNo=" + fldBillNo + ", fldEstimatedAmt=" + fldEstimatedAmt + ", fldDischarged="
				+ fldDischarged + "]";
	}

	public int getFldPatientId() {
		return fldPatientId;
	}

	public void setFldPatientId(int fldPatientId) {
		this.fldPatientId = fldPatientId;
	}

	public int getFldRegistrationID() {
		return fldRegistrationID;
	}

	public void setFldRegistrationID(int fldRegistrationID) {
		this.fldRegistrationID = fldRegistrationID;
	}

	public int getFldAdmissionNo() {
		return fldAdmissionNo;
	}

	public void setFldAdmissionNo(int fldAdmissionNo) {
		this.fldAdmissionNo = fldAdmissionNo;
	}

	public String getFldAdmissionDate() {
		return fldAdmissionDate;
	}

	public void setFldAdmissionDate(String fldAdmissionDate) {
		this.fldAdmissionDate = fldAdmissionDate;
	}

	public short getFldAdmissionYear() {
		return fldAdmissionYear;
	}

	public void setFldAdmissionYear(short fldAdmissionYear) {
		this.fldAdmissionYear = fldAdmissionYear;
	}

	public short getFldAdmissionMonth() {
		return fldAdmissionMonth;
	}

	public void setFldAdmissionMonth(short fldAdmissionMonth) {
		this.fldAdmissionMonth = fldAdmissionMonth;
	}

	public int getFldAdmissionDay() {
		return fldAdmissionDay;
	}

	public void setFldAdmissionDay(int i) {
		this.fldAdmissionDay = i;
	}

	public short getFldAge() {
		return fldAge;
	}

	public void setFldAge(short fldAge) {
		this.fldAge = fldAge;
	}

	public String getFldWardID() {
		return fldWardID;
	}

	public void setFldWardID(String fldWardID) {
		this.fldWardID = fldWardID;
	}

	public short getFldBedNo() {
		return fldBedNo;
	}

	public void setFldBedNo(short fldBedNo) {
		this.fldBedNo = fldBedNo;
	}

	public String getFldDishargeDate() {
		return fldDishargeDate;
	}

	public void setFldDishargeDate(String fldDishargeDate) {
		this.fldDishargeDate = fldDishargeDate;
	}

	public short getFldDischargeYear() {
		return fldDischargeYear;
	}

	public void setFldDischargeYear(short fldDischargeYear) {
		this.fldDischargeYear = fldDischargeYear;
	}

	public short getFldDischargeMonth() {
		return fldDischargeMonth;
	}

	public void setFldDischargeMonth(short fldDischargeMonth) {
		this.fldDischargeMonth = fldDischargeMonth;
	}

	public int getFldDischargeDay() {
		return fldDischargeDay;
	}

	public void setFldDischargeDay(int i) {
		this.fldDischargeDay = i;
	}

	public int getFldBillNo() {
		return fldBillNo;
	}

	public void setFldBillNo(int fldBillNo) {
		this.fldBillNo = fldBillNo;
	}

	public Double getFldEstimatedAmt() {
		return fldEstimatedAmt;
	}

	public void setFldEstimatedAmt(Double fldEstimatedAmt) {
		this.fldEstimatedAmt = fldEstimatedAmt;
	}

	public boolean isFldDischarged() {
		return fldDischarged;
	}

	public void setFldDischarged(boolean fldDischarged) {
		this.fldDischarged = fldDischarged;
	}

}
