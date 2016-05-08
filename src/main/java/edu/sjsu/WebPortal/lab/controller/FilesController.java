package edu.sjsu.WebPortal.lab.controller;


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
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.FileCopyUtils;
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


import edu.sjsu.WebPortal.lab.MySQL.*;
import edu.sjsu.WebPortal.lab.dao.LoginDAO;
import edu.sjsu.WebPortal.lab.utilServices.PlayPP;
import edu.sjsu.WebPortal.labservices.LoginService;
import edu.sjsu.WebPortal.labservices.UserDocumentService;
import edu.sjsu.WebPortal.labservices.UserImageService;
import edu.sjsu.WebPortal.labservices.UserRecordService;

import edu.sjsu.WebPortal.lab.utilServices.CheckSession;
import edu.sjsu.WebPortal.lab.utilServices.FileValidator;

 
@SuppressWarnings("unused")
@Controller
public class FilesController {
    
	
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
    private UserDocumentService userDocumentService;
	
	 
	private String imagePath = "/WEB-INF/images";
    
	private static final Logger logr = Logger.getLogger(FilesController.class); 
	
	
	@RequestMapping(value = "/FileError404",method = RequestMethod.GET)
    public String handleFileError(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
   		 ModelMap map,@RequestParam(value = "fid", required = false, defaultValue = "false") String fid) 
    {
   	
   	 if(logr.isDebugEnabled()){
            logr.debug("/FileError404 GET method is executed!");
            
   }
   	 String msg=null;
   	 String id=null;
   	 if(!(error.equalsIgnoreCase("false"))) {
   		 msg=error;
   		  
   	 }
   	 
   	 if(!(fid.equalsIgnoreCase("false"))) {
   		id=fid;
   		  
   	 }
   	 
   	String ans ="<label>KKKKKK<label><br/><iframe src='https://rpm.newrelic.com/public/charts/gFI8lGU76DD' width='500' height='300' scrolling='no' frameborder='no'></iframe>";
		 map.addAttribute("Message", msg);
		 map.addAttribute("id", id);
		 map.addAttribute("ans", ans);
    	 System.out.println("in handleFileError GET");
    	
   
    	return "ImageError404";
	 
}
	
	
	
