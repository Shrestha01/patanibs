package com.patanhospital.mis.server;

public class Session {
	private int userId;
	private String userName;
	private String userPwd;
	private String userRole;
	private String uerIP;
	private String userMac;
	private String userTime;
	
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserRole() {
		return userRole;
	}
	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}
	public String getUerIP() {
		return uerIP;
	}
	public void setUerIP(String uerIP) {
		this.uerIP = uerIP;
	}
	public String getUserMac() {
		return userMac;
	}
	public void setUserMac(String userMac) {
		this.userMac = userMac;
	}
	public String getUserTime() {
		return userTime;
	}
	public void setUserTime(String userTime) {
		this.userTime = userTime;
	}
	
	@Override
	public String toString() {
		return "Session [userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd + ", userRole="
				+ userRole + ", uerIP=" + uerIP + ", userMac=" + userMac + ", userTime=" + userTime + "]";
	}
	
	
	
}
