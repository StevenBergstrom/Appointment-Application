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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static utils.AppointmentDAO.readAppointments;
import static utils.AppointmentDAO.selectAppointmentType;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class TypeReportPageController implements Initializable {

    @FXML private ComboBox<String> TypeDropDown;

    @FXML private TableView<Appointment> TypeReportTable;

    @FXML private TableColumn<Appointment, Integer> AppointmentIDColumn;

    @FXML private TableColumn<Appointment, String> TypeColumn;

    @FXML private Button BackButton;
    
    ObservableList<Appointment> currentAppointments = FXCollections.observableArrayList();
    ObservableList<String> currentAppointmentTypes = FXCollections.observableArrayList();
    
    public void selectType(ActionEvent event) throws SQLException
    {
        selectAppointmentType(TypeDropDown.getValue());
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
        TypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        
        try {
            currentAppointments = readAppointments();
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < currentAppointments.size(); i++){
            if(!currentAppointmentTypes.contains(currentAppointments.get(i).getType())){
                currentAppointmentTypes.add(currentAppointments.get(i).getType());
            }
        }
        
        TypeDropDown.setItems(currentAppointmentTypes);
        TypeDropDown.getSelectionModel().selectFirst();
        
        try {
            selectAppointmentType(TypeDropDown.getValue());
        } catch (SQLException ex) {
            Logger.getLogger(TypeReportPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        TypeReportTable.setItems(currentAppointments);
        
    }    
    
}
