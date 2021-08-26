package com.patanhospital.mis.dao;

import java.util.ArrayList;

import com.patanhospital.mis.model.WardType;
import com.patanhospital.mis.util.KeyValue;

public interface WardTypeDAO {

	public void addWardType(WardType wardType);
	public ArrayList<KeyValue> getAllWardType();
	public ArrayList<WardType> WardInfo();

}
