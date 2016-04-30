package edu.sjsu.WebApp.lab.controller;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.Servlet;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.apache.tomcat.util.codec.binary.Base64;
//import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.ValidationUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.fasterxml.jackson.databind.ObjectMapper;


import edu.sjsu.WebApp.lab.MySQL.*;
import edu.sjsu.WebApp.lab.dao.LoginDAO;
import edu.sjsu.WebApp.lab.utilServices.PlayPP;
import edu.sjsu.WebApp.labservices.LoginService;
import edu.sjsu.WebApp.labservices.UserImageService;
import edu.sjsu.WebApp.labservices.UserRecordService;

import edu.sjsu.WebApp.lab.utilServices.CheckSession;
import edu.sjsu.WebApp.lab.utilServices.FileValidator;

 
@SuppressWarnings("unused")
@Controller
public class ImageController {
    
	

	
	LoginModel loginModel;
	
	ImageModel imageModel;
	
	SimpleUploadFile simpleuploadfile;
	
	ImageUploadModel imageuploadModel;
    
    @Autowired
	private HttpSession httpSession;
    
	@Autowired
	private CheckSession sessionService;
	
	@Autowired 
    private LoginService loginService;
	
	@Autowired 
	ServletContext servletContext;
	
	@Autowired 
    private UserImageService userImageService;
	
	 
	private String imagePath = "/WEB-INF/images";
    
	private static final Logger logr = Logger.getLogger(ImageController.class); 
	
