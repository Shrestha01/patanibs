package com.patanhospital.mis.database;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.forms.Login;

public class DatabaseConnectionManager {

    private Connection connection = null;
    private static final Logger logger = LoggerFactory.getLogger(DatabaseConnectionManager.class);

    public DBMessage getMysqlConnection() {
    	DBMessage dbmsg = new DBMessage();
        try {
            Class.forName("com.mysql.jdbc.Driver");
            //connection = DriverManager.getConnection("jdbc:mysql://192.168.10.8:3306/test", "adarsh", "password");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/patanhospital_ibs", "root", "");
            dbmsg.setCODE("0");
            dbmsg.setMSG("Connection Established");
            dbmsg.setCurrentConnection(connection);
        } catch (ClassNotFoundException ex) {
            dbmsg.setCODE("102");
            dbmsg.setCLASSPATH(this.getClass().getCanonicalName());
            dbmsg.setMSG(ex.toString());
            logger.error(ex.toString());
        } catch (SQLException ex) {
            dbmsg.setCODE("101");
            dbmsg.setCLASSPATH(this.getClass().getCanonicalName());
            dbmsg.setMSG(ex.toString());
            logger.error(ex.toString());
        }
        return dbmsg;
    }
}
