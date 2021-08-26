package com.patanhospital.mis.server;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.model.AppUser;
import com.patanhospital.mis.util.Hashcypher;

public class UserSessionImpl {
	private static final Logger logger = LoggerFactory.getLogger(UserSessionImpl.class);
	private DBMessage dbMsg;
	private DatabaseAssistant dbAssistant;

	public UserSessionImpl() {
		dbAssistant = new DatabaseAssistant();
	}
	
	public Session setUserSession(AppUser appUser) {
		Session session = null;
		String authenticateSql = "SELECT fldUserID, fldUserName, fldUserPassword, fldRoleName FROM tblUser, tblRole WHERE fldRoleID=fldUserRole AND fldUserName='"+ appUser.getFldUserName() + "' AND fldUserPassword='"+
				Hashcypher.hashString(appUser.getFldUserPassword()) + "' AND fldUserVisible=TRUE";
		dbMsg = dbAssistant.execQuery(authenticateSql);
		if(!dbMsg.getCODE().equals("0")) {
			logger.info(dbMsg.getMSG());
		}else {
			ResultSet result = dbMsg.getResultSet();
			try {
				while(result.next()) {
					session = new Session();
					session.setUserId(result.getInt("fldUserID"));
					session.setUserName(result.getString("fldUserName"));
					session.setUserPwd(result.getString("fldUserPassword"));
					session.setUserRole(result.getString("fldRoleName"));
				}
				try {
					session.setUerIP(InetAddress.getLocalHost().toString());
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				session.setUserMac("xxxxxx");
				session.setUserTime(LocalDateTime.now().toString());
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return session;
	}
}
