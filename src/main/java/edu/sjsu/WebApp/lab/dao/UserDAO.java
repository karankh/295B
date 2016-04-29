package edu.sjsu.WebApp.lab.dao;

import java.util.List;

import edu.sjsu.WebApp.lab.MySQL.*;
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