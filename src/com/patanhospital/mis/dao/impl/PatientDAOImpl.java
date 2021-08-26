package com.patanhospital.mis.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.cmnmessage.DBMessage;
import com.patanhospital.mis.dao.PatientDAO;
import com.patanhospital.mis.database.DatabaseAssistant;
import com.patanhospital.mis.database.DatabaseConnectionManager;
import com.patanhospital.mis.model.InPatient;
import com.patanhospital.mis.model.Patient;
import com.patanhospital.mis.model.WardType;

public class PatientDAOImpl implements PatientDAO {
	private static final Logger logger = LoggerFactory.getLogger(PatientDAOImpl.class);
	private AppMessage appMsg;
	private DBMessage dbMsg;
	private DatabaseAssistant dbAssistant;
	private Statement stmt;

	public PatientDAOImpl() {
		dbAssistant = new DatabaseAssistant();
		appMsg = new AppMessage();
	}

	/**
	 * @author Sijan
	 * @detail Function to add new Patient
	 * @param patient
	 * @return AppMessage
	 */
	@Override
	public AppMessage addPatient(Patient patient) {
		// TODO Check Existing Patient With Same Patient ID
		String addPatientQuery = "INSERT INTO tblPatient (fldPatientID, fldLastname, fldName, fldEthnicGroup, fldVillageTown"
				+ ", fldWardNo, fldDistrict, fldBirthDate, fldYearBorn, fldMonthBorn, fldDayBorn, fldSex, fldGuardiansName, fldRelation)"
				+ "VALUES (" + patient.getFldPatientId() + ", '" + patient.getFldPatientLastName() + "', '"
				+ patient.getFldPatientName() + "', '" + patient.getFldEthnicGroup() + "', '"
				+ patient.getFldVillageTown() + "', " + patient.getFldWardNo() + ", '" + patient.getFldDistrict()
				+ "', '" + patient.getFldBirthDate() + "', " + patient.getFldYearBorn() + ", "
				+ patient.getFldMonthBorn() + ", " + patient.getFldDayBorn() + ", '" + patient.getFldSex() + "', '"
				+ patient.getFldGuardiansName() + "', '" + patient.getFldRelation() + "')";
		appMsg = dbAssistant.execAction(addPatientQuery);
		return appMsg;
	}

