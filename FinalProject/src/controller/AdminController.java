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
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import model.Admin;
import model.Bike;
import model.Vehicle;
import model.Booking;
import model.Car;
import model.Customer;
import model.Van;
import model.VehicleNotAvailableException;


/**
 * FXML Controller class
 *
 * @author Mahmoud Ehab
 */
public class AdminController extends SceneController {

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
    
    @FXML private Button removeBookingButton;
    
    // ===== Customers Tab =====
    
    @FXML private TableView<Customer> customersTable;
    
    @FXML private TableColumn<Customer, String> customersCol;
    
    @FXML private Button addCustomerButton;
    
    @FXML private Button removeCustomerButton;
    
    // ===== Admins Tab =====
    
    @FXML private TableView<Admin> adminsTable;
    
    @FXML private TableColumn<Admin, String> adminsCol;
    
    @FXML private Button addAdminButton;
    
    @FXML private Button removerAdminButton;

    // ===== General =====
    @FXML private TabPane tabPane;

    @FXML private Button backButton;
    
    protected final ObservableList<Vehicle> vehicles = FXCollections.observableArrayList();
    
    protected final ObservableList<Booking> bookings = FXCollections.observableArrayList();
    
    private final ObservableList<Customer> customers = FXCollections.observableList(CustomerDataBase.getCustomers());
    
    private final ObservableList<Admin> admins = FXCollections.observableList(AdminDataBase.getAdmins());

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
        
        customersCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUserName()));
        
        adminsCol.setCellValueFactory(data -> new javafx.beans.property.SimpleStringProperty(data.getValue().getUserName()));
        
        vehicles.addAll(
            new Car("Toyota", 50),
            new Bike("Yamaha", 20),
            new Van("Ford", 80)
        );
        try
        {
            bookings.addAll(
                    new Booking(CustomerDataBase.getCustomers().get(0), vehicles.get(0), 5),
                    new Booking(CustomerDataBase.getCustomers().get(1), vehicles.get(1), 7)
            );
        }
        catch (VehicleNotAvailableException ex)
        {
            System.out.println(ex.getMessage());
        }
        
        vehiclesTable.setItems(vehicles);
        
        bookingsTable.setItems(bookings);
        
        customersTable.setItems(customers);
        
        adminsTable.setItems(admins);
        
        addVehicleButton.setOnAction(e -> addVehicle());
        
        removeVehicleButton.setOnAction(e -> removeVehicle());
        
        changeAvailButton.setOnAction(e -> changeAvail());
        
        removeBookingButton.setOnAction(e -> removeBooking());
        
        addCustomerButton.setOnAction(e -> addCustomer());
        
        removeCustomerButton.setOnAction(e -> removeCustomer());
        
        addAdminButton.setOnAction(e -> addAdmin());
        
        removerAdminButton.setOnAction(e -> removerAdmin());
        
        backButton.setOnAction(e -> handleBack());
    }

    // ===== Button Actions =====

    @FXML
    private void addVehicle() {
        TextField brandField = new TextField();
        TextField priceField = new TextField();

        brandField.setPromptText("Brand");
        priceField.setPromptText("Price per day");

        VBox content = new VBox(10,
                new Label("Brand:"), brandField,
                new Label("Price:"), priceField
        );

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/Icons/icon.png")));
        alert.setTitle("Add Vehicle");
        alert.setHeaderText("Enter vehicle details");
        alert.getDialogPane().setContent(content);

        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                String brand = brandField.getText();
                double price = Double.parseDouble(priceField.getText());
                vehicles.add(new Car(brand, price));
            }
        });
    }

    @FXML
    private void removeVehicle() {
        Vehicle removedVehicle = vehiclesTable.getSelectionModel().getSelectedItem();
        vehicles.remove(removedVehicle);
    }

    @FXML
    private void changeAvail() {
        Vehicle changedVehicle = vehiclesTable.getSelectionModel().getSelectedItem();
        changedVehicle.changeAvail();
        vehiclesTable.refresh();
    }
    
    @FXML
    private void removeBooking() {
        Booking removedBooking = bookingsTable.getSelectionModel().getSelectedItem();
        bookings.remove(removedBooking);
    }
    
    @FXML
    private void addCustomer()
    {
        TextField userNameField = new TextField();
        TextField passwordField = new TextField();

        userNameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        VBox content = new VBox(10,
                new Label("Username:"), userNameField,
                new Label("Password:"), passwordField
        );

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/Icons/icon.png")));
        alert.setTitle("Add Customer");
        alert.setHeaderText("Enter customer details");
        alert.getDialogPane().setContent(content);

        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                String userName = userNameField.getText();
                String password = passwordField.getText();
                customers.add(new Customer(userName, password));
            }
        });
    }
    
    @FXML
    private void removeCustomer()
    {
        Customer removedCustomer = customersTable.getSelectionModel().getSelectedItem();
        customers.remove(removedCustomer);
    }
    
    @FXML
    private void addAdmin()
    {
        TextField userNameField = new TextField();
        TextField passwordField = new TextField();

        userNameField.setPromptText("Username");
        passwordField.setPromptText("Password");

        VBox content = new VBox(10,
                new Label("Username:"), userNameField,
                new Label("Password:"), passwordField
        );

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/Icons/icon.png")));
        alert.setTitle("Add Admin");
        alert.setHeaderText("Enter admin details");
        alert.getDialogPane().setContent(content);

        alert.showAndWait().ifPresent(result -> {
            if (result == ButtonType.OK) {
                String userName = userNameField.getText();
                String password = passwordField.getText();
                admins.add(new Admin(userName, password));
            }
        });
    }
    
    @FXML
    private void removerAdmin()
    {
        Admin removedAdmin = adminsTable.getSelectionModel().getSelectedItem();
        admins.remove(removedAdmin);
    }

    @FXML
    private void handleBack() {
        switchToScene("/view/MainView.fxml");
    }
}