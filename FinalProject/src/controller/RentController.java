/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Mahmoud Ehab
 */
public class RentController {

    @FXML
    private Button vanbtn;
    @FXML
    private Button bikebtn;
    @FXML
    private Button carbtn;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        vanbtn.setOnAction(e -> rentVan());
        bikebtn.setOnAction(e -> rentBike());
        carbtn.setOnAction(e -> rentCar());
    }    
    
    private void rentCar()
    {
        System.out.println("Rented a Car");
    }
    
    private void rentVan()
    {
        System.out.println("Rented a Van");
    }
    
    private void rentBike()
    {
        System.out.println("Rented a Bike");
    }
}
