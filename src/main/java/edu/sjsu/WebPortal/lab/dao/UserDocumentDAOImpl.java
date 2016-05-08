package edu.sjsu.WebPortal.lab.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.sjsu.WebPortal.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebPortal.lab.MySQL.UserDocument;
import edu.sjsu.WebPortal.lab.MySQL.UserImage;
import edu.sjsu.WebPortal.lab.MySQL.UserPageModel;


 
/**
 * Implementation of the UserDocumentDAO Interface
 * @author Karan
 */
@SuppressWarnings("unused")
@Repository("userDocumentDAO")
public class UserDocumentDAOImpl implements UserDocumentDAO {

	
	
			/*@Override
			public int insertImage(UserImage userImage) {
				
				System.out.println("I am in insertImage method");
				
				String SQL = "INSERT  INTO cimages_details (imagedesc, imagename, userid, imagecontent) values (?,?,?,?);";
			   
			    int id=0;
			   
		       
		        try
		        {
		        	
		           
		            PreparedStatement ps= ConnectionDAO.getConnection().prepareStatement(SQL);
		            ps.setString(1, userImage.getImagedesc());
		            ps.setString(2, userImage.getImagename());
		            ps.setString(3, userImage.getUserid());
		            ps.setBytes(4, userImage.getContent());
		          
		            
		            	
		            	if(ps.executeUpdate()==1);
		            	{
		            		
		            			System.out.println("insertion done in db");
		            			ResultSet rs = ps.getGeneratedKeys();
		            			
		            			if(rs!=null && rs.next())
		            				{
		            					id= rs.getInt(1);
		            					System.out.println("gen id is-"+id);
		            				}
		            		
		            			userImage.setImageid(id+""); 
		            			return 1;
		            	}
		            	
		           
		           
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            userImage.setImageid("-1"); 
		            return 0;
		        }
			}

			@Override
			public UserImage getImageById(String Imageid) {
				System.out.println("I m in getImageById method");
				String SQL = "SELECT * FROM cimages_details WHERE imageid = '"+Imageid+"';";
				UserImage userimage = new UserImage();
		        Statement stmt;
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            if (null!= rs && rs.next())
		            {
		            	
		            	userimage.setUserid(rs.getInt("userid")+"");
		            	userimage.setContent(rs.getBytes("imagecontent"));
		            	userimage.setImagedesc(rs.getString("imagedesc"));
		            	userimage.setImagename(rs.getString("imagename"));
		            	userimage.setImageid(Imageid);
		            	System.out.println("in db class -"+userimage.getImageid());
		            
		            
		                return userimage;
		            }
		            else
		            {
		            	
		            	 userimage.setImageid("-1");
		                return userimage;
		            }
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            userimage.setImageid("-1");
		            return userimage;
		        }
			}

			@Override
			public List<UserImage> getAllImages() {
				System.out.println("I m in getAllImages method");
				String SQL = "SELECT * FROM cimages_details;";
				boolean  flag=false;
		        Statement stmt;
		        
		        List<UserImage> liUserImages = new ArrayList<UserImage>();
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            
		            while (null!= rs && rs.next())
		            {
		            	UserImage userimage = new UserImage();
		            	userimage.setUserid(rs.getInt("userid")+"");
		            	userimage.setContent(rs.getBytes("imagecontent"));
		            	userimage.setImagedesc(rs.getString("imagedesc"));
		            	userimage.setImagename(rs.getString("imagename"));
		            	userimage.setImageid(rs.getInt("imageid")+"");
		            
		            	liUserImages.add(userimage);
		            	  if(!flag) {
		            		  flag=true;
				            	
			            	}
		                
		            }
		            
		            if(!flag) {
		            	liUserImages.add(null);
		            	
	            	}
		            return liUserImages;
		           
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            liUserImages.add(null);
		            return liUserImages;
		        }
			}

			@Override
			public boolean deleteImageById(String Imageid) {
						System.out.println("I m in deleteImageById method");
				
				String SQL = "DELETE FROM cimages_details WHERE imageid = '"+Imageid+"';";
		        System.out.println("SQL : "+ SQL);
		        Statement stmt;
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            
		            stmt.execute(SQL);
		            
		            return true;
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            
		            return false;
		        }
			}

			@Override
			public List<UserImage> getAllImagesByUserId(String userId) {
				System.out.println("I m in getAllImagesByUserId method");
				String SQL = "SELECT * FROM cimages_details WHERE userid = '"+userId+"';";
				boolean flag=false;
		        Statement stmt;
		        List<UserImage> liUserImages = new ArrayList<UserImage>();
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            while (null!= rs && rs.next())
		            {
		            	UserImage userimage = new UserImage();
		            	userimage.setUserid(rs.getInt("userid")+"");
		            	userimage.setContent(rs.getBytes("imagecontent"));
		            	userimage.setImagedesc(rs.getString("imagedesc"));
		            	userimage.setImagename(rs.getString("imagename"));
		            	userimage.setImageid(rs.getInt("imageid")+"");
		            
		            	liUserImages.add(userimage);
		            	if(!flag) {
		            		flag=true;
		            	}
		            	
		                
		            }
		            
		            if(!flag) {
		            	liUserImages.add(null);
		            	
	            	}
		            return liUserImages;
		           
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            liUserImages.add(null);
		            return liUserImages;
		        }
			}

			@Override
			public List<UserImage> searchImagesByText(String text) {
				System.out.println("I m in searchImagesByText method");
				String SQL = "SELECT * FROM cloudAppdb.cimages_details WHERE imagedesc LIKE '%"+text+"%' or imagename LIKE '%"+text+"%' ;";
				boolean flag=false;
		        Statement stmt;
		        List<UserImage> liUserImages = new ArrayList<UserImage>();
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            while (null!= rs && rs.next())
		            {
		            	UserImage userimage = new UserImage();
		            	userimage.setUserid(rs.getInt("userid")+"");
		            	userimage.setContent(rs.getBytes("imagecontent"));
		            	userimage.setImagedesc(rs.getString("imagedesc"));
		            	userimage.setImagename(rs.getString("imagename"));
		            	userimage.setImageid(rs.getInt("imageid")+"");
		            
		            	liUserImages.add(userimage);
		            	if(!flag) {
		            		flag=true;
		            	}
		            	
		                
		            }
		            
		            if(!flag) {
		            	liUserImages.add(null);
		            	
	            	}
		            return liUserImages;
		           
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            liUserImages.add(null);
		            return liUserImages;
		        }
			}
*/
			@Override
			public int insertDocument(UserDocument userDocument,boolean flag) {

				
				System.out.println("I am in insertDocument method");
				
				String SQL=null;
				if(flag) {
					SQL = "INSERT  INTO creports_details (repdesc, repname, userid, repcontent,reptype,repcateg) values (?,?,?,?,?,?);";
				}
				else {
					SQL = "INSERT  INTO cdocs_details (docdesc, docname, userid, doccontent,doctype,doccateg) values (?,?,?,?,?,?);";
				}
				
			 
			    int id=0;
			    
		
		        try
		        {
		        	
		           
		            PreparedStatement ps= ConnectionDAO.getConnection().prepareStatement(SQL);
		            ps.setString(1, userDocument.getDocumentDescp());
		            ps.setString(2, userDocument.getDocumentName());
		            ps.setString(3, userDocument.getUserid());
		            ps.setBytes(4, userDocument.getContent());
		            ps.setString(5, userDocument.getDocumentType());
		            ps.setString(6, userDocument.getDocumentCategory());
		          
		            
		            	
		            	if(ps.executeUpdate()==1);
		            	{
		            		
		            			System.out.println("insertion done in db");
		            			ResultSet rs = ps.getGeneratedKeys();
		            			
		            			if(rs!=null && rs.next())
		            				{
		            					id= rs.getInt(1);
		            					System.out.println("gen id is-"+id);
		            				}
		            		
		            			userDocument.setDocid(id+""); 
		            			return id;
		            	}
		            	
		           
		           
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            userDocument.setDocid("-1"); 
		            return 0;
		        }
			}

