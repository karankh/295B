package edu.sjsu.WebPortal.labservices;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.WebPortal.lab.MySQL.LoginModel;
import edu.sjsu.WebPortal.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebPortal.lab.MySQL.UserPageModel;
import edu.sjsu.WebPortal.lab.dao.LoginDAO;
import edu.sjsu.WebPortal.lab.dao.UserDAO;

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