package edu.sjsu.WebApp.lab.MySQL;

import org.springframework.stereotype.Repository;

/**
 * This model class contains the properties for the filed on the login.jsp page.
 * @author karan
 *
 */

public class UserPageModel {
 
	private Boolean isUserPresent;
	
	private String id;
 
   
    private String firstname;
    

    private String lastname;
    
    
    private String email;
    
    private String passwrd;
    

    private Boolean isUserlogin=false;
   
	public Boolean getIsUserlogin() {
		return isUserlogin;
	}





	public void setIsUserlogin(Boolean isUserlogin) {
		this.isUserlogin = isUserlogin;
	}





	public String getId() {
		return id;
	}





	public void setId(String id) {
		this.id = id;
	}





	public String getFirstname() {
		return firstname;
	}





	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}





	public String getLastname() {
		return lastname;
	}





	public void setLastname(String lastname) {
		this.lastname = lastname;
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





	public Boolean getIsUserPresent() {
		return isUserPresent;
	}





	public void setIsUserPresent(Boolean isUserPresent) {
		this.isUserPresent = isUserPresent;
	}




    
}