     @RequestMapping(value = "/uploadFile",method = RequestMethod.GET)
     public String uploadImageN(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 ModelMap map) 
     {
    	
    	 if(logr.isDebugEnabled()){
             logr.debug("/uploadFile GET method is executed!");
             
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
     

     
    @RequestMapping(value = { "/uploadFile" }, method = RequestMethod.POST)
 	public String uploadImage(@Valid SimpleUploadFile fileBucket, BindingResult result, ModelMap model) 
 			throws IOException{
    	 if(logr.isDebugEnabled()){
             logr.debug("/uploadFile POST method is executed!");
             
    }
    	 
    	 if(!(sessionService.isUserLoggedIN())){
 			return "redirect:/login?error=You need to login Again ! Some UserId problem.";
     		
     	}
    	 
 		int docid=0;
 		FileValidator filevalidator = new FileValidator();
 		filevalidator.validate(fileBucket, result);
 		if (result.hasErrors()) {
 			//set ? error = error and call
 			
 			return "redirect:/uploadFile?error=Type Or Size OF Uploaded FileIncorrect !!!";
 		} else {
 			
 			System.out.println("Fetching file");
 			
 			
 			UserDocument userDocument = new UserDocument();
 			
 			
 		  	if(sessionService.isUserLoggedIN()){
        		if(httpSession.getAttribute("USERID")!=null) {
        			System.out.println("In ImageController, session's userid is -- "+httpSession.getAttribute("USERID"));
        			userDocument.setUserid(httpSession.getAttribute("USERID")+"");
            	}
        		
        	}
    	 
 			
 			MultipartFile mpfile = fileBucket.getFile();
 			
 			userDocument.setContent(mpfile.getBytes());
 			userDocument.setDocumentCategory(fileBucket.getDocumentCategory());
 			userDocument.setDocumentDescp(fileBucket.getDocumentDescp());
 			userDocument.setDocumentType(mpfile.getContentType());
 			userDocument.setDocumentName(mpfile.getOriginalFilename());


 			docid = userDocumentService.insertDocument(userDocument,false);
 			if(docid!=0){
 				System.out.println("success");
 			  
 				userDocument.setDocid(docid+"");
 				 return "redirect:/uploadFile?error=Successfully uploaded, /listdocs for seeing them";
 				
 			}
 			else{
 				
 				return "redirect:/uploadFile?error=Not uploaded, try again.";
 			}
 		}
     }
     
     
     @RequestMapping(value = "/viewFile-{docId}",method = RequestMethod.GET)
     public void  viewImageN(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 ModelMap map,@PathVariable String docId) throws IOException 
     {
    	 if(logr.isDebugEnabled()){
             logr.debug("/viewFile-{docId} GET method is executed!");
             
         }
    	 
    	

    	 UserDocument userDocument= new UserDocument();
    	 SimpleUploadFile fileModel = new SimpleUploadFile();
 		 map.addAttribute("fileBucket", fileModel);
 		 
 		 userDocument = userDocumentService.getDocumentById(docId,false);
 		 System.out.println("userImageID"+userDocument.getDocid());
 		if(!(userDocument.getDocid().equalsIgnoreCase("-1")))
 			{ 	
 		response.setContentType(userDocument.getDocumentType());
        response.setContentLength(userDocument.getContent().length);
        
       // response.setHeader("Content-Disposition","attachment; filename=\"" + userDocument.getDocumentName() +"\"");
 
        try {
			FileCopyUtils.copy(userDocument.getContent(), response.getOutputStream());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		logr.error(e);
			
			response.sendRedirect("FileError404?fid="+docId);
		}}
 		else {
 			response.sendRedirect("FileError404?fid="+docId);

 		}
		

 }
     
     @RequestMapping(value = "/viewReport-{docId}",method = RequestMethod.GET)
     public void  viewReport(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 ModelMap map,@PathVariable String docId) throws IOException 
     {
    	 if(logr.isDebugEnabled()){
             logr.debug("/viewReport-{docId} GET method is executed!");
             
         }
    	 
    	

    	 UserDocument userDocument= new UserDocument();
    	 SimpleUploadFile fileModel = new SimpleUploadFile();
 		 map.addAttribute("fileBucket", fileModel);
 		 
 		 userDocument = userDocumentService.getDocumentById(docId,true);
 		 System.out.println("userReportID"+userDocument.getDocid());
 		if(!(userDocument.getDocid().equalsIgnoreCase("-1")))
 			{ 		
 			
 		response.setContentType(userDocument.getDocumentType());
        response.setContentLength(userDocument.getContent().length);
        
       // response.setHeader("Content-Disposition","attachment; filename=\"" + userDocument.getDocumentName() +"\"");
 
        try {
			FileCopyUtils.copy(userDocument.getContent(), response.getOutputStream());
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
		logr.error(e);
			
			response.sendRedirect("FileError404?fid="+docId);
		}}
 		else {
 			response.sendRedirect("FileError404?fid="+docId);

 		}
		

 }
     

     @RequestMapping(value = "/downloadFile-{docId}",method = RequestMethod.GET)
     public void  downloadFile(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 ModelMap map,@PathVariable String docId) throws IOException 
     {
    	 if(logr.isDebugEnabled()){
             logr.debug("/downloadFile-{docId} GET method is executed!");
             
         }
    	 
    	

    	 UserDocument userDocument= new UserDocument();
    	 SimpleUploadFile fileModel = new SimpleUploadFile();
 		 map.addAttribute("fileBucket", fileModel);
 		 
 		 userDocument = userDocumentService.getDocumentById(docId,false);
 		 System.out.println("userImageID"+userDocument.getDocid());
 		if(!(userDocument.getDocid().equalsIgnoreCase("-1")))
 			{ 		
 		response.setContentType(userDocument.getDocumentType());
        response.setContentLength(userDocument.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + userDocument.getDocumentName() +"\"");
 
        try {
			FileCopyUtils.copy(userDocument.getContent(), response.getOutputStream());
			
			
		} catch (IOException e) {
		
		logr.error(e);
			
			response.sendRedirect("FileError404?fid="+docId);
		}}
 		else {
 			response.sendRedirect("FileError404?fid="+docId);

 		}
		

 }
     
     @RequestMapping(value = "/downloadReport-{docId}",method = RequestMethod.GET)
     public void  downloadReport(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 ModelMap map,@PathVariable String docId) throws IOException 
     {
    	 if(logr.isDebugEnabled()){
             logr.debug("/downloadReport-{docId} GET method is executed!");
             
         }
    	 
    	

    	 UserDocument userDocument= new UserDocument();
    	 SimpleUploadFile fileModel = new SimpleUploadFile();
 		 map.addAttribute("fileBucket", fileModel);
 		 
 		 userDocument = userDocumentService.getDocumentById(docId,true);
 		 System.out.println("userImageID"+userDocument.getDocid());
 		if(!(userDocument.getDocid().equalsIgnoreCase("-1")))
 			{ 		
 		response.setContentType(userDocument.getDocumentType());
        response.setContentLength(userDocument.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + userDocument.getDocumentName() +"\"");
 
        try {
			FileCopyUtils.copy(userDocument.getContent(), response.getOutputStream());
			
			
		} catch (IOException e) {
		
		logr.error(e);
			
			response.sendRedirect("FileError404?fid="+docId);
		}}
 		else {
 			response.sendRedirect("FileError404?fid="+docId);

 		}
		

 }
     
     
     
     @RequestMapping(value = "/toolsScripts",method = RequestMethod.GET)
     public ModelAndView handleToolsScripts(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 @RequestParam(value = "fid", required = false, defaultValue = "false") String fid) 
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
    	 
    	 List<UserDocument> liDocs = userDocumentService.getAllDocumentsByCategory("script",false);
    	 for(int i =0;i<liDocs.size();i++) {
    		 if(liDocs.get(i).getDocumentName().contains("pdf")) {

  				liDocs.get(i).setIsPdf("yes");
  			}
    	 }
    	 ModelAndView model = new ModelAndView("scriptsTools");
     	
     	model.addObject("listScripts", liDocs);
     	 
     	model.addObject("Message", msg);
     	model.addObject("id", id);
        	 return model;
 	 
 }
     
     @RequestMapping(value = "/processFlow",method = RequestMethod.GET)
     public ModelAndView handleprocessFlow(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 @RequestParam(value = "fid", required = false, defaultValue = "false") String fid) 
     {
    	
    	 if(logr.isDebugEnabled()){
             logr.debug("/processFlow GET method is executed!");
             
    }
    	 String msg=null;
    	 String id=null;
    	 if(!(error.equalsIgnoreCase("false"))) {
    		 msg=error;
    		  
    	 }
    	 
    	 if(!(fid.equalsIgnoreCase("false"))) {
    		id=fid;
    		  
    	 }
    	 
    	 List<UserDocument> liDocs = userDocumentService.getAllDocumentsByCategory("ProcessFlow",false);
    	 for(int i =0;i<liDocs.size();i++) {
    		 if(liDocs.get(i).getDocumentName().contains("pdf")) {

  				liDocs.get(i).setIsPdf("yes");
  			}
    	 }
    	 ModelAndView model = new ModelAndView("processFlow");
     	
     	model.addObject("listProcessFlow", liDocs);
     	 
     	model.addObject("Message", msg);
     	model.addObject("id", id);
        	 return model;
 	 
 }
     
     @RequestMapping(value = "/reports",method = RequestMethod.GET)
     public ModelAndView handleReport(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 @RequestParam(value = "fid", required = false, defaultValue = "false") String fid) 
     {
    	
    	 if(logr.isDebugEnabled()){
             logr.debug("/reports GET method is executed!");
             
    }
    	 String msg=null;
    	 String id=null;
    	 if(!(error.equalsIgnoreCase("false"))) {
    		 msg=error;
    		  
    	 }
    	 
    	 if(!(fid.equalsIgnoreCase("false"))) {
    		id=fid;
    		  
    	 }
    	 
    	 List<UserDocument> liDocs = userDocumentService.getAllDocuments(true);
    	 for(int i =0;i<liDocs.size();i++) {
    		 if(liDocs.get(i).getDocumentName().contains("pdf")) {
    
  				liDocs.get(i).setIsPdf("yes");
  			}
    	 }
    	 ModelAndView model = new ModelAndView("reports");
     	
     	model.addObject("listReports", liDocs);
     	 
     	model.addObject("Message", msg);
     	model.addObject("id", id);
        	 return model;
 	 
 }
     
     @RequestMapping(value = "/uploadReport",method = RequestMethod.GET)
     public String uploadReport(HttpServletRequest request,  HttpServletResponse response,@RequestParam(value = "error", required = false, defaultValue = "false") String error,
    		 ModelMap map,@RequestParam(value = "txt", required = false, defaultValue = "false") String txt) 
     {
    	
    	 if(logr.isDebugEnabled()){
             logr.debug("/uploadReport GET method is executed!");
             
    }
    	 String msg=null;
    	 if(!(error.equalsIgnoreCase("false"))) {
    		 msg=error;
    		  
    	 }
    	 
    	 String upid="false";
    	 if(!(txt.equalsIgnoreCase("false"))) {
    		 upid=txt;
    		 
    		  
    	 }
    	 
    	 
    		httpSession = sessionService.getHttpSession();
    		
    		if(!(sessionService.isUserLoggedIN())){
    			return "redirect:/login?error=You need to login to upload Report";
        		
        	}
      
    	 SimpleUploadFile fileModel = new SimpleUploadFile();
 		 map.addAttribute("fileBucket", fileModel);
 		 map.addAttribute("Message", msg);
 		map.addAttribute("upid", upid);

     	System.out.println("in upload Report GET");
     	
    
     	return "uploadReport";
	 
 }
     
     @RequestMapping(value = { "/uploadReport" }, method = RequestMethod.POST)
  	public String uploadReports(@Valid SimpleUploadFile fileBucket, BindingResult result, ModelMap model) 
  			throws IOException{
     	 if(logr.isDebugEnabled()){
              logr.debug("/uploadReport POST method is executed!");
              
     }
     	 
     	 if(!(sessionService.isUserLoggedIN())){
  			return "redirect:/login?error=You need to login Again ! Some UserId problem.";
      		
      	}
     	 
  		int docid=0;
  		FileValidator filevalidator = new FileValidator();
  		filevalidator.validate(fileBucket, result);
  		if (result.hasErrors()) {
  			//set ? error = error and call
  			
  			return "redirect:/uploadReport?error=Type Or Size OF Uploaded FileIncorrect !!!";
  		} else {
  			
  			System.out.println("Fetching file");
  			
  			
  			UserDocument userDocument = new UserDocument();
  			
  			
  		  	if(sessionService.isUserLoggedIN()){
         		if(httpSession.getAttribute("USERID")!=null) {
         			System.out.println("In ImageController, session's userid is -- "+httpSession.getAttribute("USERID"));
         			userDocument.setUserid(httpSession.getAttribute("USERID")+"");
             	}
         		
         	}
     	 
  			
  			MultipartFile mpfile = fileBucket.getFile();
  			
  			userDocument.setContent(mpfile.getBytes());
  			userDocument.setDocumentCategory(fileBucket.getDocumentCategory());
  			userDocument.setDocumentDescp(fileBucket.getDocumentDescp());
  			userDocument.setDocumentType(mpfile.getContentType());
  			userDocument.setDocumentName(mpfile.getOriginalFilename());

  			
  			docid = userDocumentService.insertDocument(userDocument,true);
  			if(docid!=0){
  				System.out.println("success");
  			  
  				userDocument.setDocid(docid+"");
  				
  				 return "redirect:/uploadReport?error=Successfully uploaded&txt="+userDocument.getDocid();
  				
  			}
  			else{
  				
  				return "redirect:/uploadReport?error=Not uploaded, try again.";
  			}
  		}
      }
     
     
     
     
     
     
     
     
}
     
     
    /* @RequestMapping(value = "/listdocs",method = RequestMethod.GET)
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
     
     
     @ResponseBody
     @RequestMapping(value ="/txt" )
     public String txtResponse(HttpServletResponse response){
         String fileName = "a.txt";
        // response.setHeader("Content-Disposition", "attachment; filename=" + fileName);
         String content = "This is txt content";
         return content;
     }
     */
     
