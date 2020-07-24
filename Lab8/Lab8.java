/*
 * Course: CS1021-021
 * Winter 2018
 * Lab 8: Final Project
 * Name: Stuart Harley
 * Created: 1/30/2019
 */

package harleys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class runs the Image Manipulator
 */
public class Lab8 extends Application {

    /**
     * Start method of the JavaFX program
     * @param stage the stage
     * @throws IOException if there is a problem loading Lab8.fxml
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Lab8.fxml"));
        stage.setTitle("Image Manipulator");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
