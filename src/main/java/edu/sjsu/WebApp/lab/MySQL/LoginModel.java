package edu.sjsu.WebApp.lab.MySQL;


import java.io.Serializable;

/**
 * This model class contains the properties for the filed on the registration.jsp page.
 * @author karan
 *
 */

public class LoginModel implements Serializable {
	
	
	private static final long serialVersionUID = 1L;


    private String id;
    
    private String email;
    
    private String passwrd;
    
    private Boolean isLogin;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPasswrd() {
		return passwrd;
	}

	public void setPasswrd(String passwrd) {
		this.passwrd = passwrd;
	}

	public Boolean getIsLogin() {
		return isLogin;
	}

	public void setIsLogin(Boolean isLogin) {
		this.isLogin = isLogin;
	}



	
}
