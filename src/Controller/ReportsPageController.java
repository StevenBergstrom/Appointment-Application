/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class ReportsPageController implements Initializable {

    @FXML private Button ScheduleButton;

    @FXML private Button AppointmentsByTypeButton;

    @FXML private Button AdditionalReportButton;

    @FXML private Label ReportsLabel;

    @FXML private Button BackButton;
    
    public void goToTypeReport(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("TypeReportPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    public void goToContactReport(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("ContactReportPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void goToCitiesReport(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("CountriesReportPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void goToMain(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
