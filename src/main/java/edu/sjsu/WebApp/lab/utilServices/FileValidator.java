package edu.sjsu.WebApp.lab.utilServices;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
 
import edu.sjsu.WebApp.lab.MySQL.*;
 
@Component
public class FileValidator implements Validator {
     
	
	private int size = 5120000;
	private String img_type="jpg|jpegpng|gif|bmp";
	
	@Override
    public boolean supports(Class<?> arg0) {
        return SimpleUploadFile.class.equals(arg0);
    }
 
	@Override
    public void validate(Object arg0, Errors arg1) {
    	SimpleUploadFile file = (SimpleUploadFile) arg0;
         
        if(file.getFile()!=null){
            if (file.getFile().getSize() == 0) {
                arg1.rejectValue("file", "file missing");
            }
            else if (file.getFile().getSize() > size) {
                arg1.rejectValue("file", "file too large, max size allowed is - 5MB");
            }
            else if (!checkFileType(file.getFile().getOriginalFilename())) {
                arg1.rejectValue("file", "only images supported!");
            }
        }
    }

	private boolean checkFileType(String originalFilename) {
		
		if(originalFilename.length()>0) {
			String[] parts = originalFilename.split("\\.");
			if(parts.length>0) {
				String extension = parts[parts.length -1];
				return this.img_type.contains(extension);
			}
		}
		
		return false;
	}
}