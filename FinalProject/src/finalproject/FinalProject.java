/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMain.java to edit this template
 */
package finalproject;

import java.io.IOException;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;
import javafx.fxml.FXMLLoader;
import controller.SceneController;


/**
 *
 * @author Mahmoud Ehab
 */
public class FinalProject extends Application {
    
    @Override
    public void start(Stage primaryStage) throws IOException {
        SceneController.setStage(primaryStage);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/view/MainView.fxml"));
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.setTitle("Vehicle Rental System");
        primaryStage.getIcons().add(new Image(getClass().getResourceAsStream("/Resources/Icons/icon2.png")));
        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
