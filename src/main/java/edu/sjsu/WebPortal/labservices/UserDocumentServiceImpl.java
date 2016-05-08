package edu.sjsu.WebPortal.labservices;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.sjsu.WebPortal.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebPortal.lab.MySQL.UserDocument;
import edu.sjsu.WebPortal.lab.MySQL.UserImage;
import edu.sjsu.WebPortal.lab.MySQL.UserPageModel;
import edu.sjsu.WebPortal.lab.dao.UserDAO;
import edu.sjsu.WebPortal.lab.dao.UserDocumentDAO;
import edu.sjsu.WebPortal.lab.dao.UserImageDAO;

/**
 * Implementation of UserRecordService
 * @author karan
 *
 */
@Service("userDocumentService")
public class UserDocumentServiceImpl implements UserDocumentService {
    @Autowired
    UserDocumentDAO userDocumentDAO;
 
  

	@Override
	public int insertDocument(UserDocument userDocument,boolean flag) {
		return userDocumentDAO.insertDocument(userDocument,flag);
	}

	@Override
	public UserDocument getDocumentById(String Docid,boolean flag) {
		return userDocumentDAO.getDocumentById(Docid,flag);
	}

	@Override
	public List<UserDocument> getAllDocuments(boolean flagU) {
		return userDocumentDAO.getAllDocuments(flagU);
	}

	@Override
	public boolean deleteDocumentById(String Docid,boolean flag) {
		return userDocumentDAO.deleteDocumentById(Docid,flag);
	}

	@Override
	public List<UserDocument> getAllDocumentsByUserId(String userId,boolean flag) {
		return userDocumentDAO.getAllDocumentsByUserId(userId,flag);
	}

	@Override
	public List<UserDocument> searchDocumentsByText(String text,boolean flag) {
		return userDocumentDAO.searchDocumentsByText(text,flag);
	}

	@Override
	public List<UserDocument> getAllDocumentsByCategory(String category,boolean flag) {
		return userDocumentDAO.getAllDocumentsByCategory(category,flag);
	}

	@Override
	public List<UserDocument> searchScriptsProcessFlowsByText(String text) {
		return userDocumentDAO.searchScriptsProcessFlowsByText(text);
	}

	@Override
	public List<UserDocument> searchReportsByText(String text) {
		return userDocumentDAO.searchReportsByText(text);
	}
 
}