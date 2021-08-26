package com.patanhospital.mis.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.dao.WardTypeDAO;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.model.WardType;
import com.patanhospital.mis.util.KeyValue;

public class WardTypeDAOImpl implements WardTypeDAO {
	private static final Logger logger = LoggerFactory.getLogger(WardTypeDAOImpl.class);
	private DBMessage dbMsg;
	private DatabaseAssistant dbAssistant;
	private ResultSet result;
	private AppMessage appMsg;

	public WardTypeDAOImpl() {
		dbAssistant = new DatabaseAssistant();
	}

	@Override
	public void addWardType(WardType wardType) {
		String insertQuery = "INSERT INTO tblWardType (fldWardID, fldWardDesc, fldBedCount) VALUES("
				+ wardType.getFldWardID() + ",'" + wardType.getFldWardDesc() + "','" + wardType.getFldBedCount() + "')";
		appMsg = dbAssistant.execAction(insertQuery);
		//System.out.println(insertQuery);
		//System.out.println("obj>>"+wardType);
		if(!appMsg.getCODE().equals("0")) {
			System.out.println("failed");
			JOptionPane.showMessageDialog(null, "failed", "Data Insertion", JOptionPane.ERROR_MESSAGE);
			logger.error(appMsg.getMSG());
		}else {
			System.out.println("sucess");
			JOptionPane.showMessageDialog(null, "Success", "Data Insertion", JOptionPane.PLAIN_MESSAGE);
			logger.error(appMsg.getMSG());
		}
		//System.out.println(wardType.getFldWardID() + wardType.getFldWardDesc() + wardType.getFldBedCount());
	}

	@Override
	public ArrayList<KeyValue> getAllWardType() {
		ArrayList<KeyValue> keyValWardTypeArr = new ArrayList<>();
		String searchPatientQuery = "SELECT * FROM tblWardType";
		dbMsg = dbAssistant.execQuery(searchPatientQuery);
		if (!dbMsg.getCODE().equals("0")) {
			logger.error(dbMsg.getMSG());
			return null;
		} else {
			try {
				result = dbMsg.getResultSet();
				while (result.next()) {
					keyValWardTypeArr.add(new KeyValue(result.getString("fldWardDesc"), result.getString("fldWardID")));
				}
				return keyValWardTypeArr;
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

	public ArrayList<WardType> WardInfo() {

		String sql = "SELECT * FROM tblWardType";
		ArrayList<WardType> wardList = new ArrayList<WardType>();
		DBMessage db = dbAssistant.execQuery(sql);
		if (!db.getCODE().equals("0")) {

		} else {
			ResultSet rs = db.getResultSet();

			try {
				while (rs.next()) {

					//System.out.println(rs.getString("fldWardID"));

					WardType wType = new WardType();
					wType.setFldWardID(rs.getString("fldWardID"));
					wType.setFldWardDesc(rs.getString("fldWardDesc"));
					wType.setFldBedCount(rs.getShort("fldBedCount"));
					wardList.add(wType);

				}

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return wardList;

	}

}
