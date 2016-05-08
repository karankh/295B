package edu.sjsu.WebPortal.lab.MySQL;



public class UserImage {



	private String userid;
	 
	   
    private String imageid;
    

    private String imagedesc;
    
    private String imagename;
    
    
    private byte[] content;
    
    private String imagerepresentation;


	public String getUserid() {
		return userid;
	}


	public void setUserid(String userid) {
		this.userid = userid;
	}


	public String getImageid() {
		return imageid;
	}


	public void setImageid(String imageid) {
		this.imageid = imageid;
	}


	public String getImagedesc() {
		return imagedesc;
	}


	public void setImagedesc(String imagedesc) {
		this.imagedesc = imagedesc;
	}


	public byte[] getContent() {
		return content;
	}


	public void setContent(byte[] content) {
		this.content = content;
	}


	public String getImagename() {
		return imagename;
	}


	public void setImagename(String imagename) {
		this.imagename = imagename;
	}


	public String getImagerepresentation() {
		return imagerepresentation;
	}


	public void setImagerepresentation(String imagerepresentation) {
		this.imagerepresentation = imagerepresentation;
	}
    
    
	
}
