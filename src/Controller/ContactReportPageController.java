/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static utils.AppointmentDAO.readAppointments;
import static utils.AppointmentDAO.selectAppointmentContact;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class ContactReportPageController implements Initializable {

    @FXML private TableView<Appointment> ContactReportTable;

    @FXML private TableColumn<Appointment, Integer> AppointmentIDColumn;

    @FXML private TableColumn<Appointment, String> ContactColumn;
    
    @FXML private TableColumn<Appointment, String> StartColumn;

    @FXML private TableColumn<Appointment, String> EndColumn;

    @FXML private ComboBox<String> ContactDropDown;

    @FXML private Button BackButton;
    
    ObservableList<Appointment> currentAppointments = FXCollections.observableArrayList();
    ObservableList<String> currentAppointmentContacts = FXCollections.observableArrayList();

    public void selectContact(ActionEvent event) throws SQLException
    {
        selectAppointmentContact(ContactDropDown.getValue());
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
        
        AppointmentIDColumn.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        ContactColumn.setCellValueFactory(new PropertyValueFactory<>("contact"));
        StartColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getStart().toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toString()));
        EndColumn.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getEnd().toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toString()));
        
        
        try {
            currentAppointments = readAppointments();
        } catch (SQLException ex) {
            Logger.getLogger(ContactReportPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        for(int i = 0; i < currentAppointments.size(); i++){
            if(!currentAppointmentContacts.contains(currentAppointments.get(i).getContact())){
                currentAppointmentContacts.add(currentAppointments.get(i).getContact());
            }
        }
        
        ContactDropDown.setItems(currentAppointmentContacts);
        ContactDropDown.getSelectionModel().selectFirst();
        
        try {
            selectAppointmentContact(ContactDropDown.getValue());
        } catch (SQLException ex) {
            Logger.getLogger(ContactReportPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ContactReportTable.setItems(currentAppointments);
    }    
    
}
