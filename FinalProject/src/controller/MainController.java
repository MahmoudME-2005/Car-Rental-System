/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package controller;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import DataBase.CustomerDataBase;
import Security.SecurityUtils;
import java.io.IOException;
import model.Customer; //temp
/**
 * FXML Controller class
 *
 * @author Mahmoud Ehab
 */
public class MainController extends SceneController {

    @FXML private TextField name;
    @FXML private TextField password;
    @FXML private Label stateLabel;
    @FXML private Button Login;
    @FXML private Button Register;

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize()
    {
        stateLabel.setVisible(false);
        Register.setOnAction(e -> handleRegister());
        Login.setOnAction(e -> handleLogin());
    }    
    
    private void handleRegister()
    {
        boolean state = DataBase.CustomerDataBase.register(name.getText(), password.getText());

        if (state == true)
        {
            stateLabel.setText("Sucess!!");
            stateLabel.setStyle("-fx-text-fill: green");
            stateLabel.setVisible(true);
        }
        else
        {
            stateLabel.setText("Username is already taken");
            stateLabel.setStyle("-fx-text-fill: red");
            stateLabel.setVisible(true);
        }
        
        for (Customer c: DataBase.CustomerDataBase.customers)
        {
            System.out.println(c.getUserName());
        }
    }
    
    private void handleLogin()
    {
        boolean isAdmin = SecurityUtils.authenticateAdmin(name.getText(), password.getText());
        boolean isCustomer = SecurityUtils.authenticateCustomer(name.getText(), password.getText());
        
        if (isAdmin)
        {
            System.out.println("Enter mr.Admin");
            switchToScene("/view/Admin.fxml");
        }
        else if (isCustomer)
        {
            System.out.println("Enter mr.Customer");
            switchToScene("/view/Rent.fxml");
        }
        else
        {
            stateLabel.setText("Invalid credentials");
            stateLabel.setStyle("-fx-text-fill: red");
            stateLabel.setVisible(true);
        }
    }
}
