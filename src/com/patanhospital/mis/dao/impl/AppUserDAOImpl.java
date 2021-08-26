package com.patanhospital.mis.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.dao.AppUserDAO;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.model.AppUser;
import com.patanhospital.mis.util.Hashcypher;

public class AppUserDAOImpl implements AppUserDAO {
	private static final Logger logger = LoggerFactory.getLogger(AppUserDAOImpl.class);
	private AppMessage appMsg;
	private DBMessage dbMsg;
	private DatabaseAssistant dbAssistant;
	
	 public AppUserDAOImpl() {
		dbAssistant = new DatabaseAssistant();
		appMsg = new AppMessage();
	}

	@Override
	public AppMessage addAppUser(AppUser appUser) {
		// TODO Auto-generated method stub
		return appMsg;
	}

	@Override
	public ArrayList<AppUser> listAllAppUser() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public AppUser getAppUserInfo(int fldUserID) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean validateAppUser(AppUser appUser) {
		boolean sts = false;
		String authenticateSql = "SELECT fldUserID FROM tblUser WHERE fldUserName='"+ appUser.getFldUserName() + "' AND fldUserPassword='"+
				Hashcypher.hashString(appUser.getFldUserPassword()) + "' AND fldUserVisible=TRUE";
		dbMsg = dbAssistant.execQuery(authenticateSql);
		if(!dbMsg.getCODE().equals("0")) {
			logger.info(dbMsg.getMSG());
		}else {
			ResultSet result = dbMsg.getResultSet();
			try {
				if(result.next()) {
					logger.info("User Exists");
					sts = true;
				}else {
					logger.info("No User Found");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return sts;
	}

}
