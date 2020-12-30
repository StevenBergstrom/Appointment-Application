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
import java.time.ZoneId;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static utils.AppointmentDAO.monthlyAppointments;
import static utils.AppointmentDAO.weeklyAppointments;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class CalendarPageController implements Initializable {

    @FXML private TableView<Appointment> AppointmentTable;
    @FXML private TableColumn<Appointment, Integer> AppointmentID;
    @FXML private TableColumn<Appointment, Integer> CustomerID;
    @FXML private TableColumn<Appointment, String> Start;
    @FXML private TableColumn<Appointment, String> End;
    @FXML private TableColumn<Appointment, String> Type;
    @FXML private RadioButton WeekViewRB;
    @FXML private RadioButton MonthViewRB;
    @FXML private ToggleGroup CalendarView;
    @FXML private Button backButton;

    public void goToMain(ActionEvent event) throws IOException {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void viewSelect(ActionEvent event){
        if(WeekViewRB.isSelected()){
            try {
                AppointmentTable.setItems(weeklyAppointments());
            } catch (SQLException ex) {
                Logger.getLogger(CustomerPageController.class.getName()).log(Level.SEVERE, null, ex);
            }  
        } else
        try {
                AppointmentTable.setItems(monthlyAppointments());
            } catch (SQLException ex) {
                Logger.getLogger(CustomerPageController.class.getName()).log(Level.SEVERE, null, ex);
            }  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Start.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getStart().toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toString()));
        End.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getEnd().toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toString()));
        
        try {
            AppointmentTable.setItems(weeklyAppointments());
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPageController.class.getName()).log(Level.SEVERE, null, ex);
        }  
    }    
    
}
