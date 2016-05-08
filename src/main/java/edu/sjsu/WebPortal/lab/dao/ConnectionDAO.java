package edu.sjsu.WebPortal.lab.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Connection manager class.
 * @author Karan
 *
 */
public class ConnectionDAO
{
    static Connection conn;
 
    /**
     * This method returns the connection object.
     * @return Connection
     */
    public static Connection getConnection()
    {
        try
        {
            String url = "jdbc:mysql://127.0.0.1:3306/";
            String dbName ="cloudPortaldb"; // here the name of Database.
            String uname = "root"; //username
            String pwd = "clouddb"; //password
            String autoReconnect = "?autoReconnect=true";
            //MySQL jdbc driver
            Class.forName("com.mysql.jdbc.Driver");
            try
            {
                if(null==conn){
                    conn = DriverManager.getConnection(url+dbName+autoReconnect,uname,pwd);
                    System.out.println(" !!! got db conn !!! ");
                }
            }
            catch (SQLException ex)
            {
                System.out.println("SQL Exception occurred while getting connection object. \nDetails : "+ ex.getMessage());
            }
        }
        catch(ClassNotFoundException e)
        {System.out.println("here");
            System.out.println("Exception occurred while getting connection object. \nDetails : "+ e.getMessage());
        }
        return conn;
    }
 
    /**
     * This method returns the Statement object to execute any SQL query.
     * @return
     */
    public static Statement getStatement()
    {
        Statement stmt = null;
        try
        {
            Connection connection = ConnectionDAO.getConnection();
            stmt=connection.createStatement();
        }
        catch (SQLException e) {
            System.out.println("Exception occurred while getting Statement object. \nDetails : "+ e.getMessage());
        }
        return stmt;
    }
}