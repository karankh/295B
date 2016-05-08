package edu.sjsu.WebPortal.lab.dao;

import java.util.List;

import edu.sjsu.WebPortal.lab.MySQL.*;
/**
 * UserDAO interface
 * @author Karan
 *
 */
public interface UserDocumentDAO {
 
	public int insertDocument(UserDocument userDocument,boolean flag);//using
	public UserDocument getDocumentById(String Docid,boolean flag);//using
	public List<UserDocument> getAllDocuments(boolean flagU);
	public boolean deleteDocumentById(String Docid,boolean flag);//using
	public List<UserDocument> getAllDocumentsByUserId(String userId,boolean flag);//using
	public List<UserDocument> searchDocumentsByText(String text,boolean flag);//using
	public List<UserDocument> getAllDocumentsByCategory(String category,boolean flag);//using
	public List<UserDocument> searchScriptsProcessFlowsByText(String text);
	public List<UserDocument> searchReportsByText(String text);
}