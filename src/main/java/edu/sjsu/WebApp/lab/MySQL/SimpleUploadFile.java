package edu.sjsu.WebApp.lab.MySQL;


import org.springframework.web.multipart.MultipartFile;


public class SimpleUploadFile {

 
    private MultipartFile file;
   private String imagedesc;
 
    public MultipartFile getFile() {
        return file;
    }
 
    public void setFile(MultipartFile file) {
        this.file = file;
    }

	public String getImagedesc() {
		return imagedesc;
	}

	public void setImagedesc(String imagedesc) {
		this.imagedesc = imagedesc;
	}
    
    
}
