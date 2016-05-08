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
import edu.sjsu.WebPortal.lab.MySQL.UserImage;
import edu.sjsu.WebPortal.lab.MySQL.UserPageModel;


 
/**
 * Implementation of the UserImageDAO Interface
 * @author Karan
 */
@SuppressWarnings("unused")
@Repository("userImageDAO")
public class UserImageDAOImpl implements UserImageDAO {

	
	
	/*
	 * This method inserts the record of user in db
	 * */
		public int insertUser(SignUpPageModel signUpPageModel) {
				System.out.println("I am in creatuser method");
				
				String SQL = "INSERT  INTO cuser_details (firstname, lastname, email, passwrd) values (?,?,?,?);";
			   
			    int id=0;
			   
		       
		        try
		        {
		        	
		           
		            PreparedStatement ps= ConnectionDAO.getConnection().prepareStatement(SQL);
		            ps.setString(1, signUpPageModel.getFirstname());
		            ps.setString(2, signUpPageModel.getLastname());
		            ps.setString(3, signUpPageModel.getEmail());
		            ps.setString(4, signUpPageModel.getPasswrd());
		          
		            
		            	
		            	if(ps.executeUpdate()==1);
		            	{
		            		
		            			System.out.println("insertion done in db");
		            			ResultSet rs = ps.getGeneratedKeys();
		            			
		            			if(rs!=null && rs.next())
		            				{
		            					id= rs.getInt(1);
		            					System.out.println("gen id is-"+id);
		            				}
		            		
		            	signUpPageModel.setId(id+"");
		            	signUpPageModel.setUsertype("nonadmin");
		            			return 1;
		            	}
		            	
		           
		           
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		           
		            return 0;
		        }
				
			}
			
			/*
			 * This method retrieves  the record of user from db
			 * */
			public UserPageModel getUser(UserPageModel userPageModel) {
				System.out.println("I m in getUser method");
				String SQL = "SELECT * FROM cuser_details WHERE userid = '"+userPageModel.getId()+"';";
		       
		        Statement stmt;
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            if (null!= rs && rs.next())
		            {
		            	System.out.println("user firstname is: " + rs.getString("firstname"));
		            	userPageModel.setFirstname(rs.getString("firstname"));
		            	userPageModel.setLastname(rs.getString("lastname"));
		            	userPageModel.setEmail(rs.getString("email"));
		            	userPageModel.setPasswrd(rs.getString("passwrd"));
		            	userPageModel.setIsUserPresent(true);
		            
		                return userPageModel;
		            }
		            else
		            {
		            	
		            	userPageModel.setIsUserPresent(false);
		                return userPageModel;
		            }
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            
		            return null;
		        }
				
			}
			
			
			/*
			 * This method retrieves  the record of user from db
			 * */
			public UserPageModel isUserPresentById(UserPageModel userPageModel) {
				System.out.println("I m in getUser method");
				String SQL = "SELECT * FROM cuser_details WHERE userid = '"+userPageModel.getId()+"';";
		       
		        Statement stmt;
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            if (null!= rs && rs.next())
		            {
		            	userPageModel.setIsUserPresent(true);
		            
		                return userPageModel;
		            }
		            else
		            {
		            	
		            	userPageModel.setIsUserPresent(false);
		                return userPageModel;
		            }
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            userPageModel.setIsUserPresent(false);
		            return null;
		        }
				
			}
			
			
			
			
			
			/*
			 * This method retrieves  the record of user from db by user 's email
			 * */
			public SignUpPageModel getUserbyEmail(SignUpPageModel signUpPageModel) {
				System.out.println("I m in getUserbyEmail method");
				String SQL = "SELECT * FROM cuser_details WHERE email = '"+signUpPageModel.getEmail()+"';";
		       
		        Statement stmt;
		        try
		        {
		            stmt = ConnectionDAO.getStatement();
		            ResultSet rs = stmt.executeQuery(SQL);
		            if (null!= rs && rs.next())
		            {
		            	
		            	signUpPageModel.setFirstname(rs.getString("firstname"));
		            	signUpPageModel.setLastname(rs.getString("lastname"));
		            	signUpPageModel.setEmail(rs.getString("email"));
		            	signUpPageModel.setPasswrd(rs.getString("passwrd"));
		            	signUpPageModel.setIsUserAlreadyRegistered(true);
		            	signUpPageModel.setUsertype(rs.getString("usertype"));
		            
		                return signUpPageModel;
		            }
		            else
		            {
		            	signUpPageModel.setIsUserAlreadyRegistered(false);
		   
		                return signUpPageModel;
		            }
		        }
		        catch (SQLException e)
		        {
		            System.out.println("SQL Exception : "+e.getMessage());
		            
		            return null;
		        }
				
			}
			
			
			/*
			 * This method checks if user exists or not.
			 * */
			public boolean isUserThere(SignUpPageModel signUpPageModel) {
				System.out.println("email id passed --"+signUpPageModel.getEmail());
				
				Boolean isUserPresent = getUserbyEmail(signUpPageModel).getIsUserAlreadyRegistered();
				System.out.println("fname returned -- "+signUpPageModel.getFirstname());
				if(isUserPresent)
				{
					return true;
				}
				return false;
				
				
			}
			
			
			
			
			
			
			
			/*
			 * This method deletes the record of user in db
			 * */
			public boolean deleteUser(UserPageModel userPageModel) {
				System.out.println("I m in delUser method");
				
				String SQL = "DELETE FROM cuser_details WHERE userid = '"+userPageModel.getId()+"';";
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
			
			
			/*
			 * This method updates the record of user in db
			 * */
			public int updateUser(UserPageModel userPageModel)
			{
			String SQL2 = "UPDATE cuser_details set firstname = ?, lastname = ?, email = ? where userid = ? ;";
		
		       
	        try
	        {
	        	System.out.println("in db update user method");
	        	
	        	
	           
	           
	            PreparedStatement ps= ConnectionDAO.getConnection().prepareStatement(SQL2);
	            ps.setString(1, userPageModel.getFirstname());
	            ps.setString(2, userPageModel.getLastname());
	            ps.setString(3, userPageModel.getEmail());
	            ps.setString(4, userPageModel.getId());
	           
	           
	            if (ps.executeUpdate()==1)
	            {
	            	System.out.println("user record updation  done in db");
	            	
	            	
	            	return 1;
	                
	            }
	            else
	            {
	            	return 0;
	            }
	            
	           
	        }
	        catch (SQLException e)
	        {
	        	System.out.println("catch in user details updation");
	            System.out.println("SQL Exception : "+e.getMessage());
	            return 0;
	           
	            
	        }
}

			@Override
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
}