    /* @RequestMapping(value="/getpdf-{docId}", method=RequestMethod.GET)
     public void getPDF(@PathVariable String docId,HttpServletResponse response) {
         
    	 
    	 
    	 System.out.println("in upload image N GET");
    	 System.out.println("input doc id is::"+docId);
    	 

    	 UserImage userImage= new UserImage();
    	 SimpleUploadFile fileModel = new SimpleUploadFile();
 		 
 		 
 		 userImage = userImageService.getImageById(docId);
 		 System.out.println("userImageID"+userImage.getImageid());
 		
 		//response.setContentType(userImage.g);
        response.setContentLength(userImage.getContent().length);
        response.setHeader("Content-Disposition","attachment; filename=\"" + userImage.getImagename() +"\"");
 
        try {
			FileCopyUtils.copy(userImage.getContent(), response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        */
        
// 		if(userImage.getImageid().equalsIgnoreCase("-1")) {
// 			
// 			ModelAndView model = new ModelAndView("ImageError404");
//       	    model.addObject("id", docId);
//       	 
//       	    return model;
// 		}
     	
     	
     	
     
       // response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
     
         // retrieve contents of "C:/tmp/report.pdf" that were written in showHelp
        // byte[] contents = userImage.getContent();

//         HttpHeaders headers = new HttpHeaders();
//         headers.setContentType(MediaType.parseMediaType("application/pdf"));
//         String filename = userImage.getImagename();
//         headers.setContentDispositionFormData(filename, filename);
//         headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
//         response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
        // ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
         //return response;
     //}
     
