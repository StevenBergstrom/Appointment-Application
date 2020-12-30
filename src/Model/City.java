/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.Timestamp;

/**
 *
 * @author bergs
 */
public class City extends LogManager {
    
    private int cityId;
    private String city;
    private int countryId;
    
    public City(int cityId, String city, int countryId, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy)
    {
        super(createDate, createdBy, lastUpdate, lastUpdateBy);
        this.cityId = cityId;
        this.city = city;
        this.countryId = countryId;
        
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

   
}
