package edu.sjsu.WebApp.lab.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
//import org.codehaus.jackson.map.ObjectMapper;
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

import edu.sjsu.WebApp.lab.MySQL.HomePageModel;
import edu.sjsu.WebApp.lab.MySQL.LoginModel;
import edu.sjsu.WebApp.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebApp.lab.MySQL.UserPageModel;
import edu.sjsu.WebApp.lab.utilServices.CheckSession;
import edu.sjsu.WebApp.lab.utilServices.PlayPP;
import edu.sjsu.WebApp.labservices.UserRecordService;
 
@SuppressWarnings("unused")
@Controller
public class FirstController {
 
	private static final Logger logr = Logger.getLogger(FirstController.class); 
    
    @Autowired 
    private UserRecordService userRecordService;
    
    @Autowired
	private HttpSession httpSession;
    
    @Autowired
	private CheckSession sessionService;
    
    private SignUpPageModel signUpPageModel;
    private HomePageModel homepageModel;
    private UserPageModel userpageModel;
    private LoginModel loginModel;
    
    
    
    
    /**
     * This method will load the homepage.jsp page when the /homepage requested.
     */
    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public ModelAndView initError() {
    	// if user doesn't exist, then redirect to error page
	          return new ModelAndView("error404", "userpageDetails", userpageModel);
	      
    }
    
    
    
    /**
     * This method will load the homepage.jsp page when the /homepage requested.
     */
    @RequestMapping(value = "/homepage",method = RequestMethod.GET)
    public ModelAndView initRR(@RequestParam(value = "error", required = false, defaultValue = "false") String error) {
    	homepageModel = new HomePageModel();
 
    	if(logr.isDebugEnabled()){
            logr.debug("get UserHomePage method is executed!"); }
    	
    	String msg=null;
    	if(!error.equalsIgnoreCase("false")) {
    		System.out.println("in homepage get the error is :"+error);
    		msg=error;
    	}

    	httpSession = sessionService.getHttpSession();
    	if(sessionService.isUserLoggedIN()){
    		if(httpSession.getAttribute("USERID")!=null) {
    			System.out.println("home page session id-- "+httpSession.getAttribute("USERID"));
        		System.out.println("setting homepage isUser true");
        		homepageModel.setIsUser(true);
        	}
    		
    	}
    	
    	ModelAndView model = new ModelAndView("homepage");
     	 model.addObject("homepageDetails", homepageModel);
     	 model.addObject("Message", msg);
     	 return model;
    	
       
    }
    
    @RequestMapping(value = "/homepage",method = RequestMethod.POST)
    public String init(@ModelAttribute("homepageDetails")HomePageModel homepageModel,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		HttpServletRequest request,  HttpServletResponse response) {
    	
    	
    	if(logr.isDebugEnabled()){
            logr.debug("POST UserHomePage method is executed!"); } 
    	String msg=null;
    	if(!error.equalsIgnoreCase("false")) {
    		System.out.println("in homepage get the error is :"+error);
    		msg=error;
    	}

    	httpSession = sessionService.getHttpSession();
    	if(sessionService.isUserLoggedIN()){
    		if(httpSession.getAttribute("USERID")!=null) {
    			System.out.println("home page session id-- "+httpSession.getAttribute("USERID"));
        		System.out.println("setting homepage isUser true");
        		homepageModel.setIsUser(true);
        	}
    		
    	}
    	
    	return "redirect:/searchImages?text="+homepageModel.getSearchText();
    	
       
    }
    
   
    
