package edu.sjsu.WebPortal.lab.controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import org.apache.log4j.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


import com.fasterxml.jackson.databind.ObjectMapper;
import edu.sjsu.WebPortal.lab.*;
import edu.sjsu.WebPortal.lab.MySQL.*;

import edu.sjsu.WebPortal.labservices.*;
import edu.sjsu.WebPortal.lab.utilServices.*;
 
@SuppressWarnings("unused")
@Controller
public class LoginController {
    
	
	
	LoginModel loginModel;
	UserPageModel userpageModel;

    
    @Autowired
	private HttpSession httpSession;
    
    @Autowired 
    private UserRecordService userRecordService;
    
	@Autowired
	private CheckSession sessionService;
	
	@Autowired 
    private LoginService loginService;
	 
	private static final Logger logr = Logger.getLogger(LoginController.class); 
    
    @RequestMapping(value = "/login",method = RequestMethod.GET)
    public ModelAndView loginPage(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error) {
    
    	
    	String msg=null;
    	if(logr.isDebugEnabled()){
            logr.debug("login method is executed!");
            
   }
    	httpSession = sessionService.getHttpSession();
    	if(sessionService.isUserLoggedIN()){
    		ModelAndView model = new ModelAndView("alreadyLoggedIn");
         	 model.addObject("Message", "Currently logged IN, try log out first from  homepage");
         	 return model;
    		
    	}
    	
    		
    	loginModel = new LoginModel();
    	
    	
    	
    	if(!error.equalsIgnoreCase("false")) {
    		System.out.println("in login get, the error is :"+error);
    		msg=error;
    	}
    	
    	response.setHeader(
    	        "Cache-Control",
    	        "no-cache, max-age=0, must-revalidate, no-store");
    	
    	ModelAndView model = new ModelAndView("login");
      	 model.addObject("logindetails", loginModel);
      	 model.addObject("Message", msg);
      	 return model;
 
    }
    
    @RequestMapping(value = "/login",method = RequestMethod.POST)
    public String recieveCategory(@ModelAttribute("logindetails")LoginModel loginModel1, BindingResult bindingResult, 
            HttpServletRequest request,  HttpServletResponse response) 
    {
    	
    	httpSession = sessionService.getHttpSession();
    	if(sessionService.isUserLoggedIN()){
    		
    		return "redirect:/homepage?error=User already logged In, try logging out first.!";
    	}
    	
        try {
        	
        	    String msg=null;
        	   
        	   	loginModel1.setPasswrd(PlayPP.sha1(loginModel1.getPasswrd()));
            	loginModel1 = loginService.validate(loginModel1);
            	
            	if(logr.isDebugEnabled()){
                    logr.debug("login method POST - loginModel1.getId() !");
                    logr.debug(loginModel1.getId());
                    
           }
            	if(httpSession==null) { 
            		if(logr.isDebugEnabled())
            			logr.debug("session null error");
            	}
            	
            	if(!loginModel1.getIsLogin()) {
	            	loginModel = new LoginModel();
	            	return "redirect:/login?error=Invalid credentials. Try Again.";
                 } else {
                	 
            		httpSession = sessionService.getHttpSession();
            		httpSession.setAttribute("USERID", loginModel1.getId());
            		httpSession.setAttribute("USERNAME", loginModel1.getEmail());
            		userpageModel = new UserPageModel();
            		userpageModel.setEmail(loginModel1.getEmail());
            		userpageModel =userRecordService.getUserByEmailId(userpageModel);
            		httpSession.setAttribute("USERFIRSTNAME", userpageModel.getFirstname());
            		httpSession.setAttribute("USERTYPE", userpageModel.getUsertype());
            		sessionService.setHttpSession(httpSession);
            		logr.info("my userid in session is" + httpSession.getAttribute("USERID"));
            		return "redirect:/homepage";
            	}
           	 	
           
        } catch (Exception e) {
            System.out.println("Exception in FirstController "+e.getMessage());
            e.printStackTrace();
            return "redirect:/login?error=Invalid credentials. Try Again.";
        }
    }
    
    @RequestMapping(value = "/logout",method = RequestMethod.GET)
    public String logoutPage(HttpServletRequest request,  HttpServletResponse response) {
    	
    	
    	System.out.println("in logout");
    	httpSession = sessionService.getHttpSession();
    	if(!sessionService.isUserLoggedIN()){
    		
    		response.setHeader(
        	        "Cache-Control",
        	        "no-cache, max-age=0, must-revalidate, no-store");
        	
    		return "redirect:/login?error= !! Not Currently loggedIn !!";
    	}
    	
    	else {
    		httpSession.removeAttribute("USERID");
        	httpSession.removeAttribute("USERNAME");
    		httpSession.removeAttribute("USERFIRSTNAME");
        	httpSession.invalidate();
        	//sessionService.setHttpSession(null);
        	
        	loginModel = new LoginModel();
        
        	response.setHeader(
        	        "Cache-Control",
        	        "no-cache, max-age=0, must-revalidate, no-store");
        	
        	return "redirect:/login?error=LoggedOut Successfully";
    		
    	}
    	
    	
    	
    	
    }
    
    @RequestMapping(value = "/*",method = RequestMethod.GET)
    public String initError89() {
    	// if user doesn't exist, then redirect to error page
	          return "defaultPage";
	      
    }
    
}