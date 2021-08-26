package com.patanhospital.mis.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.dao.DrHeadingDAO;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.model.ChargeDRHeading;
import com.patanhospital.mis.util.KeyValue;

public class DrHeadingDAOImpl implements DrHeadingDAO {
	private static final Logger logger = LoggerFactory.getLogger(DrHeadingDAOImpl.class);
	private DatabaseAssistant dbAssistant;
	private AppMessage appMsg;

	public DrHeadingDAOImpl() {
		dbAssistant = new DatabaseAssistant();
		appMsg = new AppMessage();
	}

	@Override
	public ArrayList<KeyValue> drHeadingList() {
		ArrayList<KeyValue> drHeadinglist = null;
		String getDrHeadingQuery = "SELECT * FROM tblDrHeading";
		DBMessage dbMsg = dbAssistant.execQuery(getDrHeadingQuery);
		if (!dbMsg.getCODE().equals("0")) {
			logger.info(dbMsg.getMSG());
		} else {
			logger.info(dbMsg.getMSG());
			drHeadinglist = new ArrayList<>();
			ResultSet drResult = dbMsg.getResultSet();
			try {
				while (drResult.next()) {
					drHeadinglist.add(new KeyValue(drResult.getString("fldDrHeadingName"),
							drResult.getInt("fldDrHeadingNo") + ""));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		logger.info(drHeadinglist.toString());
		return drHeadinglist;
	}

	@Override
	public ArrayList<ChargeDRHeading> listAllDrHeading() {
		ArrayList<ChargeDRHeading> drList = new ArrayList<ChargeDRHeading>();
		String sql = "SELECT * FROM tblDrHeading";
		DBMessage dbMsg = dbAssistant.execQuery(sql);
		if (!dbMsg.getCODE().equals("0")) {
			logger.error(dbMsg.getMSG());
		} else {
			ResultSet rs = dbMsg.getResultSet();
			try {
				while (rs.next()) {
					ChargeDRHeading chargeHeading = new ChargeDRHeading();

					chargeHeading.setFldDrHeadingNo(rs.getInt("fldDrHeadingNo"));

					chargeHeading.setFldDrHeadingName(rs.getString("fldDrHeadingName"));
					chargeHeading.setFldMainGroup(rs.getString("fldDrGroup"));
					chargeHeading.setFldRate(rs.getDouble("fldRate"));
					chargeHeading.setFldMainGroup(rs.getString("fldMainGroup"));
					drList.add(chargeHeading);

					// System.out.println(rs.getString("fldDrHeadingName"));
				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return drList;

	}

	@Override
	public AppMessage addDrHeading(ChargeDRHeading addDrHeading) {
		String sql = "INSERT INTO tblDrHeading(fldDrHeadingNo,fldDrHeadingName,fldDrGroup,fldRate,fldMainGroup) VALUES('"
				+ addDrHeading.getFldDrHeadingNo() + "','" + addDrHeading.getFldDrHeadingName() + "','"
				+ addDrHeading.getFldDrGroup() + "','" + addDrHeading.getFldRate() + "','"
				+ addDrHeading.getFldMainGroup() + "')";
		appMsg = dbAssistant.execAction(sql);
		return appMsg;
	}

	@Override
	public AppMessage updateDrHeading(ChargeDRHeading updateDrHeading) {
		String sql = "UPDATE tblDrHeading SET fldDrHeadingName='" + updateDrHeading.getFldDrHeadingName()
				+ "',fldDrGroup ='" + updateDrHeading.getFldDrGroup() + "',fldRate ='" + updateDrHeading.getFldRate()
				+ "',fldMainGroup ='" + updateDrHeading.getFldMainGroup() + "' WHERE fldDrHeadingNo='"
				+ updateDrHeading.getFldDrHeadingNo() + "' ";

		appMsg = dbAssistant.execUpdate(sql);
		return appMsg;

	}

}