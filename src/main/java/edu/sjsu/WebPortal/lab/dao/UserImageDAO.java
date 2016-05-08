package edu.sjsu.WebPortal.lab.dao;

import java.util.List;

import edu.sjsu.WebPortal.lab.MySQL.*;
/**
 * UserDAO interface
 * @author Karan
 *
 */
public interface UserImageDAO {
 
	public int insertImage(UserImage userImage);
	public UserImage getImageById(String Imageid);
	public List<UserImage> getAllImages();
	public boolean deleteImageById(String Imageid);
	public List<UserImage> getAllImagesByUserId(String userId);
	public List<UserImage> searchImagesByText(String text);
}