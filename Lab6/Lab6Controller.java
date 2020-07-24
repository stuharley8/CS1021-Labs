/*
 * Course: CS-1021-021
 * Winter 2018
 * Lab 6 - Exceptions
 * Name: Stuart Harley
 * Created: 1/17/19
 */

package harleys;

import edu.msoe.se1021.Lab6.WebsiteTester;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.Optional;

/**
 * Controller class for the lab6 fxml
 */
public class Lab6Controller {

    private WebsiteTester tester = new WebsiteTester();

    private Alert alert = new Alert(Alert.AlertType.ERROR);

    @FXML
    private TextField urlTextField;

    @FXML
    private TextField sizeTextField;

    @FXML
    private TextField downloadTimeTextField;

    @FXML
    private TextField portTextField;

    @FXML
    private TextField hostTextField;

    @FXML
    private TextField timeoutTextField;

    @FXML
    private TextArea webPageTextArea;

    /**
     * This method checks to see if a value entered in the timeoutTextField is valid.
     * If it is not valid it creates an alert and resets the value to the previous value.
     * If it is valid it sets the tester timeout variable to the value entered.
     * @param actionEvent the actionEvent
     */
    @FXML
    public void setTimeout(ActionEvent actionEvent) {
        String timeoutAmount = timeoutTextField.getText();
        try {
            tester.setTimeout(timeoutAmount);
        } catch(NumberFormatException e) {
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Invalid Timeout Error");
            alert.setContentText("Timeout must be greater than or equal to 0.");
            alert.showAndWait();
            timeoutTextField.setText(tester.getTimeout());
        }
    }

    /**
     * This method attempts to access the url provided and return the information at said URL.
     * This method provides multiple catch blocks for possible exceptions when attempting
     * to access the URL. If no exceptions are caught, then the website tester GUI will update.
     * @param actionEvent the actionEvent
     */
    @FXML
    public void analyze(ActionEvent actionEvent) {
        String url = urlTextField.getText();
        try {
            tester.openURL(url);
            tester.openConnection();
            tester.downloadText();
            sizeTextField.setText(Integer.toString(tester.getSize()));
            downloadTimeTextField.setText(Long.toString(tester.getDownloadTime()));
            hostTextField.setText(tester.getHostname());
            portTextField.setText(Integer.toString(tester.getPort()));
            webPageTextArea.setText(tester.getContent());
        } catch(MalformedURLException | IllegalArgumentException e) {
            alert.setTitle("Error Dialog");
            alert.setHeaderText("URL Error");
            alert.setContentText("The URL entered in the text box is invalid.");
            alert.showAndWait();
            urlTextField.setText("");
        } catch(UnknownHostException e) {
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Host Error");
            alert.setContentText("Error: Unable to reach the host " + urlTextField.getText());
            alert.showAndWait();
        } catch(SocketTimeoutException e) {
            alert.setTitle("Timeout Dialog");
            alert.setHeaderText("Wait longer?");
            alert.setContentText("There has been a timeout reaching the site. " +
                    "Click OK to extend the timeout period?");
            Optional<ButtonType> result = alert.showAndWait();
            if(result.get() == ButtonType.OK) {
                TextInputDialog dialog = new TextInputDialog("");
                dialog.setTitle("Set Timeout");
                dialog.setHeaderText("Set extended timeout");
                dialog.setContentText("Desired timeout: ");
                Optional<String> extend = dialog.showAndWait();
                try {
                    tester.setTimeout(extend.get());
                    tester.openConnection();
                    tester.downloadText();
                    sizeTextField.setText(Integer.toString(tester.getSize()));
                    downloadTimeTextField.setText(Long.toString(tester.getDownloadTime()));
                    hostTextField.setText(tester.getHostname());
                    portTextField.setText(Integer.toString(tester.getPort()));
                    webPageTextArea.setText(tester.getContent());
                } catch (NumberFormatException exception) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error Dialog");
                    alert.setHeaderText("Invalid Timeout Error");
                    alert.setContentText("Timeout must be greater than or equal to 0.");
                    alert.showAndWait();
                    timeoutTextField.setText(tester.getTimeout());
                } catch (IOException exception) {
                    alert.setTitle("Timeout Dialog");
                    alert.setHeaderText("Timeout Error");
                    alert.setContentText("There has been a timeout reaching the site.");
                    alert.showAndWait();
                }
            }
        } catch(IOException e) {
            alert.setTitle("Error Dialog");
            alert.setHeaderText("File Error");
            alert.setContentText("There was a problem reading from the given file.");
        }
    }
}
