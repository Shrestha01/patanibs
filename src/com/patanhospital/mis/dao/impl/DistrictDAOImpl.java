package com.patanhospital.mis.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.dao.DistrictDAO;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.model.District;

public class DistrictDAOImpl implements DistrictDAO {
	private static final Logger logger = LoggerFactory.getLogger(DistrictDAOImpl.class);
	private DBMessage dbMsg;
	private DatabaseAssistant dbAssistant;
	private AppMessage appMsg;
	private ResultSet result;

	public DistrictDAOImpl() {
		dbAssistant = new DatabaseAssistant();
	}

	@Override
	public ArrayList<District> listAllDistrictName() {
		ArrayList<District> listOfDistrict = new ArrayList<>();
		String selectQuery = "SELECT * FROM tblDistrict";
		dbMsg = dbAssistant.execQuery(selectQuery);

		if (!dbMsg.getCODE().equals("0")) {
			logger.info(dbMsg.getMSG());
			return null;
		} else {
			result = dbMsg.getResultSet();
			try {
				while (result.next()) {
					District district = new District();
					
					district.setFldDistrictCode(result.getShort("fldDistrictCode"));
					district.setFldDistrictName(result.getString("fldDistrictName"));
					listOfDistrict.add(district);
				}
				return listOfDistrict;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			} finally {
				try {
					result.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public AppMessage addNewDistrict(District district) {
		String insertQuery = "INSERT INTO tblDistrict (fldDistrictCode, fldDistrictName) VALUES("
				+ district.getFldDistrictCode() + ",'" + district.getFldDistrictName() + "')";
		appMsg = dbAssistant.execAction(insertQuery);
		return appMsg;
	}

	@Override
	public AppMessage updateDistrict(District updateDisct) {

		String sql = "UPDATE tblDistrict SET fldDistrictName='" + updateDisct.getFldDistrictName()
				+ "' WHERE fldDistrictCode='" + updateDisct.getFldDistrictCode() + "' ";

		appMsg = dbAssistant.execUpdate(sql);
		return appMsg;
	}

}
