/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import Model.City;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 *
 * @author bergs
 */
public class CityDAO {
    private static ObservableList<City> allCitys = FXCollections.observableArrayList();
    
    public ObservableList<City> getAllAppointments()
    {
        return allCitys;
    }
    
    public static ObservableList<City> selectAllCities() throws SQLException
    {
        allCitys.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM city";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            City newCity = new City(rs.getInt("cityId"),rs.getString("city"),rs.getInt("countryId"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allCitys.add(newCity);
        }
        return allCitys;
        
        
    }
    
    public static ObservableList<City> selectCities(Integer countryID) throws SQLException
    {
        allCitys.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM city WHERE countryId = '" + countryID + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            City newCity = new City(rs.getInt("cityId"),rs.getString("city"),rs.getInt("countryId"),rs.getTimestamp("createDate"),rs.getString("createdBy"),rs.getTimestamp("lastUpdate"),rs.getString("lastUpdateBy"));
            allCitys.add(newCity);
        }
        return allCitys;
        
    }
    
    public static Integer selectCountryID(String city) throws SQLException
    {
        allCitys.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT countryId FROM city WHERE city = '" + city + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            int countryId = rs.getInt(1);
            return countryId;
        }
        return null;
        
    }
    
    public static Integer selectCityID(String city) throws SQLException
    {
        allCitys.clear();
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT cityId FROM city WHERE city = '" + city + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            int cityId = rs.getInt(1);
            return cityId;
        }
        return null;
        
    }
    
    public static Integer readCityByName(String cityName) throws SQLException{
        
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT cityId FROM city WHERE city ='" + cityName + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        while(rs.next())
        {
            int cityID = rs.getInt(1);
            return cityID;
        }
        return null;
    }
}
