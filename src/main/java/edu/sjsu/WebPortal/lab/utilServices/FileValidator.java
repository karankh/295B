package edu.sjsu.WebPortal.lab.utilServices;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
 
import edu.sjsu.WebPortal.lab.MySQL.*;
 
@Component
public class FileValidator implements Validator {
     
	
	private int size = 51200000; //50 Mb
	private String file_type=".exe";
	
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
                arg1.rejectValue("file", "file too large, max size allowed is - 50MB");
            }
            else if (checkFileType(file.getFile().getOriginalFilename())) {
                arg1.rejectValue("file", "no .exes are  supported!");
            }
        }
    }

	private boolean checkFileType(String originalFilename) {
		
		if(originalFilename.length()>0) {
			String[] parts = originalFilename.split("\\.");
			if(parts.length>0) {
				String extension = parts[parts.length -1];
				return this.file_type.contains(extension);
			}
		}
		
		return false;
	}
}