package com.patanhospital.mis.dao;

import java.util.ArrayList;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.model.District;

public interface DistrictDAO {
	public ArrayList<District> listAllDistrictName();
	public AppMessage addNewDistrict(District district);
	public AppMessage updateDistrict(District updateDisct);
}
