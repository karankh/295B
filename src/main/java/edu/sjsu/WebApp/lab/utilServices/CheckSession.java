package edu.sjsu.WebApp.lab.utilServices;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Repository;
import org.springframework.web.servlet.ModelAndView;

import edu.sjsu.WebApp.lab.MySQL.LoginModel;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;




 
/**
 * Implementation of the encryption class
 * @author Karan
 */

@SuppressWarnings("unused")
@Service("sessionService")
public class CheckSession {
	
	@Autowired
	private HttpSession httpSession;
	
	public HttpSession getHttpSession() {
		return httpSession;
	}

	public void setHttpSession(HttpSession httpSession) {
		this.httpSession = httpSession;
	}
	
	
	public boolean checkAuth() {
		
		LoginModel login = new LoginModel();

		if(httpSession==null)
    	{
    		System.out.println("user not logged in so redirecting to login");
    		        	
         return false;
    		
    	}
    	
    	if(httpSession.getAttribute("USERID")==null)
    	{
    		System.out.println("userid not fetched from session, some problem");
    		
    		return false;
    		
    	}
		return true;
        
        }
	
	
public boolean isUserLoggedIN() {
		
		LoginModel login = new LoginModel();

		if(httpSession==null)
    	{
    		System.out.println("user not logged in.");
    		        	
         return false;
    		
    	}
    	
    	if(httpSession.getAttribute("USERID")==null)
    	{
    		System.out.println("userid not fetched from session, some problem.");
    		
    		return false;
    		
    	}
		return true;
        
        }
         
    }
	
