/*
 * Course: CS-1021-021
 * Winter 2018
 * Lab 6 - Exceptions
 * Name: Stuart Harley
 * Created: 1/17/19
 */

package harleys;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

/**
 * This class runs the Website Tester
 */
public class Lab6 extends Application {

    /**
     * Start method of the JavaFX program
     * @param stage the stage
     * @throws IOException if there is a problem loading Lab6.fxml
     */
    @Override
    public void start(Stage stage) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("Lab6.fxml"));
        stage.setTitle("Website Tester");
        stage.setResizable(false);
        stage.setScene(new Scene(root));
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
