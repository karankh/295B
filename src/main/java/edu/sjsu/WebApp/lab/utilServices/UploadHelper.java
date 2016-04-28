package edu.sjsu.WebApp.lab.utilServices;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

public class UploadHelper {
	
	
	@Autowired
	private HttpSession httpSession;
    
	@Autowired
	private CheckSession sessionService;
	
public  String simpleUpload(MultipartFile file,
		HttpServletRequest request, boolean encrypt_file_name,
		String upload_folder) {
	
	String filename = null;
	try {
		if(!file.isEmpty()) {
			httpSession = sessionService.getHttpSession();
			String applicationPath = httpSession.getServletContext().getRealPath("");
			if(encrypt_file_name) {
				String currentFileName=file.getOriginalFilename();
				String extension = currentFileName.substring(
						currentFileName.lastIndexOf("."), currentFileName.length());
				Long nameRandom = Calendar.getInstance().getTimeInMillis();
				String newfilename = nameRandom+extension;
				filename=newfilename;
				
			} else
				filename=file.getOriginalFilename();
			byte[] bytes=file.getBytes();
			String rootPath = applicationPath;
			File dir = new File(rootPath + File.separator+
					upload_folder);
			if(!dir.exists())
				dir.mkdirs();
			File serverFile = new File(dir.getAbsolutePath()
					+File.separator+filename);
			BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));
			stream.write(bytes);
			stream.close();
			return filename;
			
		} else {
			filename = null;
		   }
	    } catch (Exception e) {
	filename = null;
}
return filename;
	
}
}
