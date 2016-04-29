package edu.sjsu.WebApp.labservices;


import edu.sjsu.WebApp.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebApp.lab.MySQL.UserPageModel;

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