/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Model.Country;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bergs
 */
public class CountryDAO {
    private static ObservableList<Country> allCountrys = FXCollections.observableArrayList();
    
    public ObservableList<Country> getAllAppointments()
    {
        return allCountrys;
    }
    
    public static ObservableList<Country> selectCountrys() throws SQLException
    {
        allCountrys.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM country";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Country newCountry = new Country(rs.getInt("countryId"),rs.getString("country"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allCountrys.add(newCountry);
        }
        return allCountrys;
        
        
    }
    
    public static ObservableList<Country> selectCountrysByID(Integer countryID) throws SQLException
    {
        allCountrys.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM country WHERE countryId = '" + countryID + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            Country newCountry = new Country(rs.getInt("countryId"),rs.getString("country"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allCountrys.add(newCountry);
        }
        return allCountrys;
        
        
    }
    
    public static String selectCountryName(Integer countryID) throws SQLException
    {
        allCountrys.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT country FROM country WHERE countryId = '" + countryID + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            String countryName = rs.getString(1);
            return countryName;
        }
        return null;
        
        
    }
}
