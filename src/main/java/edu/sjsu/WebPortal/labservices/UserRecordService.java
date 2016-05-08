package edu.sjsu.WebPortal.labservices;


import java.util.List;

import edu.sjsu.WebPortal.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebPortal.lab.MySQL.UserDocument;
import edu.sjsu.WebPortal.lab.MySQL.UserPageModel;

/**
 * UserRecordService Interface
 * @author Karan
 *
 */
public interface UserRecordService {
 
	
	
	public int insertUser(SignUpPageModel signUpPageModel);
	public UserPageModel getUser(UserPageModel userPageModel);
	public boolean deleteUser(UserPageModel userPageModel);
	public int updateUser(UserPageModel userPageModel);
	public boolean isUserThere(SignUpPageModel signUpPageModel);
	public UserPageModel isUserPresentById(UserPageModel userPageModel);
	public UserPageModel getUserByEmailId(UserPageModel userPageModel);
	
}