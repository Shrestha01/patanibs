package com.patanhospital.mis.dao;

import java.util.ArrayList;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.model.ChargeDRHeading;
import com.patanhospital.mis.util.KeyValue;

public interface DrHeadingDAO {
	public ArrayList<KeyValue> drHeadingList();
	public ArrayList<ChargeDRHeading> listAllDrHeading();
	public AppMessage addDrHeading(ChargeDRHeading addDrHeading);
	public AppMessage updateDrHeading(ChargeDRHeading updateDrHeading);
	

}
