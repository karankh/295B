package edu.sjsu.WebApp.labservices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.WebApp.lab.MySQL.LoginModel;
import edu.sjsu.WebApp.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebApp.lab.MySQL.UserPageModel;
import edu.sjsu.WebApp.lab.dao.LoginDAO;
import edu.sjsu.WebApp.lab.dao.UserDAO;

/**
 * Implementation of UserRecordService
 * @author karan
 *
 */
@Service("loginService")
public class LoginServiceImpl implements LoginService {
    @Autowired
    LoginDAO loginDAO;
 
	public LoginModel validate(LoginModel loginmodel) {
		return loginDAO.validate(loginmodel);
	}
 
}