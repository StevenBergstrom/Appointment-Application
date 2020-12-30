/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AlertClass.selectCustomerError;
import Model.Customer;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
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
import static utils.AppointmentDAO.deleteCustomerAppointments;
import static utils.CustomerDAO.deleteCustomer;
import static utils.CustomerDAO.readCustomers;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class CustomerPageController implements Initializable {
    
    @FXML private Button backButton;
    @FXML private Button addButton;
    @FXML private Button updateButton;
    @FXML private Button deleteButton;
    @FXML private TableView<Customer> CustomerTable;
    @FXML private TableColumn<Customer, Integer> CustomerID;
    @FXML private TableColumn<Customer, String> CustomerName;
    
    private Customer selectedCustomer;
    private Integer selectedCustomerID;
    

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
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("AddCustomer.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    public void update(ActionEvent event) throws IOException, SQLException
    {
        if(CustomerTable.getSelectionModel().isEmpty()){
            selectCustomerError();
            return;
        }
        
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("UpdateCustomer.fxml"));
        Parent mainPageParent = loader.load();
        
        Scene mainPageScene = new Scene(mainPageParent);
        
        UpdateCustomerController controller = loader.getController();
        controller.loadCustomer(CustomerTable.getSelectionModel().getSelectedItem());
        
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void delete(ActionEvent event) throws SQLException, IOException {    
        
        if(CustomerTable.getSelectionModel().isEmpty()){
            selectCustomerError();
            return;
        }
        
        selectedCustomer = CustomerTable.getSelectionModel().getSelectedItem();
        selectedCustomerID = selectedCustomer.getCustomerId();
        deleteCustomerAppointments(selectedCustomerID);
        deleteCustomer(selectedCustomerID);
        
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();  
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        CustomerID.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        CustomerName.setCellValueFactory(new PropertyValueFactory<>("customerName"));
        try {
            CustomerTable.setItems(readCustomers());
        } catch (SQLException ex) {
            Logger.getLogger(CustomerPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }    
    
}
