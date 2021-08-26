package com.patanhospital.mis.dao;

import java.util.ArrayList;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.model.InPatient;
import com.patanhospital.mis.model.Patient;

public interface PatientDAO {
	public AppMessage addPatient(Patient patient);
	public Patient searchPatient(int fldPatientID);
	public int addInPatient(InPatient inPatient);
	public ArrayList<InPatient> searchInPatient(int fldPatientID);
	public InPatient searchCompPatientDetail(int fldAdmissionID);
}
