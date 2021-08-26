package com.patanhospital.mis.cmnmessage;

import java.sql.Connection;
import java.sql.ResultSet;

public class DBMessage extends AppMessage {

	private Connection currentConnection;
	private ResultSet resultSet;

	public Connection getCurrentConnection() {
		return currentConnection;
	}

	public void setCurrentConnection(Connection currentConnection) {
		this.currentConnection = currentConnection;
	}

	public ResultSet getResultSet() {
		return resultSet;
	}

	public void setResultSet(ResultSet resultSet) {
		this.resultSet = resultSet;
	}
	
	
}
