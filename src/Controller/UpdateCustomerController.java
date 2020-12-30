/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AlertClass.typeError;
import Model.Customer;
import Model.Address;
import Model.City;
import Model.Country;
import java.io.IOException;
import static java.lang.Integer.parseInt;
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
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import static utils.AddressDAO.readAddressID;
import static utils.AddressDAO.readAddressName;
import static utils.AddressDAO.readCityName;
import static utils.AddressDAO.readCountryID;
import static utils.AddressDAO.readCurrentAddress;
import static utils.AddressDAO.readPhoneNumber;
import static utils.AddressDAO.readPostal;
import static utils.AddressDAO.updateAddress;
import static utils.CityDAO.readCityByName;
import static utils.CityDAO.selectAllCities;
import static utils.CityDAO.selectCountryID;
import static utils.CountryDAO.selectCountryName;
import static utils.CountryDAO.selectCountrysByID;
import static utils.CustomerDAO.updateCustomer;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class UpdateCustomerController implements Initializable {

    private Customer selectedCustomer;
    private Address currentCustomerAddress;
    
    @FXML private Label NameLabel;

    @FXML private Label AddressLabel;

    @FXML private Label PhoneLabel;

    @FXML private Label CityLabel;

    @FXML private Label CountryLabel;

    @FXML private Label PostalLabel;

    @FXML private TextField NameTextField;

    @FXML private Button BackButton;

    @FXML private TextField PhoneTextField;
    
    @FXML private ComboBox<String> CityDropDown;

    @FXML private ComboBox<String> CountryDropDown;

    @FXML private TextField PostalTextField;

    @FXML private TextField AddressTextField;

    ObservableList<City> currentCities = FXCollections.observableArrayList();
    ObservableList<Country> currentCountrys = FXCollections.observableArrayList();
    ObservableList<City> currentCityNames = FXCollections.observableArrayList();
    
    public void updateCustomerButton(ActionEvent event) throws SQLException, IOException {
        
        if(NameTextField.getText().isEmpty()){
            AlertClass.errorMessage(1, NameTextField);
            return;
        }
        if(PhoneTextField.getText().isEmpty()){
            AlertClass.errorMessage(1, PhoneTextField);
            return;
        }
        if(AddressTextField.getText().isEmpty()){
            AlertClass.errorMessage(1, AddressTextField);
            return;
        }
        if(PostalTextField.getText().isEmpty()){
            AlertClass.errorMessage(1, PostalTextField);
            return;
        }
        try{
            parseInt(PostalTextField.getText());
        } catch (NumberFormatException e){
            typeError();
            return;
        }
        
        int Postal = parseInt(PostalTextField.getText());
        Integer customerId = selectedCustomer.getCustomerId();
        String CustomerName = NameTextField.getText();
        String addressField = AddressTextField.getText();
        String City = CityDropDown.getValue();
        String PhoneNumber = PhoneTextField.getText();
        updateAddress(currentCustomerAddress.getAddressId(), addressField, readCityByName(City), Postal, PhoneNumber);
        updateCustomer(customerId, CustomerName, readAddressID(addressField));
        
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void loadCustomer(Customer customer) throws SQLException
    {
        selectedCustomer = customer;
        currentCustomerAddress = readCurrentAddress(selectedCustomer.getAddressId());
        NameTextField.setText(selectedCustomer.getCustomerName());
        AddressTextField.setText(readAddressName(selectedCustomer.getAddressId()));
        CityDropDown.setValue(readCityName(currentCustomerAddress.getCityId()));
        CountryDropDown.setValue(selectCountryName((readCountryID(currentCustomerAddress.getCityId()))));
        PostalTextField.setText(readPostal(selectedCustomer.getAddressId()));
        PhoneTextField.setText(readPhoneNumber(selectedCustomer.getAddressId()));
    }
    
    public void countryClick(ActionEvent event) throws SQLException{
        
        CountryDropDown.getItems().remove(0);
        
        try {
            currentCountrys = selectCountrysByID(selectCountryID(CityDropDown.getValue()));
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        CountryDropDown.getItems().add(currentCountrys.get(0).getCountry());
        
        CountryDropDown.getSelectionModel().selectFirst();
    }
    
    public void goToCustomer(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        try {
            currentCities = selectAllCities();
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        for(int i = 0;i < currentCities.size(); i++){
            CityDropDown.getItems().add(currentCities.get(i).getCity());
        }
        
        CityDropDown.getSelectionModel().selectFirst();
        
        
        try {
            currentCountrys = selectCountrysByID(selectCountryID(CityDropDown.getValue()));
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        CountryDropDown.getItems().add(currentCountrys.get(0).getCountry());
        
    }    
    
}
