package com.patanhospital.mis.dao;

import java.util.ArrayList;

import com.patanhospital.mis.cmnmessage.AppMessage;
import com.patanhospital.mis.model.AppUser;

public interface AppUserDAO {
	public AppMessage addAppUser(AppUser appUser);
	public ArrayList<AppUser> listAllAppUser();
	public AppUser getAppUserInfo(int fldUserID);
	public boolean validateAppUser(AppUser appUser);
}
