/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import DataBase.CustomerDataBase;
import DataBase.AdminDataBase;
import model.Bike;
import model.Vehicle;
import model.Booking;
import model.Car;
import model.Van;
import model.VehicleNotAvailableException;


/**
 * FXML Controller class
 *
 * @author Mahmoud Ehab
 */
public class AdminController {

        // ===== Vehicles Tab =====
    @FXML private TableView<Vehicle> vehiclesTable;

    @FXML private TableColumn<Vehicle, String> vehicleIdCol;

    @FXML private TableColumn<Vehicle, String> vehicleBrandCol;

    @FXML private TableColumn<Vehicle, Double> vehiclePriceCol;

    @FXML private TableColumn<Vehicle, String> vehicleAvailabilityCol;

    @FXML private Button addVehicleButton;

    @FXML private Button removeVehicleButton;
    
    @FXML private Button changeAvailButton;

    // ===== Booking Tab =====
    @FXML private TableView<Booking> bookingsTable;

    @FXML private TableColumn<Booking, String> bookingIdCol;
    
    @FXML private TableColumn<Booking, String> bookingCustomerCol;

    @FXML private TableColumn<Booking, String> bookingBrandCol;

    @FXML private TableColumn<Booking, Integer> bookingDaysCol;

    @FXML private TableColumn<Booking, Double> bookingTotalPriceCol;

    @FXML private Button addBookingButton;
    
    @FXML private Button removeBookingButton;

    // ===== General =====
    @FXML private TabPane tabPane;

    @FXML private Button backButton;
    
    private ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
    
    private ObservableList<Booking> bookings = FXCollections.observableArrayList();

    public void initialize() {
        
        vehicleIdCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        
        vehicleBrandCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getBrand()));
        
        vehiclePriceCol.setCellValueFactory(data-> new javafx.beans.property.SimpleObjectProperty<Double>(data.getValue().getPricePerDay()));
        
        vehicleAvailabilityCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().isAvailable() ? "Yes" : "No"));
        
        bookingIdCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        
        bookingCustomerCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomer().getUserName()));
        
        bookingBrandCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getVehicle().getBrand()));
        
        bookingDaysCol.setCellValueFactory(data-> new javafx.beans.property.SimpleObjectProperty<Integer>(data.getValue().getDays()));
        
        bookingTotalPriceCol.setCellValueFactory(data-> new javafx.beans.property.SimpleObjectProperty<Double>(data.getValue().getTotalPrice()));
        
        vehicles.addAll(
            new Car("C1", "Toyota", 50),
            new Bike("B1", "Yamaha", 20),
            new Van("V1", "Ford", 80)
        );
        try
        {
            bookings.addAll(
                    new Booking("1", CustomerDataBase.customers.get(0), vehicles.get(0), 5),
                    new Booking("2", CustomerDataBase.customers.get(1), vehicles.get(1), 7)
            );
        }
        catch (VehicleNotAvailableException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        vehiclesTable.setItems(vehicles);
        
        bookingsTable.setItems(bookings);
        
        System.out.println("RentController initialized successfully");

        // Future:
        // - configure table columns
        // - load data
        // - set button actions
    }

    // ===== Button Actions =====

    @FXML
    private void handleRent() {
        System.out.println("Rent button clicked");
    }

    @FXML
    private void handleReturn() {
        System.out.println("Return button clicked");
    }

    @FXML
    private void handleCancelBooking() {
        System.out.println("Cancel booking clicked");
    }

    @FXML
    private void handleBack() {
        System.out.println("Back button clicked");
        // Example:
        // SceneController.switchTo("Main");
    }
}
