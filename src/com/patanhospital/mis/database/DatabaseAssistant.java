
package com.patanhospital.mis.database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;

public class DatabaseAssistant {
	private Logger extAppLogger = Logger.getLogger("ExternalAppLogger");
	//copythis for error log
	private static final Logger logger = Logger.getLogger(DatabaseAssistant.class);

	private DatabaseConnectionManager dbConMgr;
	private DBMessage dbMsg;
	private AppMessage appMsg;
	private Connection dbConnect = null;
	private static Statement stmt = null;

	public DatabaseAssistant() {
		dbConMgr = new DatabaseConnectionManager();
		dbConnect = dbConMgr.getMysqlConnection().getCurrentConnection();
	}

	/**
	 * For Select Statement
	 * 
	 * @param query
	 * @return DBMessage Object
	 */
	public DBMessage execQuery(String query) {
		dbMsg = new DBMessage();
		ResultSet result = null;
		try {
			stmt = dbConnect.createStatement();
			result = stmt.executeQuery(query);
			dbMsg.setCODE("0");
			dbMsg.setMSG("Query Successful!");
			dbMsg.setResultSet(result);
		} catch (SQLException ex) {
			dbMsg.setCODE("101");
			dbMsg.setMSG("Something Went Wrong While Executing Query");
			dbMsg.setCLASSPATH(this.getClass().getCanonicalName());
			logger.error(ex.toString());
			ex.printStackTrace();
		}
		return dbMsg;
	}

	/**
	 * For Insert,Delete
	 * 
	 * @param qu
	 * @return AppMessage Object
	 */
	public AppMessage execAction(String qu) {
		appMsg = new AppMessage();
		try {
			stmt = dbConnect.createStatement();
			stmt.execute(qu);
			appMsg.setCODE("0");
			appMsg.setMSG("Query Completed");
			extAppLogger.info(qu);
		} catch (SQLException ex) {
			appMsg.setCODE("101");
			appMsg.setMSG("Something Went Wrong While Executing Query");
			appMsg.setCLASSPATH(this.getClass().getCanonicalName());
			logger.error(ex.toString());
			ex.printStackTrace();
		}
		return appMsg;
	}

	/**
	 * For Update
	 * 
	 * @param qu
	 * @return AppMessage Object
	 */
	public AppMessage execUpdate(String qu) {
		appMsg = new AppMessage();
		try {
			stmt = dbConnect.createStatement();
			stmt.execute(qu);
			appMsg.setCODE("0");
			appMsg.setMSG("Query Completed");
			extAppLogger.info(qu);
		} catch (SQLException ex) {
			appMsg.setCODE("101");
			appMsg.setMSG("Something Went Wrong While Executing Query");
			appMsg.setCLASSPATH(this.getClass().getCanonicalName());
			logger.error(ex.toString());
			ex.printStackTrace();
		}
		return appMsg;
	}
}
