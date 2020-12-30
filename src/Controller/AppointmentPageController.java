/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AlertClass.selectAppointmentError;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import static utils.AppointmentDAO.deleteAppointment;
import static utils.AppointmentDAO.readAppointments;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class AppointmentPageController implements Initializable {

    Appointment selectedAppointment;
    Integer appointmentId;
    
    @FXML private Button addButton;

    @FXML private Button updateButton;

    @FXML private Button deleteButton;

    @FXML private Button backButton;
    
    @FXML private TableView<Appointment> AppointmentTable;
    @FXML private TableColumn<Appointment, Integer> AppointmentID;
    @FXML private TableColumn<Appointment, Integer> CustomerID;
    @FXML private TableColumn<Appointment, String> Start;
    @FXML private TableColumn<Appointment, String> End;
    @FXML private TableColumn<Appointment, String> Type;
    
    public void goToMain(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void add(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("AddAppointment.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void update(ActionEvent event) throws IOException, SQLException
    {
        if(AppointmentTable.getSelectionModel().isEmpty()){
            selectAppointmentError();
            return;
        }
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateAppointment.fxml"));
        Parent mainPageParent = loader.load();
        
        Scene mainPageScene = new Scene(mainPageParent);
        
        UpdateAppointmentController controller = loader.getController();
        controller.loadAppointment(AppointmentTable.getSelectionModel().getSelectedItem());
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void delete(ActionEvent event) throws SQLException, IOException
    {
        if(AppointmentTable.getSelectionModel().isEmpty()){
            selectAppointmentError();
            return;
        }
        selectedAppointment = AppointmentTable.getSelectionModel().getSelectedItem();
        appointmentId = selectedAppointment.getAppointmentId();
        deleteAppointment(appointmentId);
        
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        
        AppointmentID.setCellValueFactory(new PropertyValueFactory<>("appointmentId"));
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        Type.setCellValueFactory(new PropertyValueFactory<>("type"));
        Start.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getStart().toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toString()));
        End.setCellValueFactory(c-> new SimpleStringProperty(c.getValue().getEnd().toLocalDateTime().atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.systemDefault()).toLocalDateTime().toString()));
        
        try {
            AppointmentTable.setItems(readAppointments());
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
}
