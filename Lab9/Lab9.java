/*
 * Course: CS1021-021
 * Winter 2018
 * Lab 9: Final Project Continued
 * Name: Stuart Harley
 * Created: 2/7/2019
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
public class Lab9 extends Application {

    /**
     * Start method of the JavaFX program
     * @param stage the stage
     * @throws IOException if there is a problem loading Lab9.fxml
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader mainLoader = new FXMLLoader();
        Parent root = mainLoader.load(getClass().getResource("Lab9.fxml").openStream());
        stage.setTitle("Image Manipulator");
        stage.setResizable(false);
        stage.setScene(new Scene(root));

        FXMLLoader kernelLoader = new FXMLLoader();
        Stage kernelStage = new Stage();
        Parent kernelRoot = kernelLoader.load(getClass().getResource("KernelUI.fxml").openStream());
        kernelStage.setTitle("Filter Kernel");
        kernelStage.setResizable(false);
        kernelStage.setScene(new Scene(kernelRoot));
        kernelStage.setAlwaysOnTop(true);

        Lab9Controller mainController = mainLoader.getController();
        mainController.setKernelStage(kernelStage);
        mainController.setPrimaryStage(stage);
        KernelController kernelController = kernelLoader.getController();
        kernelController.setStage(kernelStage);
        kernelController.setController(mainController);

        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
