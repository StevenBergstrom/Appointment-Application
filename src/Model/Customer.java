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
public class Customer extends LogManager{
    
    private int customerId;
    private String customerName;
    private int addressId;
    private boolean active;
    
    public Customer(int customerId, String customerName, int addressId, boolean active, Timestamp createDate, String createdBy, Timestamp lastUpdate, String lastUpdateBy)
    {
        super(createDate, createdBy, lastUpdate, lastUpdateBy);
        this.customerId = customerId;
        this.customerName = customerName;
        this.addressId = addressId;
        this.active = active;
        
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public int getAddressId() {
        return addressId;
    }

    public void setAddressId(int addressId) {
        this.addressId = addressId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

   
}
