/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.Appointment;
import java.time.LocalDateTime;

/**
 *
 * @author bergs
 */
public class AppointmentDAO {
    private static ObservableList<Appointment> allAppointments = FXCollections.observableArrayList();
    
    public ObservableList<Appointment> getAllAppointments()
    {
        return allAppointments;
    }
    
    public void addAppointment(Appointment appointment)
    {
        allAppointments.add(appointment);
    }
    
    public void removeAppointment(Appointment appointment)
    {
        for(int i = 0; i < allAppointments.size(); i++)
        {
            if(allAppointments.get(i) == appointment)
            {
                allAppointments.remove(i);
            }
        }
    }
    
    public void changeAppointment(int index, Appointment newAppointment)
    {
        allAppointments.remove(index);
        allAppointments.add(index, newAppointment);
    }
    
    public static void createAppointment(Integer customerId, String type, String contact, Timestamp start, Timestamp end) throws SQLException
    {
        Statement statement = DBQuery.getStatement();
        String createStatement = "INSERT INTO appointment (customerId, userId, title, description, location, contact, type, url, start, end, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES('" + customerId + "','3', 'not needed', 'not needed', 'not needed','" + contact + "','" + type + "', 'not needed','" + start + "','" + end + "', NOW(),'test',NOW(),'test')";
        statement.execute(createStatement);
    }
    
    public static ObservableList<Appointment> readAppointments() throws SQLException
    {
        allAppointments.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM appointment";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Appointment newAppointment = new Appointment(rs.getInt("appointmentId"),rs.getInt("customerId"),rs.getInt("userId"),rs.getString("title"),rs.getString("description"),rs.getString("location"),rs.getString("contact"),rs.getString("type"),rs.getString("url"),rs.getTimestamp("start"),rs.getTimestamp("end"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allAppointments.add(newAppointment);
        }
        return allAppointments;
        
        
    }
    
    public static void updateAppointment(Integer AppointmentID, String type, String contact, Integer customerID, Timestamp start,  Timestamp end) throws SQLException
    {
        Statement statement = DBQuery.getStatement();
        String updateStatement = "UPDATE appointment SET type = '" + type + "', contact = '" + contact + "', customerId = '" + customerID + "', start = '" + start + "', end = '" + end + "' WHERE appointmentId = '" + AppointmentID + "'";
        statement.execute(updateStatement);
    }
    
    public static void deleteAppointment(Integer appointmentId) throws SQLException
    {
        Statement statement = DBQuery.getStatement();
        String deleteStatement = "DELETE FROM appointment WHERE appointmentId='" + appointmentId + "'";
        statement.execute(deleteStatement);
    }
    
    public static void deleteCustomerAppointments(Integer customerId) throws SQLException
    {
        Statement statement = DBQuery.getStatement();
        String deleteStatement = "DELETE FROM appointment WHERE customerId='" + customerId + "'";
        statement.execute(deleteStatement);
    }
    
    public static ObservableList<Appointment> weeklyAppointments() throws SQLException
    {
        allAppointments.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM appointment WHERE start > curdate() AND start <= date_add(curdate(), interval 7 day)";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Appointment newAppointment = new Appointment(rs.getInt("appointmentId"),rs.getInt("customerId"),rs.getInt("userId"),rs.getString("title"),rs.getString("description"),rs.getString("location"),rs.getString("contact"),rs.getString("type"),rs.getString("url"),rs.getTimestamp("start"),rs.getTimestamp("end"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allAppointments.add(newAppointment);
        }
        return allAppointments;
        
        
    }
    
    public static ObservableList<Appointment> monthlyAppointments() throws SQLException
    {
        allAppointments.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM appointment WHERE start > date_sub(LAST_DAY(curdate()), interval 1 month) AND start < date_add(LAST_DAY(curdate()), interval 1 day)";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Appointment newAppointment = new Appointment(rs.getInt("appointmentId"),rs.getInt("customerId"),rs.getInt("userId"),rs.getString("title"),rs.getString("description"),rs.getString("location"),rs.getString("contact"),rs.getString("type"),rs.getString("url"),rs.getTimestamp("start"),rs.getTimestamp("end"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allAppointments.add(newAppointment);
        }
        return allAppointments;
        
        
    }
    
    public static ObservableList<Appointment> selectAppointmentType(String type) throws SQLException
    {
        allAppointments.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM appointment WHERE type = '" + type + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Appointment newAppointment = new Appointment(rs.getInt("appointmentId"),rs.getInt("customerId"),rs.getInt("userId"),rs.getString("title"),rs.getString("description"),rs.getString("location"),rs.getString("contact"),rs.getString("type"),rs.getString("url"),rs.getTimestamp("start"),rs.getTimestamp("end"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allAppointments.add(newAppointment);
        }
        return allAppointments;
        
    }
    
    public static ObservableList<Appointment> selectAppointmentContact(String contact) throws SQLException
    {
        allAppointments.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM appointment WHERE contact = '" + contact + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Appointment newAppointment = new Appointment(rs.getInt("appointmentId"),rs.getInt("customerId"),rs.getInt("userId"),rs.getString("title"),rs.getString("description"),rs.getString("location"),rs.getString("contact"),rs.getString("type"),rs.getString("url"),rs.getTimestamp("start"),rs.getTimestamp("end"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allAppointments.add(newAppointment);
        }
        return allAppointments;
        
    }
    
    public static ObservableList<Appointment> selectAppointmentOverlap(Timestamp start) throws SQLException
    {
        allAppointments.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM appointment WHERE start = '" + start + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Appointment newAppointment = new Appointment(rs.getInt("appointmentId"),rs.getInt("customerId"),rs.getInt("userId"),rs.getString("title"),rs.getString("description"),rs.getString("location"),rs.getString("contact"),rs.getString("type"),rs.getString("url"),rs.getTimestamp("start"),rs.getTimestamp("end"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allAppointments.add(newAppointment);
        }
        return allAppointments;
        
    }
    
    public static ObservableList<Appointment> selectAppointmentAlert(LocalDateTime localTime) throws SQLException
    {
        allAppointments.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM appointment WHERE start >= '" + localTime + "' AND start <= DATE_ADD('" + localTime + "', interval 15 MINUTE)";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Appointment newAppointment = new Appointment(rs.getInt("appointmentId"),rs.getInt("customerId"),rs.getInt("userId"),rs.getString("title"),rs.getString("description"),rs.getString("location"),rs.getString("contact"),rs.getString("type"),rs.getString("url"),rs.getTimestamp("start"),rs.getTimestamp("end"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allAppointments.add(newAppointment);
        }
        return allAppointments;
        
    }
    
}
