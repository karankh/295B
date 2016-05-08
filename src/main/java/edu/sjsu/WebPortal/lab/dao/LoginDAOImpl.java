package edu.sjsu.WebPortal.lab.dao;

import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Repository;

import edu.sjsu.WebPortal.lab.MySQL.LoginModel;
import edu.sjsu.WebPortal.lab.MySQL.SignUpPageModel;
import edu.sjsu.WebPortal.lab.MySQL.UserPageModel;
import edu.sjsu.WebPortal.lab.utilServices.PlayPP;


 
/**
 * Implementation of the LoginDao Interface
 * @author Karan
 */
@SuppressWarnings("unused")
@Repository("loginDAO")
public class LoginDAOImpl implements LoginDAO {



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
			                loginmodel.setUsertype(rs.getString("usertype"));
			            	
			            	
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