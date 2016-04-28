package edu.sjsu.WebApp.labservices;


import edu.sjsu.WebApp.lab.MySQL.LoginModel;
import edu.sjsu.WebApp.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebApp.lab.MySQL.UserPageModel;

/**
 * LoginService Interface
 * @author Karan
 *
 */
public interface LoginService {
	public LoginModel validate(LoginModel loginmodel);
}