     @RequestMapping(value = "/uploadImageN",method = RequestMethod.GET)
     public String uploadImageN(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 ModelMap map) 
     {
    	 System.out.println("in uploadImageN");
    	 if(logr.isDebugEnabled()){
             logr.debug("/uploadImageN GET method is executed!");
             
    }
    	 String msg=null;
    	 if(!(error.equalsIgnoreCase("false"))) {
    		 msg=error;
    		  
    	 }
    	 
    		httpSession = sessionService.getHttpSession();
    		
    		if(!(sessionService.isUserLoggedIN())){
    			return "redirect:/login?error=You need to login to upload Pics";
        		
        	}
      
    	 SimpleUploadFile fileModel = new SimpleUploadFile();
 		 map.addAttribute("fileBucket", fileModel);
 		 map.addAttribute("Message", msg);

     	System.out.println("in upload image N GET");
     	
    
     	return "upload";
	 
 }
     

     
     @RequestMapping(value = { "/uploadImageN" }, method = RequestMethod.POST)
 	public String uploadImage(@Valid SimpleUploadFile fileBucket, BindingResult result, ModelMap model) 
 			throws IOException{
    	 if(logr.isDebugEnabled()){
             logr.debug("/uploadImageN POST method is executed!");
             
    }
    	 
    	 if(!(sessionService.isUserLoggedIN())){
 			return "redirect:/login?error=You need to login Again ! Some UserId problem.";
     		
     	}
    	 
 		int imageid=0;
 		FileValidator filevalidator = new FileValidator();
 		filevalidator.validate(fileBucket, result);
 		if (result.hasErrors()) {
 			//set ? error = error and call
 			
 			return "redirect:/uploadImageN?error=Type Or Size OF Uploaded FileIncorrect !!!";
 		} else {
 			
 			System.out.println("Fetching file");
 			
 			UserImage userImage= new UserImage();
 			
 			
 		  	if(sessionService.isUserLoggedIN()){
        		if(httpSession.getAttribute("USERID")!=null) {
        			System.out.println("In ImageController, session's userid is -- "+httpSession.getAttribute("USERID"));
        			userImage.setUserid(httpSession.getAttribute("USERID")+"");
            	}
        		
        	}
    	 
 			
 			MultipartFile mpfile = fileBucket.getFile();
 			userImage.setContent(mpfile.getBytes());
 			userImage.setImagedesc(fileBucket.getImagedesc());
 			userImage.setImagename(mpfile.getOriginalFilename());
 			
 			imageid = userImageService.insertImage(userImage);
 			if(imageid!=0){
 				System.out.println("success");
 			   //set ? error = success and pass imageid to page for view
 				userImage.setImageid(imageid+"");
 				 return "redirect:/uploadImageN?error=Successfully uploaded, /listdocs for seeing them";
 				
 			}
 			else{
 				//set ? error = error and call
 				return "redirect:/uploadImageN?error=Not uploaded, try again.";
 			}
 		}
     }
     
     
     @RequestMapping(value = "/viewImageN-{docId}",method = RequestMethod.GET)
     public ModelAndView viewImageN(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 ModelMap map,@PathVariable String docId) 
     {
    	 if(logr.isDebugEnabled()){
             logr.debug("/viewImageN-{docId} GET method is executed!");
             
    }
    	 System.out.println("in upload image N GET");
    	 System.out.println("input doc id is::"+docId);
    	 

    	 UserImage userImage= new UserImage();
    	 SimpleUploadFile fileModel = new SimpleUploadFile();
 		 map.addAttribute("fileBucket", fileModel);
 		 
 		 userImage = userImageService.getImageById(docId);
 		 System.out.println("userImageID"+userImage.getImageid());
 		if(userImage.getImageid().equalsIgnoreCase("-1")) {
 			
 			ModelAndView model = new ModelAndView("ImageError404");
       	    model.addObject("id", docId);
       	 
       	    return model;
 		}
     	
     	
     	
     	byte[] encodeBase64 = Base64.encodeBase64(userImage.getContent());
       // response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
       
     	 String base64Encoded = null;
		try {
			base64Encoded = new String(encodeBase64, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			ModelAndView model = new ModelAndView("ImageError404");
       	    model.addObject("id", docId);
       	 
       	    return model;
			//e.printStackTrace();
		}
     	     ModelAndView model = new ModelAndView("showImage");
        	 model.addObject("userImage", base64Encoded);
        	 
        	 return model;
      
     
 }
     
     @RequestMapping(value = "/listdocs",method = RequestMethod.GET)
     public ModelAndView viewAllImage(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 ModelMap map) 
     {
    	 
    	 if(logr.isDebugEnabled()){
             logr.debug("/listdocs GET method is executed!");
             
    }
    	 int i =0;
    	 List<String> li =new ArrayList<String>();
    	 //imagerepresentation
    	 List<UserImage> liUserImages = userImageService.getAllImages();
    	 
    	 //handle error msg here Pending
    	 if(liUserImages.get(0)!=null ) {
    	 while(i<liUserImages.size())  {
    		 byte[] encodeBase64 = Base64.encodeBase64(liUserImages.get(i).getContent());
    		 try {
    				String base64Encoded = new String(encodeBase64, "UTF-8");
    				li.add(base64Encoded);
    				liUserImages.get(i).setImagerepresentation(base64Encoded);
    			} catch (UnsupportedEncodingException e) {
    				
    				//e.printStackTrace();
    				ModelAndView model = new ModelAndView("ImageError");
    	       	    model.addObject("msg", "operation /listdocs failed !!! try again.");
    	        	 
    	        	 return model;
    			}
    		 i++;
    	 }
    		 
    	
     	  ModelAndView model = new ModelAndView("showImages");
        	// model.addObject("userImages", li);
        	 model.addObject("userImages", liUserImages);
        	 return model;
      
     
 }
    	 ModelAndView model = new ModelAndView("ImageError");
    	 model.addObject("msg", "operation /listdocs returned 0 Images !!!!");
     	 
     	 return model;
    
}
     
     
     @RequestMapping(value = "/searchImages",method = RequestMethod.GET)
     public ModelAndView searchImages(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 @RequestParam(value = "text", required = false, defaultValue = "false") String text,ModelMap map) 
     {
    	 if(logr.isDebugEnabled()){
             logr.debug("/searchImages GET method is executed!");
             
    }
    	 
    	 System.out.println("In /searchImages ");
    	 
    	 
    	 int i =0;
    	 List<String> li =new ArrayList<String>();
    	 //imagerepresentation
    	 List<UserImage> liUserImages = userImageService.searchImagesByText(text);
    	 //handle error msg here Pending if(liUserImages.get(0)!=null )
    	 if(liUserImages.get(0)!=null ) {
    	 while(i<liUserImages.size())  {
    		 byte[] encodeBase64 = Base64.encodeBase64(liUserImages.get(i).getContent());
    		 try {
    				String base64Encoded = new String(encodeBase64, "UTF-8");
    				li.add(base64Encoded);
    				liUserImages.get(i).setImagerepresentation(base64Encoded);
    			} catch (UnsupportedEncodingException e) {
    				
    				e.printStackTrace();
    				ModelAndView model = new ModelAndView("error");
    	        	 model.addObject("Message", "Operation failed");
    	        	 
    	        	 return model;
    			}
    		 i++;
    	 }
    		 
    	
     	  ModelAndView model = new ModelAndView("showImages");
        	 model.addObject("userImages", liUserImages);
        	 
        	 return model;
      
     
 }
    	 ModelAndView model = new ModelAndView("ImageError");
    	 model.addObject("msg", "operation /listdocs returned 0 Images !!!!");
     	 
     	 return model;
    
}
     
     
     @RequestMapping(value = "/searchImagesByUser",method = RequestMethod.GET)
     public ModelAndView searchImagesByUser(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 @RequestParam(value = "text", required = false, defaultValue = "false") String text,ModelMap map) 
     {
    	 if(logr.isDebugEnabled()){
             logr.debug("/searchImages GET method is executed!");
             
    }
    	 String userid="";
    	 
    	 System.out.println("In /searchImagesByUser ");
    	 
    		if(sessionService.isUserLoggedIN()){
        		if(httpSession.getAttribute("USERID")!=null) {
        			System.out.println("In searchImagesByUser, session's userid is -- "+httpSession.getAttribute("USERID"));
        			userid=httpSession.getAttribute("USERID")+"";
            	}
        		
        	}
    	 int i =0;
    	 List<String> li =new ArrayList<String>();
    	 //imagerepresentation
    	 List<UserImage> liUserImages = userImageService.getAllImagesByUserId(userid);
    	 //handle error msg here Pending if(liUserImages.get(0)!=null )
    	 if(liUserImages.get(0)!=null ) {
    	 while(i<liUserImages.size())  {
    		 byte[] encodeBase64 = Base64.encodeBase64(liUserImages.get(i).getContent());
    		 try {
    				String base64Encoded = new String(encodeBase64, "UTF-8");
    				li.add(base64Encoded);
    				liUserImages.get(i).setImagerepresentation(base64Encoded);
    			} catch (UnsupportedEncodingException e) {
    				
    				e.printStackTrace();
    				ModelAndView model = new ModelAndView("error");
    	        	 model.addObject("Message", "Operation failed");
    	        	 
    	        	 return model;
    			}
    		 i++;
    	 }
    		 
    	
     	  ModelAndView model = new ModelAndView("showImages");
        	 model.addObject("userImages", liUserImages);
        	 
        	 return model;
      
     
 }
    	 ModelAndView model = new ModelAndView("ImageError");
    	 model.addObject("msg", "operation /searchImagesByUserId returned 0 Images !!!!");
     	 
     	 return model;
    
}
     
     
     
}