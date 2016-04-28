package edu.sjsu.WebApp.labservices;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.WebApp.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebApp.lab.MySQL.UserImage;
import edu.sjsu.WebApp.lab.MySQL.UserPageModel;
import edu.sjsu.WebApp.lab.dao.UserDAO;
import edu.sjsu.WebApp.lab.dao.UserImageDAO;

/**
 * Implementation of UserRecordService
 * @author karan
 *
 */
@Service("userImageService")
public class UserImageServiceImpl implements UserImageService {
    @Autowired
    UserImageDAO userImageDAO;
 
  

	@Override
	public int insertImage(UserImage userImage) {
		return userImageDAO.insertImage(userImage);
	}

	@Override
	public UserImage getImageById(String Imageid) {
		return userImageDAO.getImageById(Imageid);
	}

	@Override
	public List<UserImage> getAllImages() {
		return userImageDAO.getAllImages();
	}

	@Override
	public boolean deleteImageById(String Imageid) {
		return userImageDAO.deleteImageById(Imageid);
	}

	@Override
	public List<UserImage> getAllImagesByUserId(String userId) {
		return userImageDAO.getAllImagesByUserId(userId);
	}

	@Override
	public List<UserImage> searchImagesByText(String text) {
		return userImageDAO.searchImagesByText(text);
	}
 
}