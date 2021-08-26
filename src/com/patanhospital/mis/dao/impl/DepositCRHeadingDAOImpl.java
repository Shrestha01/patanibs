package com.patanhospital.mis.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.dao.DepositCRHeadingDAO;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.model.DepositCRHeading;
import com.patanhospital.mis.util.KeyValue;

public class DepositCRHeadingDAOImpl implements DepositCRHeadingDAO {
	private static final Logger logger = LoggerFactory.getLogger(DepositCRHeadingDAOImpl.class);
	private DatabaseAssistant dbAssistant;
	
	public  DepositCRHeadingDAOImpl() {
		dbAssistant = new DatabaseAssistant();
	}
	
	@Override
	public ArrayList<KeyValue> listCrHeading() {
		ArrayList<KeyValue> crHeadinglist = null;
		String sql = "SELECT * FROM tblCrHeading";
		DBMessage dbMsg = dbAssistant.execQuery(sql);
		if (!dbMsg.getCODE().equals("0")) {
			logger.info(dbMsg.getMSG());
		} else {
			logger.info(dbMsg.getMSG());
			crHeadinglist = new ArrayList<>();
			ResultSet result = dbMsg.getResultSet();
			try {
				while (result.next()) {
					crHeadinglist.add(
							new KeyValue(result.getString("fldCrHeadingName"), result.getInt("fldCrHeadingNo") + ""));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info(crHeadinglist.toString());
		return crHeadinglist;
	}

	@Override
	public void addCRHeading(DepositCRHeading deposit) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeCRHeading(int fldCrHeadingNo) {
		// TODO Auto-generated method stub

	}

	@Override
	public ArrayList<DepositCRHeading> listAllCrHeading() {
		
		
		
		
		return null;
	}

}
