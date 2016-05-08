package edu.sjsu.WebPortal.lab.dao;

import edu.sjsu.WebPortal.lab.MySQL.*;
/**
 * UserDAO interface
 * @author Karan
 *
 */
public interface UserDAO {
 
	public int insertUser(SignUpPageModel signUpPageModel);
	public UserPageModel getUser(UserPageModel userPageModel);
	public boolean deleteUser(UserPageModel userPageModel);
	public int updateUser(UserPageModel userPageModel);
	public boolean isUserThere(SignUpPageModel signUpPageModel);
	public UserPageModel isUserPresentById(UserPageModel userPageModel);
	public UserPageModel getUserByEmailId(UserPageModel userPageModel);
}