    /**
     * This method will load the signup.jsp page when the /registration requested.
     */
    @RequestMapping(value = "/signUp",method = RequestMethod.GET)
    public ModelAndView initr(@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		HttpServletRequest request,  HttpServletResponse response) {
    	signUpPageModel = new SignUpPageModel();
    	
    	if(logr.isDebugEnabled()){
            logr.debug("GET SIGNUP method is executed!"); }
    	
    	String msg=null;
    	if(!error.equalsIgnoreCase("false")) {
    		System.out.println("in signup get the error is :"+error);
    		msg=error;
    	}
    	response.setHeader(
    	        "Cache-Control",
    	        "no-cache, max-age=0, must-revalidate, no-store");
    	
    	ModelAndView model = new ModelAndView("registration");
      	 model.addObject("registrationDetails", signUpPageModel);
      	 model.addObject("Message", msg);
      	 return model;
       //return new ModelAndView("registration", "registrationDetails", signUpPageModel);
    }
    
    
    
 
    /**
     * This method will be called when the user submits the signup details from registration.jsp page.
     * If there is any error it will be displayed on the same page, if the user is valid then, will 
     * be redirected to success page.
     * 
     * @param homepageModel
     * @param bindingResult
     * @param request
     * @param response
     * @return ModelAndView
     */ 
    @RequestMapping(value = "/signUp",method = RequestMethod.POST)
    public String login(@ModelAttribute("registrationDetails")SignUpPageModel signUpPageModel1, BindingResult bindingResult, 
            HttpServletRequest request,  HttpServletResponse response) 
    {
    	
    	if(logr.isDebugEnabled()){
            logr.debug("POST SIGNUP method is executed!"); } 
    	
        try 
        {
        	String msg=null;
           
        	if (bindingResult.hasErrors())
            {
                //returning the errors on same page if any errors..
        		return "redirect:/signUp";
            }
            else
            {
            	//check if user exists!!!
            	if(userRecordService.isUserThere(signUpPageModel1)) {
            		System.out.println("Already there.");
            		return "redirect:/signUp?error=User Already Present. Try Again.";
            	}
            	
            	//encrypting password passed from jsp
            	signUpPageModel1.setPasswrd(PlayPP.sha1(signUpPageModel1.getPasswrd()));
            	
            	
            	
            	// insert the record by calling the service
            	userRecordService.insertUser(signUpPageModel1);
            //	msg="Your Page created successfully";
            	System.out.println("id is ---"+signUpPageModel1.getId());
            	userpageModel = new UserPageModel();
            	
            	userpageModel.setId(signUpPageModel1.getId());
            	userpageModel.setEmail(signUpPageModel1.getEmail());
            	userpageModel.setFirstname(signUpPageModel1.getFirstname());
            	userpageModel.setLastname(signUpPageModel1.getLastname());
            	
            	
            	return "redirect:/userpage/"+userpageModel.getId()+"/?brief=true&mode=newuser";
//            
//           	 	
          }
        } catch (Exception e) {
            System.out.println("Exception in FirstController "+e.getMessage());
            e.printStackTrace();
            return "redirect:/signUp";
        }
        
    }
    
    
    /**
     * This method will load the userpage.jsp page when the "/userpage/userId" page requested.
     * It also returns json in case brief=true
     */
    @RequestMapping(value = "/userpage/{userId}",method = RequestMethod.GET)
    public ModelAndView init(@PathVariable("userId") String id,@RequestParam(value = "brief", required = false, defaultValue = "false") String nojsoncheck,
    		@RequestParam(value = "mode", required = false, defaultValue = "notnew") String mode){
    	
    	
    	if(logr.isDebugEnabled()){
            logr.debug("get /userpage/{userId} method is executed!"); }
    	
    	
    	String msg=null;
    	if(!mode.equalsIgnoreCase("newuser")) {
    		userpageModel = new UserPageModel();
    		msg="Welcome back to our webApp";
    	}
    	
    	if(mode.equalsIgnoreCase("newuser")) {
    		
    		msg="your account has been created successfully.";
    	}
    	
    	if(mode.equalsIgnoreCase("update")) {
    		
    		msg="your account has been updated successfully.";
    	}
    	
    	
	    
    	userpageModel.setId(id);
    	userpageModel=userRecordService.getUser(userpageModel);
	       
	       // if user doesn't exist, then redirect to error page
	       if(!userpageModel.getIsUserPresent())
	       {
	    	   
	    	   return new ModelAndView("error404", "userpageDetails", userpageModel);
	       }
	       
	       // if json not requested then redirected to corresponding method.
		if(nojsoncheck.equalsIgnoreCase("true"))
		{
			     ModelAndView model = new ModelAndView("userpage");
	           	 model.addObject("userpageDetails", userpageModel);
	           	 model.addObject("Message", msg);
	           	 return model;
			 
			
			
		}
		// else return json content
		else
		{	
			return useJson(userpageModel);
        }
    }
    
