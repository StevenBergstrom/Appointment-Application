/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Appointment;
import Model.City;
import Model.Country;
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
import static utils.CityDAO.selectCities;
import static utils.CountryDAO.selectCountrys;

/**
 * FXML Controller class
 *
 * @author bergs
 */
public class CountriesReportPageController implements Initializable {

    @FXML private TableView<Country> CountriesReportTable;

    @FXML private TableColumn<Country, Integer> CountryIDColumn;

    @FXML private TableColumn<City, String> CountryColumn;

    @FXML private Button BackButton;
    
    ObservableList<Country> currentCountrys = FXCollections.observableArrayList();

    @FXML void goToReports(ActionEvent event) throws IOException {
        
        Parent mainPageParent = FXMLLoader.load(getClass().getResource("ReportsPage.fxml"));
        Scene mainPageScene = new Scene(mainPageParent);
        Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
        window.setScene(mainPageScene);
        window.show();
    }
    
    public void selectCountry(ActionEvent event) throws SQLException {
        selectCountrys();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        CountryIDColumn.setCellValueFactory(new PropertyValueFactory<>("countryId"));
        CountryColumn.setCellValueFactory(new PropertyValueFactory<>("country"));
        
        
        try {
            currentCountrys = selectCountrys();
        } catch (SQLException ex) {
            Logger.getLogger(CountriesReportPageController.class.getName()).log(Level.SEVERE, null, ex);
        }
       
        
        CountriesReportTable.setItems(currentCountrys);
    }    

    
}
