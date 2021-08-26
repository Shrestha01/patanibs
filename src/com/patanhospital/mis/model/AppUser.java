package com.patanhospital.mis.model;

public class AppUser {
	private int fldUserID;
	private String fldUserName;
	private String fldUserPassword;
	private int fldUserRole;
	private boolean fldUserVisible;
	
	public int getFldUserID() {
		return fldUserID;
	}
	public void setFldUserID(int fldUserID) {
		this.fldUserID = fldUserID;
	}
	public String getFldUserName() {
		return fldUserName;
	}
	public void setFldUserName(String fldUserName) {
		this.fldUserName = fldUserName;
	}
	public String getFldUserPassword() {
		return fldUserPassword;
	}
	public void setFldUserPassword(String fldUserPassword) {
		this.fldUserPassword = fldUserPassword;
	}
	public int getFldUserRole() {
		return fldUserRole;
	}
	public void setFldUserRole(int fldUserRole) {
		this.fldUserRole = fldUserRole;
	}
	public boolean isFldUserVisible() {
		return fldUserVisible;
	}
	public void setFldUserVisible(boolean fldUserVisible) {
		this.fldUserVisible = fldUserVisible;
	}
	
	
}
