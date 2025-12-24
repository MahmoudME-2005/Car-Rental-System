/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
/**
 *
 * @author Mahmoud Ehab
 */
public class SceneController {
    private Stage stage;
    private Scene scene;
    private Parent root;

    public void switchToScene(ActionEvent event, String fxmlFilePath) throws IOException {
        // 1. Load the FXML file
        // Note: The path must be relative to the classpath. 
        // Based on your folder structure, it likely needs to start with "/view/"
        root = FXMLLoader.load(getClass().getResource(fxmlFilePath));

        // 2. Get the current stage from the event (the button that was clicked)
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        // 3. Create the new scene and set it on the stage
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
