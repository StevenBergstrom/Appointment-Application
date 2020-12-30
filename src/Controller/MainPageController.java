/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AlertClass.appointmentAlert;
import Model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import static utils.AppointmentDAO.selectAppointmentAlert;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class MainPageController implements Initializable {

    @FXML private Button customerButton;
    @FXML private Button appointmentsButton;
    @FXML private Button calendarButton;
    @FXML private Button reportsButton;
    
    ObservableList<Appointment> appointmentAlert = FXCollections.observableArrayList();
    
    
    public void goToCustomer(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void goToAppointments(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("AppointmentPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void goToCalendar(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("CalendarPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void goToReports(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("ReportsPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
