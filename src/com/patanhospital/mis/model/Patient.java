package com.patanhospital.mis.model;

/**
 * PoJo Class for Patient Information
 * Matches the columns of tblPatient Table
 * @author Sijan
 * @since 6/12/2018
 */
public class Patient {
	
	private int fldPatientId;
	private String fldPatientLastName;
	private String fldPatientName;
	private String fldEthnicGroup;
	private String fldVillageTown;
	private short fldWardNo;
	private String fldDistrict;
	private String fldBirthDate;
	private short fldYearBorn;
	private short fldMonthBorn;
	private short fldDayBorn;
	private String fldSex;
	private String fldGuardiansName;
	private String fldRelation;
	
	
	@Override
	public String toString() {
		return "Patient [fldPatientId=" + fldPatientId + ", fldPatientLastName=" + fldPatientLastName
				+ ", fldPatientName=" + fldPatientName + ", fldEthnicGroup=" + fldEthnicGroup + ", fldVillageTown="
				+ fldVillageTown + ", fldWardNo=" + fldWardNo + ", fldDistrict=" + fldDistrict + ", fldBirthDate="
				+ fldBirthDate + ", fldYearBorn=" + fldYearBorn + ", fldMonthBorn=" + fldMonthBorn + ", fldDayBorn="
				+ fldDayBorn + ", fldSex=" + fldSex + ", fldGuardiansName=" + fldGuardiansName + ", fldRelation="
				+ fldRelation + "]";
	}
	
	public int getFldPatientId() {
		return fldPatientId;
	}
	public void setFldPatientId(int fldPatientId) {
		this.fldPatientId = fldPatientId;
	}
	public String getFldPatientLastName() {
		return fldPatientLastName;
	}
	public void setFldPatientLastName(String fldPatientLastName) {
		this.fldPatientLastName = fldPatientLastName;
	}
	public String getFldPatientName() {
		return fldPatientName;
	}
	public void setFldPatientName(String fldPatientName) {
		this.fldPatientName = fldPatientName;
	}
	public String getFldEthnicGroup() {
		return fldEthnicGroup;
	}
	public void setFldEthnicGroup(String fldEthnicGroup) {
		this.fldEthnicGroup = fldEthnicGroup;
	}
	public String getFldVillageTown() {
		return fldVillageTown;
	}
	public void setFldVillageTown(String fldVillageTown) {
		this.fldVillageTown = fldVillageTown;
	}
	public short getFldWardNo() {
		return fldWardNo;
	}
	public void setFldWardNo(short fldWardNo) {
		this.fldWardNo = fldWardNo;
	}
	public String getFldDistrict() {
		return fldDistrict;
	}
	public void setFldDistrict(String fldDistrict) {
		this.fldDistrict = fldDistrict;
	}
	public String getFldBirthDate() {
		return fldBirthDate;
	}
	public void setFldBirthDate(String fldBirthDate) {
		this.fldBirthDate = fldBirthDate;
	}
	public short getFldYearBorn() {
		return fldYearBorn;
	}
	public void setFldYearBorn(short fldYearBorn) {
		this.fldYearBorn = fldYearBorn;
	}
	public short getFldMonthBorn() {
		return fldMonthBorn;
	}
	public void setFldMonthBorn(short fldMonthBorn) {
		this.fldMonthBorn = fldMonthBorn;
	}
	public short getFldDayBorn() {
		return fldDayBorn;
	}
	public void setFldDayBorn(short fldDayBorn) {
		this.fldDayBorn = fldDayBorn;
	}
	public String getFldSex() {
		return fldSex;
	}
	public void setFldSex(String fldSex) {
		this.fldSex = fldSex;
	}
	public String getFldGuardiansName() {
		return fldGuardiansName;
	}
	public void setFldGuardiansName(String fldGuardiansName) {
		this.fldGuardiansName = fldGuardiansName;
	}
	public String getFldRelation() {
		return fldRelation;
	}
	public void setFldRelation(String fldRelation) {
		this.fldRelation = fldRelation;
	}
	
	
}
