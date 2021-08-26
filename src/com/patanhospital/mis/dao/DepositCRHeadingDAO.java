package com.patanhospital.mis.dao;

import java.util.ArrayList;

import com.patanhospital.mis.model.DepositCRHeading;
import com.patanhospital.mis.util.KeyValue;

public interface DepositCRHeadingDAO {
	public ArrayList<KeyValue> listCrHeading();
	public void addCRHeading(DepositCRHeading deposit);
	public void removeCRHeading(int fldCrHeadingNo);
	public ArrayList<DepositCRHeading> listAllCrHeading();
}
