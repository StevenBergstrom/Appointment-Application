/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.User;

/**
 *
 * @author bergs
 */
public class UserDAO {
    
    private static ObservableList<User> allUsers = FXCollections.observableArrayList();
    
    
    public ObservableList<User> getAllUsers()
    {
        return allUsers;
    }
    
    public void addUser(User user)
    {
        allUsers.add(user);
    }
    
    public void removeUser(User user)
    {
        for(int i = 0; i < allUsers.size(); i++)
        {
            if(allUsers.get(i) == user)
            {
                allUsers.remove(i);
            }
        }
    }
    
    public void changeUser(int index, User newUser)
    {
        allUsers.remove(index);
        allUsers.add(index, newUser);
    }
    
    public static void readUsers() throws SQLException
    {
        Connection conn = DBConnection.getConnection();
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM user";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            User nextUser = new User(rs.getInt("userId"), rs.getString("userName"),rs.getString("password"),rs.getBoolean("active"),rs.getTimestamp("createDate"),rs.getString("createdBy"), rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            
        }
        DBConnection.closeConnection();
        
    }
    
    
    
    
}
