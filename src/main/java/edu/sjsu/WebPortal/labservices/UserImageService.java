package edu.sjsu.WebPortal.labservices;


import java.util.List;

import edu.sjsu.WebPortal.lab.MySQL.UserImage;


/**
 * UserRecordService Interface
 * @author Karan
 *
 */
public interface UserImageService {
 
	public int insertImage(UserImage userImage);
	public UserImage getImageById(String Imageid);
	public List<UserImage> getAllImages();
	public boolean deleteImageById(String Imageid);
	public List<UserImage> getAllImagesByUserId(String userId);
	public List<UserImage> searchImagesByText(String text);
	
}