	/**
	 * @author Sijan
	 * @detail Function to add new InPatient
	 * @param inpatient
	 * @return int
	 */
	@Override
	public int addInPatient(InPatient inPatient) {
		logger.info(inPatient.toString());
		int returnVal = -1;
		ResultSet rs = null;
		Connection con = null;
		try {
			con = new DatabaseConnectionManager().getMysqlConnection().getCurrentConnection();
			String insertPatientQuery = "INSERT INTO tblInPatient(fldPatientID, fldAdmissionDate, fldAdmissionYear, fldAdmissionMonth, fldAdmissionDay, fldAge, fldWardID, fldBedNo, fldEstimatedAmt, fldDischarged)"
					+ "VALUES(" + inPatient.getFldPatientId() + ",'" + inPatient.getFldAdmissionDate() + "',"
					+ inPatient.getFldAdmissionYear() + "," + inPatient.getFldAdmissionMonth() + ","
					+ inPatient.getFldAdmissionDay() + "," + inPatient.getFldAge() + ",'" + inPatient.getFldWardID()
					+ "'," + inPatient.getFldBedNo() + "," + inPatient.getFldEstimatedAmt() + ","
					+ inPatient.isFldDischarged() + ")";
			logger.info(insertPatientQuery);
			stmt = con.createStatement();
			stmt.executeUpdate(insertPatientQuery);

			rs = stmt.getGeneratedKeys();

			if (rs.next()) {
				returnVal = rs.getInt(1);
				logger.info(returnVal + "");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				rs.close();
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		return returnVal;
	}

	/**
	 * @author Sijan
	 * @detail Function to search existing Patient
	 * @param fldPatientID
	 * @return Patient
	 */
	@Override
	public Patient searchPatient(int fldPatientID) {
		String searchPatientQuery = "SELECT * FROM tblPatient WHERE fldPatientID = " + fldPatientID;
		dbMsg = dbAssistant.execQuery(searchPatientQuery);
		if (!dbMsg.getCODE().equals("0")) {
			logger.info(dbMsg.getMSG());
			return null;
		} else {
			try {
				ResultSet result = dbMsg.getResultSet();
				if (result.next()) {
					Patient patient_new = new Patient();
					patient_new.setFldPatientId(result.getInt("fldPatientID"));
					patient_new.setFldPatientLastName(result.getString("fldLastname"));
					patient_new.setFldPatientName(result.getString("fldName"));
					patient_new.setFldEthnicGroup(result.getString("fldEthnicGroup"));
					patient_new.setFldVillageTown(result.getString("fldVillageTown"));
					patient_new.setFldWardNo(result.getShort("fldWardNo"));
					patient_new.setFldDistrict(result.getString("fldDistrict"));
					patient_new.setFldBirthDate(result.getString("fldBirthDate").substring(0, 10));
					patient_new.setFldYearBorn(result.getShort("fldYearBorn"));
					patient_new.setFldMonthBorn(result.getShort("fldMonthBorn"));
					patient_new.setFldDayBorn(result.getShort("fldDayBorn"));
					patient_new.setFldSex(result.getString("fldSex"));
					patient_new.setFldGuardiansName(result.getString("fldGuardiansName"));
					patient_new.setFldRelation(result.getString("fldRelation"));
					return patient_new;
				} else {
					logger.info("No Record Found");
					return null;
				}
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
		}
	}

	/**
	 * @author Sijan
	 * @detail Function to search existing list of admitted InPatient
	 * @param fldPatientID
	 * @return ArrayList<InPatient>
	 */
	@Override
	public ArrayList<InPatient> searchInPatient(int fldPatientID) {
		ArrayList<InPatient> inpatient_list = new ArrayList<>();
		String searchPatientQuery = "SELECT * FROM tblInPatient WHERE fldPatientID = " + fldPatientID;
		dbMsg = dbAssistant.execQuery(searchPatientQuery);
		if (!dbMsg.getCODE().equals("0")) {
			return null;
		} else {
			try {
				ResultSet result = dbMsg.getResultSet();
				while (result.next()) {
					InPatient inPatient_new = new InPatient();
					inPatient_new.setFldPatientId(result.getInt("fldPatientId"));
					inPatient_new.setFldRegistrationID(result.getInt("fldRegistrationId"));
					inPatient_new.setFldAdmissionNo(result.getInt("fldAdmissionNo"));
					inPatient_new.setFldAdmissionDate(result.getString("fldAdmissionDate").substring(0, 10));
					inPatient_new.setFldAdmissionYear(result.getShort("fldAdmissionYear"));
					inPatient_new.setFldAdmissionMonth(result.getShort("fldAdmissionMonth"));
					inPatient_new.setFldAdmissionDay(result.getInt("fldAdmissionDay"));
					inPatient_new.setFldAge(result.getShort("fldAge"));
					inPatient_new.setFldWardID(result.getString("fldWardID")); // from tblWard
					inPatient_new.setFldBedNo(result.getShort("fldBedNo"));
					inPatient_new.setFldDishargeDate(result.getString("fldDishargeDate"));
					inPatient_new.setFldDischargeYear(result.getShort("fldDischargeYear"));
					inPatient_new.setFldDischargeMonth(result.getShort("fldDischargeMonth"));
					inPatient_new.setFldDischargeDay(result.getInt("fldDischargeDay"));
					inPatient_new.setFldDischarged(result.getBoolean("fldDischarged"));

					inpatient_list.add(inPatient_new);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return inpatient_list;
	}

	@Override
	public InPatient searchCompPatientDetail(int fldAdmissionID) {
		String getPatientInfoQuery = "SELECT ip.fldPatientID, p.fldName, p.fldLastname, ip.fldAdmissionDate, ip.fldAdmissionYear, ip.fldAdmissionMonth, ip.fldAdmissionDay, wt.fldWardDesc, ip.fldBedNo, \r\n" + 
				"ip.fldEstimatedAmt FROM tblInPatient ip, tblPatient p,  tblWardType wt \r\n" + 
				"WHERE ip.fldPatientID=p.fldPatientID AND ip.fldWardID=wt.fldWardID AND ip.fldAdmissionNo='"
				+ fldAdmissionID + "'";

		DBMessage data = dbAssistant.execQuery(getPatientInfoQuery);
		if (!data.getCODE().equals("0")) {
			System.out.println(data.getMSG());
		} else {
			ResultSet result = data.getResultSet();
			try {
				if (result.next()) {
					InPatient inpatient = new InPatient();
					Patient patient = new Patient();
					WardType wardType = new WardType();

					inpatient.setFldPatientId(result.getInt("fldPatientID"));
					patient.setFldPatientName(result.getString("fldName"));
					patient.setFldPatientLastName(result.getString("fldLastname"));
					wardType.setFldWardDesc(result.getString("fldWardDesc"));
					inpatient.setPatient(patient);
					inpatient.setWard(wardType);
					String admissionDate[] = result.getString("fldAdmissionDate").split(" ");
					inpatient.setFldAdmissionDate(admissionDate[0]);
					inpatient.setFldAdmissionYear(result.getShort("fldAdmissionYear"));
					inpatient.setFldAdmissionMonth(result.getShort("fldAdmissionMonth"));
					inpatient.setFldAdmissionDay(result.getShort("fldAdmissionDay"));
					inpatient.setFldBedNo(result.getShort("fldBedNo"));
					inpatient.setFldEstimatedAmt(result.getDouble("fldEstimatedAmt"));
					return inpatient;

				} else {
					return null;
				}
			} catch (SQLException ex) {
				ex.printStackTrace();
				return null;
			}

		}
		return null;
	}

}
