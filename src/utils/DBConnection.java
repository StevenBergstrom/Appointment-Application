/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author bergs
 */
public class DBConnection {
    //JDBC url
    private static final String protocol = "jdbc";
    private static final String vendorName = ":mysql:";
    private static final String ipAddress = "//3.227.166.251/U06RzB";
    
    // jdbc url
    private static final String jdbcUrl = protocol + vendorName + ipAddress;
    
    //driver reference
    private static final String MYSQLJDBCDriver = "com.mysql.jdbc.Driver";
    private static Connection conn = null;
    
    //username
    private static final String AdminUsername = "U06RzB";
    //password
    private static final String AdminPassword = "53688848204";
    
    public static void makeConnection()
    {
        try{
            Class.forName(MYSQLJDBCDriver);
            conn = (Connection)DriverManager.getConnection(jdbcUrl, AdminUsername, AdminPassword);
            System.out.println("Connection Successful");
        }
        catch(ClassNotFoundException e)
        {
            System.out.println(e.getMessage());
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
        
    }
    public static Connection getConnection()
    {
        return conn;
    }
    
    public static void closeConnection()
    {
        try{
        conn.close();
        System.out.println("Connection Closed!");
        }
        catch(SQLException e)
        {
            System.out.println(e.getMessage());
        }
    }
    

}
