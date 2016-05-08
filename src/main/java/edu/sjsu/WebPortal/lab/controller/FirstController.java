package edu.sjsu.WebPortal.lab.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
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

import edu.sjsu.WebPortal.lab.MySQL.HomePageModel;
import edu.sjsu.WebPortal.lab.MySQL.LoginModel;
import edu.sjsu.WebPortal.lab.MySQL.SelectionModel;
import edu.sjsu.WebPortal.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebPortal.lab.MySQL.UserPageModel;
import edu.sjsu.WebPortal.lab.utilServices.CheckSession;
import edu.sjsu.WebPortal.lab.utilServices.PlayPP;
import edu.sjsu.WebPortal.labservices.UserRecordService;
import javafx.scene.control.SelectionMode;
 
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
     * This method will load the error.jsp page when the /error executed.
     */
    @RequestMapping(value = "/error",method = RequestMethod.GET)
    public ModelAndView initError() {
    	if(logr.isDebugEnabled()){
            logr.debug("get /error method is executed!"); }
	          return new ModelAndView("error404", "userpageDetails", userpageModel);
	      
    }
    
    
    
    /**
     * This method will load the homepage.jsp page when the /homepage requested.
     * Not in use
     */
    @RequestMapping(value = "/homepage",method = RequestMethod.GET)
    public ModelAndView initRR(@RequestParam(value = "error", required = false, defaultValue = "false") String error) {
    	homepageModel = new HomePageModel();
 
    	if(logr.isDebugEnabled()){
            logr.debug("get UserHomePage method is executed!"); }
    	
    	String msg=null;
    	if(!error.equalsIgnoreCase("false")) {
    		logr.debug("in homepage get the error is :"+error);
    		msg=error;
    	}

    	httpSession = sessionService.getHttpSession();
    	if(sessionService.isUserLoggedIN()){
    		if(httpSession.getAttribute("USERID")!=null) {
    			logr.debug("home page session id-- "+httpSession.getAttribute("USERID"));
        		logr.debug("setting homepage isUser true");
        		homepageModel.setIsUser(true);
        	}
    		
    	}
    	
    	ModelAndView model = new ModelAndView("homepage");
     	 model.addObject("homepageDetails", homepageModel);
     	 model.addObject("Message", msg);
     	 return model;
    	
       
    }
    
    /**
     * This method will handle POST of the homepage.jsp page when the /homepage requested.
     * Not in use
     */
    @RequestMapping(value = "/homepage",method = RequestMethod.POST)
    public String init(@ModelAttribute("homepageDetails")HomePageModel homepageModel,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		HttpServletRequest request,  HttpServletResponse response) {
    	
    	
    	if(logr.isDebugEnabled()){
            logr.debug("POST UserHomePage method is executed!"); } 
    	String msg=null;
    	if(!error.equalsIgnoreCase("false")) {
    		logr.debug("in homepage Post the error is :"+error);
    		msg=error;
    	}

    	httpSession = sessionService.getHttpSession();
    	if(sessionService.isUserLoggedIN()){
    		if(httpSession.getAttribute("USERID")!=null) {
    			logr.debug("home page session id-- "+httpSession.getAttribute("USERID"));
    			logr.debug("setting homepage isUser true");
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
    		logr.debug("in signup get the error is :"+error);
    		msg=error;
    	}
    	response.setHeader(
    	        "Cache-Control",
    	        "no-cache, max-age=0, must-revalidate, no-store");
    	
    	 ModelAndView model = new ModelAndView("registration");
      	 model.addObject("registrationDetails", signUpPageModel);
      	 model.addObject("Message", msg);
      	 return model;
       
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
            		logr.debug("Already there.");
            		return "redirect:/signUp?error=User Already Present. Try Again.";
            	}
            	
            	//encrypting password passed from jsp
            	signUpPageModel1.setPasswrd(PlayPP.sha1(signUpPageModel1.getPasswrd()));
            	signUpPageModel1.setUsertype("nonadmin");
            	
            	
            	
            	userRecordService.insertUser(signUpPageModel1);
            
            	logr.debug("id is ---"+signUpPageModel1.getId());
            	userpageModel = new UserPageModel();
            	
            	userpageModel.setId(signUpPageModel1.getId());
            	userpageModel.setEmail(signUpPageModel1.getEmail());
            	userpageModel.setFirstname(signUpPageModel1.getFirstname());
            	userpageModel.setLastname(signUpPageModel1.getLastname());
            	userpageModel.setIsUserlogin(false);
            	userpageModel.setUsertype(signUpPageModel1.getUsertype());
            	
            	return "redirect:/userpage/"+userpageModel.getId()+"/?brief=true&mode=newuser";
           	 	
          }
        } catch (Exception e) {
        	logr.debug("Exception in FirstController "+e.getMessage());
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
    	httpSession = sessionService.getHttpSession();
    	logr.info("userType - "+userpageModel.getUsertype());
    	
    	if(sessionService.isUserLoggedIN()){
    		if(httpSession.getAttribute("USERID")!=null) {
    			logr.debug("user page session id-- "+httpSession.getAttribute("USERID"));
    			logr.debug("setting userpage isUserLogin true");
        		userpageModel.setIsUserlogin(true);
        	}
    		
    	}   
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
    		logr.debug("in  json conversion method" + userpageModel.getFirstname());
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
    	
    	
		
			if(logr.isDebugEnabled()){
            logr.debug("POST /userpage/{userId} update method is executed!"); }	
		
         
         
         if (bindingResult.hasErrors())
         {
             //returning the errors on same page if any errors..
        	 return "redirect:/userpage/"+userpageModel.getId()+"/?brief=true";
         }
         else
         {
        	 
        	 userpageModel.setId(id);
        	
         	userRecordService.isUserPresentById(userpageModel);
         	
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
     * Not 100% working.
     */ 
    @RequestMapping(value = "/userpage/{userId}",method = RequestMethod.POST)
    public ModelAndView init15(  
            HttpServletRequest request,  HttpServletResponse response,
            @PathVariable("userId") String id, 
            @RequestParam(value = "firstname", required = false, defaultValue = "defFname") String fname,
            @RequestParam(value = "lastname", required = false, defaultValue = "deflastname") String lastname,
            @RequestParam(value = "email", required = false, defaultValue = "defemail") String email,
            @RequestParam(value = "passwrd", required = false, defaultValue = "defpasword") String passwrd){
    	
    	logr.debug("REST POST FOR USER... SEE this code ");
    	
    	if(logr.isDebugEnabled()){
            logr.debug("REST POST /userpage/{userId} update method is executed!"); }	
		
    	
		 UserPageModel userpageModel11 = new UserPageModel();
		 signUpPageModel= new SignUpPageModel();
		 String msg=null;
		 userpageModel11.setId(id);
        	 String returned_firstname=userRecordService.getUser(userpageModel11).getFirstname(); // checking if record exists in db.
        	 logr.debug("returnd name is"  + returned_firstname);
        	 
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
     * 
     * */
    @RequestMapping(value = "/userpage/{userId}",method = RequestMethod.POST,params="delete")
    public String init123(@ModelAttribute("userpageDetails")UserPageModel userpageModel, BindingResult bindingResult, 
            HttpServletRequest request,  HttpServletResponse response,@PathVariable("userId") String id,@RequestParam(value = "brief", required = false, defaultValue = "false") String jsoncheck){
    
    	logr.debug("in normal del");
    	if(logr.isDebugEnabled()){
            logr.debug("POST /userpage/{userId} Delete method is executed!"); }	
		
    	userpageModel.setId(id);
    	userpageModel=userRecordService.isUserPresentById(userpageModel);
     	if(!userpageModel.getIsUserPresent())
     	{
	    	 
     		return "redirect:/signUp?error=User Not Present. Try SignUp First.";
			
	        }
        if(userRecordService.deleteUser(userpageModel))
        {
        	httpSession = sessionService.getHttpSession();
            httpSession.removeAttribute("USERID");
        	  httpSession.removeAttribute("USERNAME");
    		  httpSession.removeAttribute("USERFIRSTNAME");
        	  httpSession.invalidate();
           
            userpageModel = new UserPageModel();
            response.setHeader(
        	        "Cache-Control",
        	        "no-cache, max-age=0, must-revalidate, no-store");
     	  
          userpageModel = new UserPageModel();
     	  return "redirect:/signUp?error=Thanks, the record has been deleted, now signUp here again.";
     	   
     	   
        }
        else{
        	return "redirect:/userpage/"+userpageModel.getId()+"/?brief=true";
        	}
	
        
    }
    
    
    /*
     * This method deletes the record using DELETE.
     * It can work from clients such as POSTMAN client.
     * check.
     * */    
    @RequestMapping(value = "/userpage/{userId}",method = RequestMethod.DELETE)
    public ModelAndView initD(@PathVariable("userId") String id,HttpServletResponse response){
    	if(logr.isDebugEnabled()){
            logr.debug("REST DELETE /userpage/{userId} delete method is executed!"); }	
		
    	
    	logr.debug("in abnormal del");
    	String message=null;
    	ObjectMapper obk = new ObjectMapper();
    	userpageModel = new UserPageModel();
       
    	userpageModel.setId(id);
    	userpageModel=userRecordService.isUserPresentById(userpageModel);
     	if(!userpageModel.getIsUserPresent())
     	{
	    	 
     		ModelAndView model = new ModelAndView("error");
			
           return model; 
	        }
       if(userRecordService.deleteUser(userpageModel))
       {
    	   
    	   try {httpSession = sessionService.getHttpSession();
           httpSession.removeAttribute("USERID");
       	  httpSession.removeAttribute("USERNAME");
   		  httpSession.removeAttribute("USERFIRSTNAME");
       	  httpSession.invalidate();
          
           userpageModel = new UserPageModel();
           response.setHeader(
       	        "Cache-Control",
       	        "no-cache, max-age=0, must-revalidate, no-store");
				message = obk.writer().withDefaultPrettyPrinter().writeValueAsString("Deletion Done.");
				
				
				System.out.println(message);
			} catch (Exception e) {
				
				e.printStackTrace();
			}
			
			ModelAndView model = new ModelAndView("jsonP");
			model.addObject("jsonDetails", message);

           return model; 
    	   
    	   
       }

       ModelAndView model = new ModelAndView("error");
		
       return model;
    }
    
  
    /*
     * This method loads the homepage on application startup.
     * Works on "/" mapping.     * */
    @RequestMapping(value = "/",method = RequestMethod.GET)
    public String initM() {
    	
    	logr.debug("entrii");
    	
    	return "redirect:/login";
    }
    
    
    @RequestMapping(value = "/socialApp",method = RequestMethod.GET)
    public String handleSocialApp(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
   		 ModelMap map,@RequestParam(value = "fid", required = false, defaultValue = "false") String fid) 
    {
   	
   	 if(logr.isDebugEnabled()){
            logr.debug("/socialApp GET method is executed!");
            
   }
   	 String msg=null;
   	 String id=null;
   	 if(!(error.equalsIgnoreCase("false"))) {
   		 msg=error;
   		  
   	 }
   	 
   	 if(!(fid.equalsIgnoreCase("false"))) {
   		id=fid;
   		  
   	 }
   	 

		 map.addAttribute("Message", msg);
		 map.addAttribute("id", id);
       	 return "socialApp";
	 
}
    
    
    @RequestMapping(value = "/selection",method = RequestMethod.GET)
    public ModelAndView initSelection(@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		HttpServletRequest request,  HttpServletResponse response) {
    	SelectionModel selectionModel = new SelectionModel();
    		
    	if(logr.isDebugEnabled()){
            logr.debug("GET selection method is executed!"); }
    	
    	String msg=null;
    	if(!error.equalsIgnoreCase("false")) {
    		logr.debug("in selection get the error is :"+error);
    		msg=error;
    	}
    	response.setHeader(
    	        "Cache-Control",
    	        "no-cache, max-age=0, must-revalidate, no-store");
    	
    	ModelAndView model = new ModelAndView("selection");
      	 model.addObject("selectedItems", selectionModel);
      	 model.addObject("Message", msg);
      	 return model;
       
    }
    
    @RequestMapping(value = "/selection",method = RequestMethod.POST)
    public ModelAndView selectionPost(@ModelAttribute("selectedItems")SelectionModel selectionModel, BindingResult bindingResult, 
            HttpServletRequest request,  HttpServletResponse response) 
    {
    	
    	if(logr.isDebugEnabled()){
            logr.debug("POST selection method is executed!"); } 
    	
        try 
        {
        	
        		
        	String msg=null;
           
        	if (bindingResult.hasErrors())
            {
                //returning the errors on same page if any errors..
        		 ModelAndView model = new ModelAndView("selection");
             	 model.addObject("selectedItems", selectionModel);
             	 model.addObject("Message", msg);
             	 return model;
            }
            else
            {
            	
            	logr.debug("-----------------------"+Arrays.toString(selectionModel.getPlatforms()));
            	logr.debug("-----------------------"+Arrays.toString(selectionModel.getParameters()));
            	logr.debug("-----------------------"+selectionModel.getLoad());
            	
           
            	HashMap<String,HashMap<String,String>> hmGraphs = new HashMap<String,HashMap<String,String>>();
            	HashMap<String,String> hmOpenStack = new HashMap<String,String>();
            	HashMap<String,String> hm = new HashMap<String,String>();
            	
            	hmOpenStack.put("CPU", "<b><label>CPU Performance ~ OpenStack </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/jgLQwCXN5SH' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmOpenStack.put("Memory", "<b><label>Memory Performance ~ OpenStack </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/gAQNVzKtCfu' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmOpenStack.put("Throughput", "<b><label>Throughput ~ OpenStack </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/jFRG5eL18Wt' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmOpenStack.put("Transactions", "<b><label>Transactions ~ OpenStack <lable></b><br/><iframe src='https://rpm.newrelic.com/public/charts/fCLyIshQ8d4' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmOpenStack.put("Availability", "<b><label>Availability ~ OpenStack </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/6Ot6oFPYN8O' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	
            	hmGraphs.put("OpenStack",hmOpenStack);
            	
            	HashMap<String,String> hmDocker = new HashMap<String,String>();
            	hmDocker.put("CPU", "<b><label>CPU Performance ~ Docker </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/lO2vQrRqTap' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmDocker.put("Memory", "<b><label>Memory Performance ~ Docker </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/lO2vQrRqTap' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmDocker.put("Throughput", "<b><label>Throughput ~ Docker </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/lO2vQrRqTap' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmDocker.put("Transactions", "<b><label>Transactions ~ Docker <lable></b><br/><iframe src='https://rpm.newrelic.com/public/charts/lO2vQrRqTap' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmDocker.put("Availability", "<b><label>Availability ~ Docker </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/lO2vQrRqTap' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	
            	HashMap<String,String> hmCloudFoundry = new HashMap<String,String>();
            	hmCloudFoundry.put("CPU", "<b><label>CPU Performance ~ AWS </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/ix21BWdP85z' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmCloudFoundry.put("Memory", "<b><label>Memory Performance ~ AWS </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/ix21BWdP85z' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmCloudFoundry.put("Throughput", "<b><label>Throughput ~ AWS </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/ix21BWdP85z' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmCloudFoundry.put("Transactions", "<b><label>Transactions ~ AWS <lable></b><br/><iframe src='https://rpm.newrelic.com/public/charts/ix21BWdP85z' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	hmCloudFoundry.put("Availability", "<b><label>Availability ~ AWS </label></b><br/><iframe src='https://rpm.newrelic.com/public/charts/ix21BWdP85z' width='500' height='300' scrolling='no' frameborder='no'></iframe>");
            	
            	hmGraphs.put("OpenStack",hmOpenStack);
            	hmGraphs.put("Docker",hmDocker);
            	hmGraphs.put("AWS",hmCloudFoundry);
            	
            	List<String> answers=new ArrayList<String>();
            	List<String> loadString=new ArrayList<String>();
            	
            	for(int i =0;i<selectionModel.getPlatforms().length;i++) {
            		if(selectionModel.getLoad()!=null) {
            			logr.debug("loadinggg");
            		String ltemp=selectionModel.getPlatforms()[i]+"_load.jpeg";
            		loadString.add(ltemp);}
            		for(int j =0;j<selectionModel.getParameters().length;j++) {
            			System.out.println("Input Platform "+i +" : " + selectionModel.getPlatforms()[i]);
            			System.out.println("Input Parameter "+j +" : " + selectionModel.getParameters()[j]);
            			System.out.println("getting outer hashmapBrut:"+hmGraphs.get("AWS"));
            			System.out.println("getting inner hashmapBrut:"+(hmGraphs.get("AWS")).get("CPU"));
            			System.out.println("getting outer hashmap:"+hmGraphs.get(selectionModel.getPlatforms()[i].toString()));
            			System.out.println("getting inner hashmap:"+(hmGraphs.get(selectionModel.getPlatforms()[i].toString())).get("CPU"));
            			HashMap<String,String> hmtemp = hmGraphs.get(selectionModel.getPlatforms()[i].toString());
            			String temp = hmtemp.get(selectionModel.getParameters()[j]);
            			answers.add(temp);
                	}
            	}
            	
              for(int k =0;k<answers.size();k++) {
            	  logr.debug("Getting list from computer @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
            	  logr.debug(answers.get(k));
              }
            	
              
            	 ModelAndView model = new ModelAndView("result");
             	 model.addObject("listgraphs", answers);
             	 model.addObject("listloads", loadString);
             	 model.addObject("Message", msg);
             	 return model;           	 	
          }
        } catch (Exception e) {
        	logr.debug("Exception in FirstController "+e.getMessage());
            e.printStackTrace();
            ModelAndView model = new ModelAndView("selection");
         	 model.addObject("selectedItems", selectionModel);
         	
         	 return model;
        }
        
    }
    
    @RequestMapping(value = "/dashboard",method = RequestMethod.GET)
    public ModelAndView initDashboard(@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		HttpServletRequest request,  HttpServletResponse response) {
    	SelectionModel selectionModel = new SelectionModel();
    		
    	if(logr.isDebugEnabled()){
            logr.debug("GET selection method is executed!"); }
    	
    	String msg=null;
    	if(!error.equalsIgnoreCase("false")) {
    		logr.debug("in selection get the error is :"+error);
    		msg=error;
    	}
    	response.setHeader(
    	        "Cache-Control",
    	        "no-cache, max-age=0, must-revalidate, no-store");
    	
    	List<String> DashString=new ArrayList<String>();
    	
    
    	 ModelAndView model = new ModelAndView("dashboard");

     	 model.addObject("listgraphs", DashString);
     	 model.addObject("Message", msg);
     	 return model;   
    }
    
    @RequestMapping(value = "/userpaged/{userId}",method = RequestMethod.GET)
    public String init123d(@ModelAttribute("userpageDetails")UserPageModel userpageModel, BindingResult bindingResult, 
            HttpServletRequest request,  HttpServletResponse response,@PathVariable("userId") String id,@RequestParam(value = "brief", required = false, defaultValue = "false") String jsoncheck){
    
    	
    	if(logr.isDebugEnabled()){
            logr.debug("POST /userpage/{userId} Delete method is executed!");
            logr.debug("in super del");}	
		
    	userpageModel.setId(id);
    	userpageModel = userRecordService.isUserPresentById(userpageModel);
     	if(!(userpageModel.getIsUserPresent()))
     	{
	    	 
     		return "redirect:/signUp?error=User Not Present. Try SignUp First.";
			
	        }
        if(userRecordService.deleteUser(userpageModel))
        {
          httpSession = sessionService.getHttpSession();
          httpSession.removeAttribute("USERID");
      	  httpSession.removeAttribute("USERNAME");
  		  httpSession.removeAttribute("USERFIRSTNAME");
      	  httpSession.invalidate();
         
          userpageModel = new UserPageModel();
          
     	  return "redirect:/signUp?error=Thanks, the record has been deleted, now signUp here again.";
     	   
     	   
        }
        else{
        	return "redirect:/userpage/"+userpageModel.getId()+"/?brief=true";
        	}
        

    
   
}