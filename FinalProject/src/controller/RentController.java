/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import DataBase.CustomerDataBase;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TabPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Booking;
import model.Car;
import model.Vehicle;
import model.VehicleNotAvailableException;

/**
 * FXML Controller class
 *
 * @author Mahmoud Ehab
 */
public class RentController extends AdminController {

    @FXML
    private TableView<Vehicle> vehiclesTable;
    
    @FXML
    private TableColumn<Vehicle, String> vehicleIdCol;
    
    @FXML
    private TableColumn<Vehicle, String> vehicleBrandCol;
    
    @FXML
    private TableColumn<Vehicle, Double> vehiclePriceCol;
    
    @FXML
    private TableColumn<Vehicle, String> vehicleAvailabilityCol;

    @FXML
    private TableView<Booking> bookingsTable;
    
    @FXML
    private TableColumn<Booking, String> bookingIdCol;
    
    @FXML
    private TableColumn<Booking, String> bookingCustomerCol;
    
    @FXML
    private TableColumn<Booking, String> bookingBrandCol;

    @FXML
    private TableColumn<Booking, Integer> bookingDaysCol;

    @FXML
    private TableColumn<Booking, Double> bookingTotalPriceCol;
    
    @FXML
    private TabPane tabPane;

    @FXML
    private Button rentVehicleButton;

    @FXML
    private Button returnVehicleButton;
    
    @FXML
    private Button cancelBookingButton;
    
    @FXML
    private Button backButton1;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        vehicleIdCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        
        vehicleBrandCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getBrand()));
        
        vehiclePriceCol.setCellValueFactory(data-> new javafx.beans.property.SimpleObjectProperty<Double>(data.getValue().getPricePerDay()));
        
        vehicleAvailabilityCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().isAvailable() ? "Yes" : "No"));
        
        bookingIdCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getId()));
        
        bookingCustomerCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getCustomer().getUserName()));
        
        bookingBrandCol.setCellValueFactory(data-> new javafx.beans.property.SimpleStringProperty(data.getValue().getVehicle().getBrand()));
        
        bookingDaysCol.setCellValueFactory(data-> new javafx.beans.property.SimpleObjectProperty<Integer>(data.getValue().getDays()));
        
        bookingTotalPriceCol.setCellValueFactory(data-> new javafx.beans.property.SimpleObjectProperty<Double>(data.getValue().getTotalPrice()));
        
        vehiclesTable.setItems(vehicles);
        
        bookingsTable.setItems(bookings);
        
        rentVehicleButton.setOnAction(e -> rentVehicle());
        
        returnVehicleButton.setOnAction(e -> returnVehicle());
        
        cancelBookingButton.setOnAction(e -> cancelBooking());
        
        backButton1.setOnAction(e -> handleBack());
    }
    
    public void rentVehicle()
    {
        Vehicle rentedVehicle = vehiclesTable.getSelectionModel().getSelectedItem();
        try
        {
            rentedVehicle.rent();
            TextField numberOfDays = new TextField();

            numberOfDays.setPromptText("Number Of Days:");

            VBox content = new VBox(10, new Label("Brand:"), numberOfDays);

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
            stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/Icons/icon.png")));
            alert.setTitle("Rent vehicle");
            alert.setHeaderText("For how many days?");
            alert.getDialogPane().setContent(content);

            alert.showAndWait().ifPresent(result -> {
                if (result == ButtonType.OK) {
                    try
                    {
                        int days = Integer.parseInt(numberOfDays.getText());
                        bookings.add(new Booking (CustomerDataBase.getActiveUser(), rentedVehicle, days));
                    }
                    catch (VehicleNotAvailableException ex)
                    {
                        System.out.println("Vehicle Not Available");
                    }
                }
            });
        }
        catch (VehicleNotAvailableException ex)
        {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Occurred");
            alert.setHeaderText(null); // Setting this to null makes it look cleaner
            alert.setContentText(ex.getMessage());
            alert.showAndWait();
        }
    }
    
    public void returnVehicle()
    {
        Vehicle returnedVehicle = vehiclesTable.getSelectionModel().getSelectedItem();
        returnedVehicle.returnVehicle();
    }
    
    public void cancelBooking()
    {
        
    }
    
    public void handleBack()
    {
        switchToScene("/view/MainView.fxml");
    }
}
