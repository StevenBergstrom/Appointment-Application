/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import Model.Customer;

/**
 *
 * @author bergs
 */
public class CustomerDAO {
    
    private static ObservableList<Customer> allCustomers = FXCollections.observableArrayList();
    
    public ObservableList<Customer> getAllCustomers()
    {
        return allCustomers;
    }
    
    public void addCustomer(Customer customer)
    {
        allCustomers.add(customer);
    }
    
    public void removeCustomer(Customer customer)
    {
        for(int i = 0; i < allCustomers.size(); i++)
        {
            if(allCustomers.get(i) == customer)
            {
                allCustomers.remove(i);
            }
        }
    }
    
    public void changeCustomer(int index, Customer newCustomer)
    {
        allCustomers.remove(index);
        allCustomers.add(index, newCustomer);
    }
    
    public static ObservableList<Customer> readCustomers() throws SQLException
    {
        allCustomers.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM customer";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Customer newCustomer = new Customer(rs.getInt("customerId"),rs.getString("customerName"),rs.getInt("addressId"),rs.getBoolean("active"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allCustomers.add(newCustomer);
        }
        return allCustomers;
        
        
    }
    
    public static void createCustomer(String customerName,Integer addressId) throws SQLException
    {
        Statement statement = DBQuery.getStatement();
        String insertStatement = "INSERT INTO customer (customerName, addressId, active, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES('" + customerName + "','" + addressId + "','1',NOW(),'test',NOW(),'test')";
        statement.execute(insertStatement);
    }
    
    public static ObservableList<Customer> readCustomer() throws SQLException
    {
        allCustomers.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM customer";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Customer newCustomer = new Customer(rs.getInt("customerId"), rs.getString("customerName"), rs.getInt("addressId"), rs.getBoolean("active"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allCustomers.add(newCustomer);
        }
        return allCustomers;
    }
    
    public static void updateCustomer(Integer customerId, String customerName,Integer addressId) throws SQLException
    {
        Statement statement = DBQuery.getStatement();
        String updateStatement = "UPDATE customer SET customerName = '" + customerName + "', addressId = '" + addressId + "' WHERE customerId ='" + customerId + "'";
        statement.execute(updateStatement);
    }
    
    public static void deleteCustomer(Integer idNumber) throws SQLException
    {
        Statement statement = DBQuery.getStatement();
        String deleteStatement = "DELETE FROM customer WHERE customerId='" + idNumber + "'";
        statement.execute(deleteStatement);
    }
}