    /*
     * Internal method to convert java object to json object using object mapper.
     * */
    	public ModelAndView  useJson(UserPageModel userpageModel) {
    		if(logr.isDebugEnabled()){
                logr.debug("get useJson method is executed!"); }	
            System.out.println("in  json conversion method" + userpageModel.getFirstname());
            ObjectMapper obk = new ObjectMapper();
            String message = null;
      			
			//sending JSon data withing Html pre tag
			try {
				message = "<pre>"
						+ obk.writer().withDefaultPrettyPrinter().writeValueAsString(userpageModel)
						+"<pre>";
				
				
				System.out.println(message);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			
			ModelAndView model = new ModelAndView("jsonP");
			model.addObject("jsonDetails", message);

            return model;
        
    }
    

    	/**
         * This method corresponds to /userpage/userid and updates the info. passed through html form using POST.
         */ 
    @RequestMapping(value = "/userpage/{userId}",method = RequestMethod.POST,params="update")
    public String init12(@ModelAttribute("userpageDetails")UserPageModel userpageModel, BindingResult bindingResult, 
            HttpServletRequest request,  HttpServletResponse response,@PathVariable("userId") String id, 
            @RequestParam(value = "firstname", required = false, defaultValue = "false") String firstname,
            @RequestParam(value = "lastname", required = false, defaultValue = "false") String lastname,
            @RequestParam(value = "email", required = false, defaultValue = "false") String email){
    	
    	
		System.out.println("userpage model before sql-"+userpageModel.getLastname());
		if(logr.isDebugEnabled()){
            logr.debug("POST /userpage/{userId} update method is executed!"); }	
		
         ValidationUtils.rejectIfEmptyOrWhitespace(bindingResult,"firstname","firstname", "firstname not be empty");
         
         if (bindingResult.hasErrors())
         {
             //returning the errors on same page if any errors..
        	 return "redirect:/userpage/"+userpageModel.getId()+"/?brief=true";
         }
         else
         {
        	 
        	 userpageModel.setId(id);
        	
         	userRecordService.isUserPresentById(userpageModel);
         	System.out.println("userpage model after sql-"+userpageModel.getLastname());
         	
         	if(userpageModel.getIsUserPresent())
         	{
 	    	 
         		
         		 if(userRecordService.updateUser(userpageModel)==1) // calling service to update the record.
            	 {
            		
            		 return "redirect:/userpage/"+userpageModel.getId()+"/?brief=true&mode=update";
            	 
            	 }
            	 else
            	 {
            		 return "redirect:/userpage/"+userpageModel.getId()+"/?brief=true";
            	 }
         		 
         		 
     	       
    		
 	        }
         	
         	else
         	{
        	 
         		return "redirect:/signUp?error=User Not Present. Try SignUp First.";

         	}
        	 
        	 
        	 
         }
		
      
    }
    
    /**
     * This method corresponds to /userpage/userid and updates the info. passed through query parameters using POST.
     * chkkkkkkkkkkkkkk ..problem userpagemodel
     */ 
    @RequestMapping(value = "/userpage/{userId}",method = RequestMethod.POST)
    public ModelAndView init15(  
            HttpServletRequest request,  HttpServletResponse response,
            @PathVariable("userId") String id, 
            @RequestParam(value = "firstname", required = false, defaultValue = "defFname") String fname,
            @RequestParam(value = "lastname", required = false, defaultValue = "deflastname") String lastname,
            @RequestParam(value = "email", required = false, defaultValue = "defemail") String email,
            @RequestParam(value = "passwrd", required = false, defaultValue = "defpasword") String passwrd){
    	
    	System.out.println("REST POST FOR USER... SEE this code ");
    	
    	if(logr.isDebugEnabled()){
            logr.debug("REST POST /userpage/{userId} update method is executed!"); }	
		
    	
		 UserPageModel userpageModel11 = new UserPageModel();
		 signUpPageModel= new SignUpPageModel();
		 String msg=null;
		 userpageModel11.setId(id);
        	 String returned_firstname=userRecordService.getUser(userpageModel11).getFirstname(); // checking if record exists in db.
        	 System.out.println("returnd name is"  + returned_firstname);
        	 
        	 // if record doesn't exist then create one using service.
        	 if(returned_firstname==null || returned_firstname.isEmpty())
        	 {
        		 signUpPageModel.setId(id);
        		 signUpPageModel.setFirstname(fname);
        		 signUpPageModel.setPasswrd(passwrd);
        		 signUpPageModel.setLastname(lastname);
        		 signUpPageModel.setEmail(email);
        	
        		    msg="successfully created";
        		   userRecordService.insertUser(signUpPageModel);
        		 
        	 }
        	 
        	 // updating the record in db based on values passed through query parameters.
        	 
        	 else
        	 {
        		 userpageModel11.setId(id);
        		 userpageModel11.setFirstname(fname);
        		 userpageModel11.setPasswrd(passwrd);
        		 userpageModel11.setLastname(lastname);
        		 userpageModel11.setEmail(email);
        		
        		 if(userRecordService.updateUser(userpageModel11)==1)
        		 {
        			 msg="successfully updated!";
        		 }
        		 else
        		 {
        			 msg="not updated!";
        		 }
        	 }
        	 ModelAndView model = new ModelAndView("userpage");
        	 model.addObject("userpageDetails", userpageModel11);
        	 model.addObject("Message", msg);
        	 return model;
        	 
    }
    
    /*
     * This method deletes the record using DELETE on request from form. 
     * chkkkkkkkkkkkkkkkkkkk check if user exists !!!!!!! TO DO
     * */
    @RequestMapping(value = "/userpage/{userId}",method = RequestMethod.POST,params="delete")
    public String init123(@ModelAttribute("userpageDetails")UserPageModel userpageModel, BindingResult bindingResult, 
            HttpServletRequest request,  HttpServletResponse response,@PathVariable("userId") String id,@RequestParam(value = "brief", required = false, defaultValue = "false") String jsoncheck){
    
    	System.out.println("in normal del");
    	if(logr.isDebugEnabled()){
            logr.debug("POST /userpage/{userId} Delete method is executed!"); }	
		
    	userpageModel.setId(id);
    	userRecordService.isUserPresentById(userpageModel);
     	if(!userpageModel.getIsUserPresent())
     	{
	    	 
     		return "redirect:/signUp?error=User Not Present. Try SignUp First.";
			
	        }
        if(userRecordService.deleteUser(userpageModel))
        {
     	  
          userpageModel = new UserPageModel();
     	  return "redirect:/signUp?error=Thanks, the record has been deleted, now signUp here again.";
     	   
     	   
        }
		return null;

	
        
    }
    
    
    /*
     * This method deletes the record using DELETE.
     * It can work from clients such as POSTMAN client.
     * chkkkkkkkkkkkkkkkkk
     * */    
    @RequestMapping(value = "/userpage/{userId}",method = RequestMethod.DELETE)
    public ModelAndView initD(@PathVariable("userId") String id){
    	if(logr.isDebugEnabled()){
            logr.debug("REST DELETE /userpage/{userId} delete method is executed!"); }	
		
    	
    	System.out.println("in abnormal del");
    	String message=null;
    	ObjectMapper obk = new ObjectMapper();
    	userpageModel = new UserPageModel();
       
    	userpageModel.setId(id);
    	userRecordService.isUserPresentById(userpageModel);
     	if(!userpageModel.getIsUserPresent())
     	{
	    	 
     		ModelAndView model = new ModelAndView("error");
			
           return model; 
	        }
       if(userRecordService.deleteUser(userpageModel))
       {
    	   
    	   try {
				message = obk.writer().withDefaultPrettyPrinter().writeValueAsString("Deletion Done.");
				
				
				System.out.println(message);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			ModelAndView model = new ModelAndView("jsonP");
			model.addObject("jsonDetails", message);

           return model; 
    	   
    	   
       }

       return null;
    }
    
  
    /*
     * This method loads the homepage on application startup.
     * Works on "/" mapping.     * */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String initM() {
    	
    	System.out.println("entrii");
    	
    	return "redirect:/homepage";
    }

}