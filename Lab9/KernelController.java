/*
 * Course: CS1021-021
 * Winter 2018
 * Lab 9: Final Project Continued
 * Name: Stuart Harley
 * Created: 2/7/2019
 */

package harleys;

import edu.msoe.cs1021.ImageUtil;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;


/**
 * Controller class for the KernelUI fxml
 */
public class KernelController {

    private Stage kernelStage;

    private Lab9Controller controller;

    @FXML
    private TextField topLeft;

    @FXML
    private TextField topCenter;

    @FXML
    private TextField topRight;

    @FXML
    private TextField middleLeft;

    @FXML
    private TextField center;

    @FXML
    private TextField middleRight;

    @FXML
    private TextField bottomLeft;

    @FXML
    private TextField bottomCenter;

    @FXML
    private TextField bottomRight;

    void setStage(Stage kernelStage) {
        this.kernelStage = kernelStage;
    }

    void setController(Lab9Controller controller) {
        this.controller = controller;
    }

    /**
     * Handler method for the blur button
     * Sets all kernel values to positive
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void blur(ActionEvent actionEvent) {
        try {
            topLeft.setText(Math.abs(Double.parseDouble(topLeft.getText())) + "");
            topCenter.setText(Math.abs(Double.parseDouble(topCenter.getText())) + "");
            topRight.setText(Math.abs(Double.parseDouble(topRight.getText())) + "");
            middleLeft.setText(Math.abs(Double.parseDouble(middleLeft.getText())) + "");
            center.setText(Math.abs(Double.parseDouble(center.getText())) + "");
            middleRight.setText(Math.abs(Double.parseDouble(bottomLeft.getText())) + "");
            bottomLeft.setText(Math.abs(Double.parseDouble(bottomLeft.getText())) + "");
            bottomCenter.setText(Math.abs(Double.parseDouble(topCenter.getText())) + "");
            bottomRight.setText(Math.abs(Double.parseDouble(bottomRight.getText())) + "");
        } catch(NumberFormatException e) {
            System.out.println("Filter kernel values incorrect/missing");
        }
    }

    /**
     * Handler method for the sharpen button
     * Sets all kernel values to negative except the center value which is set to positive
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void sharpen(ActionEvent actionEvent) {
        try {
            topLeft.setText(-Math.abs(Double.parseDouble(topLeft.getText())) + "");
            topCenter.setText(-Math.abs(Double.parseDouble(topCenter.getText())) + "");
            topRight.setText(-Math.abs(Double.parseDouble(topRight.getText())) + "");
            middleLeft.setText(-Math.abs(Double.parseDouble(middleLeft.getText())) + "");
            center.setText(Math.abs(Double.parseDouble(center.getText())) + "");
            middleRight.setText(-Math.abs(Double.parseDouble(bottomLeft.getText())) + "");
            bottomLeft.setText(-Math.abs(Double.parseDouble(bottomLeft.getText())) + "");
            bottomCenter.setText(-Math.abs(Double.parseDouble(topCenter.getText())) + "");
            bottomRight.setText(-Math.abs(Double.parseDouble(bottomRight.getText())) + "");
        } catch(NumberFormatException e) {
            System.out.println("Filter kernel values incorrect/missing");
        }
    }

    /**
     * Handler method for the apply button
     * Applies the set filter kernel to the image
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void apply(ActionEvent actionEvent) {
        try {
            ImageView imageWindow = controller.getImageWindow();
            double[] kernel = new double[9];
            double divisor = 0;
            kernel[0] = Double.parseDouble(topLeft.getText());
            kernel[1] = Double.parseDouble(topCenter.getText());
            kernel[2] = Double.parseDouble(topRight.getText());
            kernel[3] = Double.parseDouble(middleLeft.getText());
            kernel[4] = Double.parseDouble(center.getText());
            kernel[5] = Double.parseDouble(middleRight.getText());
            kernel[6] = Double.parseDouble(bottomLeft.getText());
            kernel[7] = Double.parseDouble(bottomCenter.getText());
            kernel[8] = Double.parseDouble(bottomRight.getText());
            for (double d : kernel) {
                divisor += d;
            }
            if (divisor != 0) {
                for (int i = 0; i < 9; i++) {
                    kernel[i] = kernel[i] / divisor;
                }
                imageWindow.setImage(ImageUtil.convolve(imageWindow.getImage(), kernel));
            } else {
                System.out.println("Unable to apply kernel, sums add to 0");
            }
        } catch(NumberFormatException | NullPointerException e) {
            System.out.println("Unable to apply filter kernel");
        }
    }
}