  /*   
     @RequestMapping(value="/getpdf2-{docId}", method=RequestMethod.GET)
     public ResponseEntity<byte[]> getPDF2(@PathVariable String docId) {
         
    	 
    	 
    	 System.out.println("in upload image N GET");
    	 System.out.println("input doc id is::"+docId);
    	 

    	 UserImage userImage= new UserImage();
    	 SimpleUploadFile fileModel = new SimpleUploadFile();
 		 
 		 
 		 userImage = userImageService.getImageById(docId);
 		 System.out.println("userImageID"+userImage.getImageid());
// 		if(userImage.getImageid().equalsIgnoreCase("-1")) {
// 			
// 			ModelAndView model = new ModelAndView("ImageError404");
//       	    model.addObject("id", docId);
//       	 
//       	    return model;
// 		}
     	
     	
     	
     
       // response.setHeader("Content-Disposition","attachment; filename=\"" + document.getName() +"\"");
     
         // retrieve contents of "C:/tmp/report.pdf" that were written in showHelp
         byte[] contents = userImage.getContent();

         HttpHeaders headers = new HttpHeaders();
         headers.setContentType(MediaType.parseMediaType("application/pdf"));
         String filename = userImage.getImagename();
         //headers.setContentDispositionFormData(filename, filename);
         headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
         ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(contents, headers, HttpStatus.OK);
         return response;
     }*/
     
     

     
     
