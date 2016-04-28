package edu.sjsu.WebApp.lab.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.sjsu.WebApp.lab.MySQL.LoginModel;
import edu.sjsu.WebApp.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebApp.lab.MySQL.UserPageModel;
import edu.sjsu.WebApp.lab.utilServices.PlayPP;


 
/**
 * Implementation of the LoginDao Interface
 * @author Karan
 */
@SuppressWarnings("unused")
@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {

	
	
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

			public LoginModel validate(LoginModel loginmodel) {
					
					int useridReturned=0;
					
			        String SQL = "SELECT * FROM cuser_details WHERE email = '"+loginmodel.getEmail()+"' and passwrd = '"+loginmodel.getPasswrd()+"';";
			        System.out.println("SQL : "+ SQL);
			        Statement stmt;
			        try
			        {
			            stmt = ConnectionDAO.getStatement();
			            ResultSet rs = stmt.executeQuery(SQL);
			            if (null!= rs && rs.next())
			            {
			            	System.out.println("user id is: " + rs.getInt("userid"));
			            	useridReturned=rs.getInt("userid");
			            	loginmodel.setId(useridReturned+"");
			            	loginmodel.setIsLogin(true);
			            	
			            	
			                return loginmodel;
			            }
			            else
			            {
			            	loginmodel.setIsLogin(false);
			                return loginmodel;
			            }
			        }
			        catch (SQLException e)
			        {
			            System.out.println("SQL Exception : "+e.getMessage());
			            loginmodel.setIsLogin(false);
		                return loginmodel;
			        }
			    }
			
}