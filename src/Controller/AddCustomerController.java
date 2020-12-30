/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import static Controller.AlertClass.typeError;
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
import static utils.AddressDAO.createAddress;
import static utils.AddressDAO.readAddressID;
import static utils.CityDAO.selectAllCities;
import static utils.CityDAO.selectCityID;
import static utils.CityDAO.selectCountryID;
import static utils.CountryDAO.selectCountrysByID;
import static utils.CustomerDAO.createCustomer;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class AddCustomerController implements Initializable {

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
    
    public void goToCustomer(ActionEvent event) throws IOException
    {
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("CustomerPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void createCustomerButton(ActionEvent event) throws SQLException, IOException
    {
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
        String CustomerName = NameTextField.getText();
        String addressField = AddressTextField.getText();
        String City = CityDropDown.getValue();
        String PhoneNumber = PhoneTextField.getText();
        try {
            createAddress(addressField, selectCityID(City), Postal, PhoneNumber);
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        try {
            createCustomer(CustomerName, readAddressID(addressField));
        } catch (SQLException ex) {
            Logger.getLogger(AddCustomerController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("MainPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
        
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
        
        CountryDropDown.getSelectionModel().selectFirst();
        
        
    }    
    
}
