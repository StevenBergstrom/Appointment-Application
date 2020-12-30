/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AlertClass.appointmentAlert;
import static Controller.AlertClass.loginError;
import Model.Appointment;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static utils.AppointmentDAO.selectAppointmentAlert;
import utils.DBConnection;
import utils.DBQuery;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class LoginPageController implements Initializable {
    
    @FXML private Label title;
    @FXML private Label username;
    @FXML private Label password;
    
    @FXML private TextField user;
    @FXML private TextField pass;
    
    @FXML private Button loginButton;
    
    ObservableList<Appointment> appointmentAlert = FXCollections.observableArrayList();
    
    public void languageChange()
    {
        ResourceBundle rb = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());
        if(Locale.getDefault().getLanguage().equals("es"))
        {
            title.setText(rb.getString("title"));
            username.setText(rb.getString("username"));
            password.setText(rb.getString("password"));
            loginButton.setText(rb.getString("login"));
        }
    }
    
    public void login(ActionEvent event) throws IOException, SQLException
    {
        if (user.getText().length() != 0 && pass.getText().length() != 0)
        {
        String userLogin = user.getText();
        String userPassword = pass.getText();
        Connection conn = DBConnection.getConnection();
        DBQuery.setStatement(conn);
        Statement statement = DBQuery.getStatement();
        String selectStatement = "SELECT * FROM user WHERE userName = '" + userLogin + "' AND password = '" + userPassword + "'";
        statement.execute(selectStatement);
        ResultSet rs = statement.getResultSet();
        boolean match = false;
        while(rs.next())
        {            
            if(rs.getString("userName").equals(userLogin) && rs.getString("password").equals(userPassword))
            {
                String currentUser = rs.getString("userName");
                match = true;
                break;
            }
        
            
        }
        if(match)
        {
            String fileName = "login.txt";
            LocalDateTime timeStamp = LocalDateTime.now();
            Scanner keyboard = new Scanner(System.in);
            FileWriter fwriter = new FileWriter(fileName, true);
            PrintWriter outputFile = new PrintWriter(fwriter);
            outputFile.println(userLogin + " " + timeStamp);
            outputFile.close();
            
            ResourceBundle rb = ResourceBundle.getBundle("Language/Nat", Locale.getDefault());
            if(Locale.getDefault().getLanguage().equals("es"))
                {
                    System.out.println(rb.getString("login") + " " + rb.getString("successful"));
                } else
                {
                    System.out.println("Login successful");
                }
            
            Timestamp currentTime = Timestamp.valueOf(LocalDateTime.now());
        LocalDateTime ldt = currentTime.toLocalDateTime();
        ZonedDateTime zdt = ldt.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime utczdt = zdt.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime ldtIn = utczdt.toLocalDateTime();
        System.out.println(ldtIn);
        
        
        try {
            appointmentAlert = selectAppointmentAlert(ldtIn);
        } catch (SQLException ex) {
            Logger.getLogger(MainPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        if(!appointmentAlert.isEmpty()){
            appointmentAlert();
        }
                        
            Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
            Scene mainPageScene = new Scene(mainPageParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(mainPageScene);
            window.show();
        }
        else
            loginError();
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        languageChange();

    }
}
