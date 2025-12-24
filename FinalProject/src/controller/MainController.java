/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.collections.*;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import model.*;

/**
 *
 * @author Mahmoud Ehab
 */
public class MainController extends SceneController {
    @FXML private TableView<Vehicle> tableView;
    @FXML private TableColumn<Vehicle, String> idCol;
    @FXML private TableColumn<Vehicle, String> brandCol;
    @FXML private TableColumn<Vehicle, Double> priceCol;
    @FXML private TableColumn<Vehicle, String> statusCol;
    private ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
    
    @FXML
    public void initialize() {
        idCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        
        brandCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getBrand()));
        
        priceCol.setCellValueFactory(data-> new javafx.beans.property.SimpleObjectProperty<>(data.getValue().getPricePerDay()));
        
        statusCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().isAvailable() ? "Yes" : "No"));
        
        vehicles.addAll(
            new Car("C1", "Toyota", 50),
            new Bike("B1", "Yamaha", 20),
            new Van("V1", "Ford", 80)
        );
        
        tableView.setItems(vehicles);
    }
    
    @FXML
    private void rentVehicle() {
        Vehicle v = tableView.getSelectionModel().getSelectedItem();
        
        if (v != null) {
            try {
                v.rent();
                tableView.refresh();
            }
            catch (VehicleNotAvailableException e) {
                showAlert(e.getMessage());
            }
        }
    }
    
    @FXML
    private void returnVehicle() {
        Vehicle v = tableView.getSelectionModel().getSelectedItem();
        
        if (v != null) {
            v.returnVehicle();
            tableView.refresh();
        }
    }
    
    private void showAlert(String msg) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setContentText(msg);
        alert.show();
    }
}
