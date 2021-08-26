package com.patanhospital.mis.dao;

import java.util.ArrayList;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.model.EthnicGroup;

public interface EthnicGroupDAO {
	public ArrayList<EthnicGroup> listAllEthnicGrp();
	public AppMessage addNewEthnicGroup(EthnicGroup ethnicGrp);
	public AppMessage updateNewEthnicGroup(EthnicGroup ethnicGrp);
}
