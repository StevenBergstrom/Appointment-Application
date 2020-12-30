/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Model.LogManager;
import java.sql.Timestamp;

/**
 *
 * @author bergs
 */
public class User extends LogManager {
    private int userId;
    private String userName;
    private String password;
    private Boolean active;
    
    public User(int userId, String userName,String password,Boolean active,Timestamp createDate,String createdBy,Timestamp lastUpdate,String lastUpdateBy)
    {    
        super(createDate, createdBy, lastUpdate, lastUpdateBy);
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.active = active;
    }
    
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }

    
}
