package edu.sjsu.WebPortal.labservices;


import edu.sjsu.WebPortal.lab.MySQL.LoginModel;
import edu.sjsu.WebPortal.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebPortal.lab.MySQL.UserPageModel;

/**
 * LoginService Interface
 * @author Karan
 *
 */
public interface LoginService {
	public LoginModel validate(LoginModel loginmodel);
}