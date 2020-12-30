/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.util.Locale;
import java.util.ResourceBundle;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

/**
 *
 * @author bergs
 */
public class AlertClass {
    public static void errorMessage(int code, TextField field){
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error Empty Field");
        alert.setHeaderText("Error Empty Field");
        switch (code){
            case 1: {
                alert.setContentText("Make sure all fields contain a value");
                break;
            }
            case 2: {
                alert.setContentText("Make sure all fields contain a value");
                break;
            }
        }
        alert.showAndWait();
}
    public static void loginError(){
       
        ResourceBundle rb = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("es"))
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle(rb.getString("error") + " " + rb.getString("logging") + " " + rb.getString("in"));
            alert.setHeaderText(rb.getString("error") + " " + rb.getString("logging") + " " + rb.getString("in"));
            alert.setContentText(rb.getString("incorrect") + " " + rb.getString("username") + " " + rb.getString("and") + " " + rb.getString("password"));
            alert.showAndWait();
        } 
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Logging In");
            alert.setHeaderText("Error Logging In");
            alert.setContentText("Incorrect username and Password");
            alert.showAndWait();
        }
        
    }
    
    public static void selectCustomerError(){
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Please select a Customer first");
        alert.showAndWait();
    }
    
    public static void selectAppointmentError(){
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("Error");
        alert.setContentText("Please select an Appointment first");
        alert.showAndWait();
    }
    
    public static void datePickerError(){
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Date Picker");
        alert.setHeaderText("Error with Date Picker");
        alert.setContentText("Date Picker cannot be empty");
        alert.showAndWait();
    }
    
    public static void timeError(){
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Time selected");
        alert.setHeaderText("Error with Time selected");
        alert.setContentText("Start time cannot be same as or after end time");
        alert.showAndWait();
    }
    
    public static void dateError(){
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Time selected");
        alert.setHeaderText("Error with Time selected");
        alert.setContentText("Date and Time cannot be in the past");
        alert.showAndWait();
    }
    
    public static void overlapError(){
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error with Time selected");
        alert.setHeaderText("Error with Time selected");
        alert.setContentText("Start Time is already taken");
        alert.showAndWait();
    }
    
    public static void appointmentAlert(){
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Appointment Alert");
        alert.setHeaderText("Appointment Alert");
        alert.setContentText("You have an appointment within 15 minutes");
        alert.showAndWait();
    }
    
    public static void typeError(){
       
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Type Error");
        alert.setHeaderText("Type error");
        alert.setContentText("Postal Code must be a number");
        alert.showAndWait();
    }
}