			@Override
			public UserDocument getDocumentById(String DocId,boolean flag) {
				System.out.println("I m in getDocumentById method");
				
				String SQL=null;
				String userid=null;
				String doccontent=null;
				String docdesc=null;
				String doctype=null;
				String doccateg=null;
				String docid=null;
				String docname=null;
				if(flag) {
					SQL = "SELECT * FROM creports_details WHERE repid = '"+DocId+"';";
					userid="userid";
					doccontent="repcontent";
					docdesc="repdesc";
					doctype="reptype";
					doccateg="repcateg";
					docid="repid";
					docname="repname";
				}
				else {
					SQL = "SELECT * FROM cdocs_details WHERE docid = '"+DocId+"';";
					userid="userid";
					doccontent="doccontent";
					docdesc="docdesc";
					doctype="doctype";
					doccateg="doccateg";
					docid="docid";
					docname="docname";
				}
				 
				UserDocument userDocument = new UserDocument();
		        Statement stmt;
		        
		       
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            if (null!= rs && rs.next())
		            {
		            	
		            	
		            	userDocument.setUserid(rs.getInt(userid)+"");
		            	userDocument.setContent(rs.getBytes(doccontent));
		            	userDocument.setDocumentDescp(rs.getString(docdesc));
		            	userDocument.setDocumentName(rs.getString(docname));
		            	userDocument.setDocumentType(rs.getString(doctype));
		            	userDocument.setDocumentCategory(rs.getString(doccateg));
		            	userDocument.setDocid(rs.getInt(docid)+"");
		            	
		            
		            
		                return userDocument;
		            }
		            else
		            {
		            	
		            	userDocument.setDocid("-1");
		                return userDocument;
		            }
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            userDocument.setDocid("-1");
		            return userDocument;
		        }
			}

			@Override
			public List<UserDocument> getAllDocuments(boolean flagU) {
				System.out.println("I m in getAllDocuments method");
				
				String SQL = null;
				
				String userid=null;
				String doccontent=null;
				String docdesc=null;
				String doctype=null;
				String doccateg=null;
				String docid=null;
				String docname=null;
			
				
				if(flagU) {
					SQL = "SELECT * FROM creports_details ;"; 
					
					userid="userid";
					doccontent="repcontent";
					docdesc="repdesc";
					doctype="reptype";
					doccateg="repcateg";
					docid="repid";
					docname="repname";
				}
				else {
					SQL = "SELECT * FROM cdocs_details ;";
					userid="userid";
					doccontent="doccontent";
					docdesc="docdesc";
					doctype="doctype";
					doccateg="doccateg";
					docid="docid";
					docname="docname";
				}
				
				boolean flag=false;
		        Statement stmt;
		        List<UserDocument> liUserDocuments = new ArrayList<UserDocument>();
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            while (null!= rs && rs.next())
		            {
		            	UserDocument userDocument = new UserDocument();
		            	userDocument.setUserid(rs.getInt(userid)+"");
		            	userDocument.setContent(rs.getBytes(doccontent));
		            	userDocument.setDocumentDescp(rs.getString(docdesc));
		            	userDocument.setDocumentName(rs.getString(docname));
		            	userDocument.setDocumentType(rs.getString(doctype));
		            	userDocument.setDocumentCategory(rs.getString(doccateg));
		            	userDocument.setDocid(rs.getInt(docid)+"");
		            
		            	liUserDocuments.add(userDocument);
		            	if(!flag) {
		            		flag=true;
		            	}
		            	
		                
		            }
		            
		            if(!flag) {
		            	liUserDocuments.add(null);
		            	
	            	}
		            return liUserDocuments;
		           
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            liUserDocuments.add(null);
		            return liUserDocuments;
		        }
					
			}

			@Override
			public boolean deleteDocumentById(String Imageid,boolean flag) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public List<UserDocument> getAllDocumentsByUserId(String userId,boolean flag) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<UserDocument> searchDocumentsByText(String text,boolean flag) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public List<UserDocument> getAllDocumentsByCategory(String category,boolean flagU) {
				System.out.println("I m in getAllDocumentsByCategory method");
				
				String SQL = null;
				
				String userid=null;
				String doccontent=null;
				String docdesc=null;
				String doctype=null;
				String doccateg=null;
				String docid=null;
				String docname=null;
			
				
				if(flagU) {
					SQL = "SELECT * FROM creports_details WHERE repcateg = '"+category+"';";
					userid="userid";
					doccontent="repcontent";
					docdesc="repdesc";
					doctype="reptype";
					doccateg="repcateg";
					docid="repid";
					docname="repname";
				}
				else {
					SQL = "SELECT * FROM cdocs_details WHERE doccateg = '"+category+"';";
					userid="userid";
					doccontent="doccontent";
					docdesc="docdesc";
					doctype="doctype";
					doccateg="doccateg";
					docid="docid";
					docname="docname";
				}
				
				boolean flag=false;
		        Statement stmt;
		        List<UserDocument> liUserDocuments = new ArrayList<UserDocument>();
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            while (null!= rs && rs.next())
		            {
		            	UserDocument userDocument = new UserDocument();
		            	userDocument.setUserid(rs.getInt(userid)+"");
		            	userDocument.setContent(rs.getBytes(doccontent));
		            	userDocument.setDocumentDescp(rs.getString(docdesc));
		            	userDocument.setDocumentName(rs.getString(docname));
		            	userDocument.setDocumentType(rs.getString(doctype));
		            	userDocument.setDocumentCategory(rs.getString(doccateg));
		            	userDocument.setDocid(rs.getInt(docid)+"");
		            
		            	liUserDocuments.add(userDocument);
		            	if(!flag) {
		            		flag=true;
		            	}
		            	
		                
		            }
		            
		            if(!flag) {
		            	liUserDocuments.add(null);
		            	
	            	}
		            return liUserDocuments;
		           
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            liUserDocuments.add(null);
		            return liUserDocuments;
		        }
			}
			
			
			@Override
			public List<UserDocument> searchScriptsProcessFlowsByText(String text) {
				System.out.println("I m in searchScriptsProcessFlowsByText method");
				String SQL = "SELECT * FROM cdocs_details WHERE docdesc LIKE '%"+text+"%' or docname LIKE '%"+text+"%' ;";
				boolean flag=false;
		        Statement stmt;
		        List<UserDocument> liUserDocuments = new ArrayList<UserDocument>();
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            while (null!= rs && rs.next())
		            {
		            	UserDocument userDocument = new UserDocument();
		            	userDocument.setUserid(rs.getInt("userid")+"");
		            	userDocument.setContent(rs.getBytes("doccontent"));
		            	userDocument.setDocumentDescp(rs.getString("docdesc"));
		            	userDocument.setDocumentName(rs.getString("docname"));
		            	userDocument.setDocumentType(rs.getString("doctype"));
		            	userDocument.setDocumentCategory(rs.getString("doccateg"));
		            	userDocument.setDocid(rs.getInt("docid")+"");
		            
		            	liUserDocuments.add(userDocument);
		            	if(!flag) {
		            		flag=true;
		            	}
		            	
		                
		            }
		            
		            if(!flag) {
		            	liUserDocuments.add(null);
		            	
	            	}
		            return liUserDocuments;
		           
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            liUserDocuments.add(null);
		            return liUserDocuments;
		        }
			}
			
			
			
			
			@Override
			public List<UserDocument> searchReportsByText(String text) {
				System.out.println("I m in searchReportsByText method");
				String SQL = "SELECT * FROM creports_details WHERE repdesc LIKE '%"+text+"%' or repname LIKE '%"+text+"%' ;";
				boolean flag=false;
		        Statement stmt;
		        List<UserDocument> liUserDocuments = new ArrayList<UserDocument>();
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            while (null!= rs && rs.next())
		            {
		            	UserDocument userDocument = new UserDocument();
		            	userDocument.setUserid(rs.getInt("userid")+"");
		            	userDocument.setContent(rs.getBytes("repcontent"));
		            	userDocument.setDocumentDescp(rs.getString("repdesc"));
		            	userDocument.setDocumentName(rs.getString("repname"));
		            	userDocument.setDocumentType(rs.getString("reptype"));
		            	userDocument.setDocumentCategory(rs.getString("repcateg"));
		            	userDocument.setDocid(rs.getInt("repid")+"");
		            
		            	liUserDocuments.add(userDocument);
		            	if(!flag) {
		            		flag=true;
		            	}
		            	
		                
		            }
		            
		            if(!flag) {
		            	liUserDocuments.add(null);
		            	
	            	}
		            return liUserDocuments;
		           
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            liUserDocuments.add(null);
		            return liUserDocuments;
		        }
			}
}