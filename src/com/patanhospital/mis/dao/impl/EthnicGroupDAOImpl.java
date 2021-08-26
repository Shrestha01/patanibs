package com.patanhospital.mis.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.dao.EthnicGroupDAO;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.model.EthnicGroup;

public class EthnicGroupDAOImpl implements EthnicGroupDAO {
	private static final Logger logger = LoggerFactory.getLogger(EthnicGroupDAOImpl.class);
	private DBMessage dbMsg;
	private DatabaseAssistant dbAssistant;
	private AppMessage appMsg;
	private ResultSet result;

	public EthnicGroupDAOImpl() {
		dbAssistant = new DatabaseAssistant();
	}

	@Override
	public ArrayList<EthnicGroup> listAllEthnicGrp() {
		ArrayList<EthnicGroup> listOfEthnicGrp = new ArrayList<>();
		String selectQuery = "SELECT * FROM tblEthnicGroup";
		dbMsg = dbAssistant.execQuery(selectQuery);

		if (!dbMsg.getCODE().equals("0")) {
			logger.info(dbMsg.getMSG());
			return null;
		} else {
			result = dbMsg.getResultSet();
			try {
				while (result.next()) {
					EthnicGroup ethGrp = new EthnicGroup();
					ethGrp.setFldEthnicID(result.getShort("fldEthnicID"));
					ethGrp.setFldEthnicGroup(result.getString("fldEthnicGroup"));
					listOfEthnicGrp.add(ethGrp);
				}
				return listOfEthnicGrp;
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
	public AppMessage addNewEthnicGroup(EthnicGroup ethnicGrp) {
		String sql = "INSERT INTO tblEthnicGroup(fldEthnicID,fldEthnicGroup) VALUES("
				+ ethnicGrp.getFldEthnicID() + ",'" + ethnicGrp.getFldEthnicGroup() + "')";
		appMsg = dbAssistant.execAction(sql);

		return appMsg;
	}

	@Override
	public AppMessage updateNewEthnicGroup(EthnicGroup ethnicGrp) {
		String sql = "UPDATE tblethnicgroup SET fldEthnicGroup='" + ethnicGrp.getFldEthnicGroup()
				+ "' WHERE  fldEthnicID='" + ethnicGrp.getFldEthnicID() + "'";
		appMsg = dbAssistant.execUpdate(sql);

		return appMsg;
	}

}
