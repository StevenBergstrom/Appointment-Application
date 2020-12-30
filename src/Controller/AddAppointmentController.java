/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;
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
import javafx.scene.control.DateCell;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.util.Callback;
import static utils.AppointmentDAO.createAppointment;
import static utils.AppointmentDAO.selectAppointmentOverlap;
import static utils.CustomerDAO.readCustomers;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class AddAppointmentController implements Initializable {

    @FXML private Label TypeLabel;
    
    @FXML private Label ContactLabel;

    @FXML private Label CustomerIDLabel;
    
    @FXML private Label DateLabel;
    
    @FXML private Label StartLabel;

    @FXML private TextField TypeTextField;
    
    @FXML private TextField ContactTextField;
    
    @FXML private ComboBox<Integer> CustomerID;

    @FXML private Button BackButton;
    
    @FXML private Button CreateAppointmentButton;
    
    @FXML private DatePicker DatePicker;

    @FXML private ComboBox<LocalTime> StartDropDown;
        
    LocalDate currentDate = LocalDate.now();
    
    
    ObservableList<Customer> currentCustomer = FXCollections.observableArrayList();
    ObservableList<Integer> currentCustomerIds = FXCollections.observableArrayList();
    ObservableList<Appointment> appointmentOverlap = FXCollections.observableArrayList();
    

    public void createAppointmentButton(ActionEvent event) throws SQLException, IOException {
        if(TypeTextField.getText().isEmpty()){
            AlertClass.errorMessage(1, TypeTextField);
            return;
        }
        
        if(ContactTextField.getText().isEmpty()){
            AlertClass.errorMessage(1, ContactTextField);
            return;
        }
        
        if(DatePicker.getValue() == null){
            AlertClass.datePickerError();
            return;
        }
        
        if(DatePicker.getValue().isBefore(LocalDate.now())){
            AlertClass.dateError();
            return;
        }
        
        LocalTime now = LocalTime.now();
        LocalDateTime currentDT = LocalDateTime.of(currentDate, now);
        LocalDateTime startDT = LocalDateTime.of(DatePicker.getValue(), StartDropDown.getValue());
        if(startDT.isBefore(currentDT)){
            AlertClass.dateError();
            return;
        }
        
        String startTime = DatePicker.getValue().toString() + " " + StartDropDown.getValue().toString() + ":00";
        Timestamp startTimestamp = createTimestamp(startTime);
        //This is where I implement my Lambda expression so that way I am not doing multiple things in the createTimestamp Method
        startTimestamp = convertedTime.convertTimestamp(startTimestamp);
        
        Timestamp endTimestamp = new  Timestamp(startTimestamp.getTime());
        endTimestamp.setTime(startTimestamp.getTime() + (15 * 60) * 1000);
        Integer customerId = CustomerID.getValue();
        String type = TypeTextField.getText();  
        String contact = ContactTextField.getText();
        appointmentOverlap = selectAppointmentOverlap(startTimestamp);
        
        if(appointmentOverlap.isEmpty()){
            createAppointment(customerId, type, contact, startTimestamp, endTimestamp);
        
            Parent mainPageParent = FXMLLoader.load(getClass().getResource("AppointmentPage.fxml"));
            Scene mainPageScene = new Scene(mainPageParent);
            Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
            window.setScene(mainPageScene);
            window.show();
        } else{
            AlertClass.overlapError();
            return;
        }
        
        
        
    }
    //Used this to make a seperate expression for convert time a timestamp so that way it would be easier to read and understand
    ConvertTimeInterface convertedTime = n -> {
        
        LocalDateTime lcl = n.toLocalDateTime();
        ZonedDateTime DefZDT = lcl.atZone(ZoneId.of(ZoneId.systemDefault().toString()));
        ZonedDateTime UTCZDT = DefZDT.withZoneSameInstant(ZoneId.of("UTC"));
        LocalDateTime fnllcl = UTCZDT.toLocalDateTime();
        n = Timestamp.valueOf(fnllcl);
        
        return n;
    };
    //used this to convert the start time of a combo box easier rather than have it all done in the initialize method
    ConvertStartTimeInterface convertedStartTime = n -> {
        
        ZonedDateTime zdt = n.atZone(ZoneId.of("UTC-4"));
        ZonedDateTime estzdt = zdt.withZoneSameInstant(ZoneId.of(ZoneId.systemDefault().toString()));
        LocalDateTime ldtIn = estzdt.toLocalDateTime();
        LocalTime convertedStartLocalTime = ldtIn.toLocalTime();
        
        return convertedStartLocalTime;
    };
    
    public void goToAppointment(ActionEvent event) throws IOException {
        
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("AppointmentPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    
    
    public Timestamp createTimestamp(String time)
    { 
        
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date parsedDate = dateFormat.parse(time);
            Timestamp timestamp = new java.sql.Timestamp(parsedDate.getTime());
            return timestamp;
        } catch (ParseException ex) {
            Logger.getLogger(AddAppointmentController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
        
    }
    
    private Callback<DatePicker, DateCell> getDayCellFactory() {
 
        final Callback<DatePicker, DateCell> dayCellFactory = new Callback<DatePicker, DateCell>() {
 
            @Override
            public DateCell call(final DatePicker datePicker) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate day, boolean empty) {
                        super.updateItem(day, empty);
 
                        if (day.isBefore(currentDate) || day.getDayOfWeek() == DayOfWeek.SATURDAY || day.getDayOfWeek() == DayOfWeek.SUNDAY) {
                            setDisable(true);
                            setStyle("-fx-background-color: #ffc0cb;");
                        }
                    }
                };
            }
        };
        return dayCellFactory;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
               
        Callback<DatePicker, DateCell> dayCellFactory= this.getDayCellFactory();
        DatePicker.setDayCellFactory(dayCellFactory);
        
        //This is where I implement my Lambda expression so that way I can convert the comboBox to the local time
        LocalTime startTime = convertedStartTime.convertTimestamp(LocalDateTime.of(currentDate, LocalTime.of(9, 0)));
        
        for(int i = 0; i < 32; i++)
        {
            StartDropDown.getItems().add(startTime);
            startTime = startTime.plusMinutes(15);
        }
        
        
        try {
            currentCustomer = readCustomers();
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0; i < currentCustomer.size(); i++){
            currentCustomerIds.add(currentCustomer.get(i).getCustomerId());
        }
        
        CustomerID.setItems(currentCustomerIds);
        CustomerID.getSelectionModel().selectFirst();
        
        StartDropDown.getSelectionModel().selectFirst();
        
    }    
    
}
