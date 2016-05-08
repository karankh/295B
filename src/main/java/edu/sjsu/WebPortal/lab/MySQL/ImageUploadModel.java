package edu.sjsu.WebPortal.lab.MySQL;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

/**
 * This model class contains the properties for the filed on the login.jsp page.
 * @author karan
 *
 */

public class ImageUploadModel {
 
	
	private List<String> li =new ArrayList<String>();
	 
	
	private SimpleUploadFile simpleUploadFile;
	
	private List<UserImage> liUserImages = new ArrayList<UserImage>();

	public List<UserImage> getLiUserImages() {
		return liUserImages;
	}

	public void setLiUserImages(List<UserImage> liUserImages) {
		this.liUserImages = liUserImages;
	}

	public SimpleUploadFile getSimpleUploadFile() {
		return simpleUploadFile;
	}

	public void setSimpleUploadFile(SimpleUploadFile simpleUploadFile) {
		this.simpleUploadFile = simpleUploadFile;
	}
}