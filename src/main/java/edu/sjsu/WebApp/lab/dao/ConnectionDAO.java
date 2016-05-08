package edu.sjsu.WebApp.lab.dao;

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
            String url = "jdbc:mysql://172.17.0.3:3306/"; // old - 127.0.0.1:3306 , 
            // aws - cloud295bmysql.cw8vxxk4fprv.us-west-2.rds.amazonaws.com // docker - /172.17.0.3
            String dbName ="cloudAppdb"; // here the name of Database. //local- cloudAppdb, aws - CloudAppdb
            String uname = "root"; //username
            String pwd = "123"; //password oldpswd = clouddb, docker - 123, aws - clouddb123
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