package edu.sjsu.WebPortal.labservices;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.WebPortal.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebPortal.lab.MySQL.UserDocument;
import edu.sjsu.WebPortal.lab.MySQL.UserPageModel;
import edu.sjsu.WebPortal.lab.dao.UserDAO;

/**
 * Implementation of UserRecordService
 * @author karan
 *
 */
@Service("userRecordService")
public class UserRecordServiceImpl implements UserRecordService {
    @Autowired
    UserDAO userDAO;
 
    

	public int insertUser(SignUpPageModel signUpPageModel) {
	return userDAO.insertUser(signUpPageModel);
	}

	public UserPageModel getUser(UserPageModel userPageModel) {
		return userDAO.getUser(userPageModel);
		
	}

	public boolean deleteUser(UserPageModel userPageModel) {
		return userDAO.deleteUser(userPageModel);
	}

	public int updateUser(UserPageModel userPageModel) {
		return userDAO.updateUser(userPageModel);
	}

	public boolean isUserThere(SignUpPageModel signUpPageModel) {
		return userDAO.isUserThere(signUpPageModel);
	}

	public UserPageModel isUserPresentById(UserPageModel userPageModel) {
		return userDAO.isUserPresentById(userPageModel);
	}

	@Override
	public UserPageModel getUserByEmailId(UserPageModel userPageModel) {
		return userDAO.getUserByEmailId(userPageModel);
	}

	
}