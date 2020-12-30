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
import Model.Address;

/**
 *
 * @author bergs
 */
public class AddressDAO {
    
    private static ObservableList<Address> allAddress = FXCollections.observableArrayList();
    
    public ObservableList<Address> getAllAddress()
    {
        return allAddress;
    }
    
    public void addAddress(Address address)
    {
        allAddress.add(address);
    }
    
    public void removeAddress(Address address)
    {
        for(int i = 0; i < allAddress.size(); i++)
        {
            if(allAddress.get(i) == address)
            {
                allAddress.remove(i);
            }
        }
    }
    
    public void changeAddress(int index, Address newAddress)
    {
        allAddress.remove(index);
        allAddress.add(index, newAddress);
    }
    
    public static void createAddress(String address, Integer cityID, Integer postalCode, String phone) throws SQLException
    {
        allAddress.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "INSERT INTO address (address, address2, cityId, postalCode, phone, createDate, createdBy, lastUpdate, lastUpdateBy) VALUES ('" + address + "', 'test', '" + cityID + "', '" + postalCode + "', '" + phone + "', NOW(),'test',NOW(),'test')";
        statement.execute(selectStatement);
        
    }
    
    public static ObservableList<Address> readAddress() throws SQLException
    {
        allAddress.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM address";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Address newAddress = new Address(rs.getInt("addressId"),rs.getString("address"),rs.getString("address2"),rs.getInt("cityId"),rs.getInt("postalCode"),rs.getString("phone"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allAddress.add(newAddress);
        }
        return allAddress;
    }
    
    public static String readPhoneNumber(Integer addressId) throws SQLException{
        String phone = null;
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM address WHERE addressId ='" + addressId + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Address newAddress = new Address(rs.getInt("addressId"),rs.getString("address"),rs.getString("address2"),rs.getInt("cityId"),rs.getInt("postalCode"),rs.getString("phone"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            phone = newAddress.getPhone();
        }
        return phone;
    }
    
    public static String readAddressName(Integer addressId) throws SQLException{
        String address = null;
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM address WHERE addressId ='" + addressId + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Address newAddress = new Address(rs.getInt("addressId"),rs.getString("address"),rs.getString("address2"),rs.getInt("cityId"),rs.getInt("postalCode"),rs.getString("phone"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            address = newAddress.getAddress();
        }
        return address;
    }
    
    public static Address readCurrentAddress(Integer addressId) throws SQLException{
        
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM address WHERE addressId ='" + addressId + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Address currentAddress = new Address(rs.getInt("addressId"),rs.getString("address"),rs.getString("address2"),rs.getInt("cityId"),rs.getInt("postalCode"),rs.getString("phone"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            return currentAddress;
        }
        return null;
    }
    
    public static String readCityName(Integer cityId) throws SQLException{
        
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT city FROM city WHERE cityId ='" + cityId + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            String cityName = rs.getString(1);
            return cityName;
        }
        return null;
    }
    
    public static Integer readCountryID(Integer cityId) throws SQLException{
        
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT countryId FROM city WHERE cityId ='" + cityId + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            int countryId = rs.getInt(1);
            return countryId;
        }
        return null;
    }
    
    public static String readPostal(Integer addressId) throws SQLException{
        
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT postalCode FROM address WHERE addressId ='" + addressId + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            String postal = rs.getString(1);
            return postal;
        }
        return null;
    }
    
    public static void updateAddress(Integer addressId, String address, Integer cityID, Integer postalCode, String phone) throws SQLException
    {
        Statement statement = DBQuery.getStatement();
        String updateStatement = "UPDATE address SET address = '" + address + "', cityId = '" + cityID + "', postalCode = '" + postalCode + "', phone = '" + phone + "' WHERE addressId = '" + addressId + "' LIMIT 1";
        statement.execute(updateStatement);
    }
    
    public static void updatePhoneNumber(Integer addressId, String phoneNumber) throws SQLException
    {
        Statement statement = DBQuery.getStatement();
        String updateStatement = "UPDATE address SET phone = '" + phoneNumber + "' WHERE addressId = '" + addressId + "'";
        statement.execute(updateStatement);
    }
    
    public static Integer readAddressID(String addressName) throws SQLException{
        
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT addressId FROM address WHERE address ='" + addressName + "' ORDER BY addressId DESC LIMIT 1";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            int addressId = rs.getInt(1);
            return addressId;
        }
        return null;
    }
}
