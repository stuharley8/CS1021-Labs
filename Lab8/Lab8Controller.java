/*
 * Course: CS1021-021
 * Winter 2018
 * Lab 8: Final Project
 * Name: Stuart Harley
 * Created: 1/30/2019
 */

package harleys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import java.io.File;
import java.nio.file.Path;

/**
 * Controller class for the lab8 fxml
 */
public class Lab8Controller {

    private Image currentImage;

    private boolean isGrayscale;

    @FXML
    private ImageView imageWindow;

    /**
     * Handler method for loading an image
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void load(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Image Chooser");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",
                "*.gif", "*.jpg", "*.png", "*.tiff", "*.msoe"));
        try {
            File file = fileChooser.showOpenDialog(null);
            Path path = file.toPath();
            currentImage = ImageIO.read(path);
            imageWindow.setImage(currentImage);
        } catch (IllegalArgumentException | NullPointerException e) {
            System.out.println("Error loading image");
        }
        isGrayscale = false;
    }

    /**
     * Handler method for reloading an image. Reload the most recently
     * loaded image (does not show the file chooser dialog)
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void reload(ActionEvent actionEvent) {
        if(currentImage != null) {
            imageWindow.setImage(currentImage);
            isGrayscale = false;
        }
    }

    /**
     * Handler method for saving an image
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void save(ActionEvent actionEvent) {
        if(imageWindow.getImage() != null) {
            FileChooser fileSaver = new FileChooser();
            fileSaver.setTitle("Image Saver");
            File savedFile = fileSaver.showSaveDialog(null);
            if (savedFile != null) {
                try {
                    ImageIO.write(currentImage, savedFile.toPath());
                } catch (IllegalArgumentException e) {
                    System.out.println("Problem saving file");
                }
            } else {
                System.out.println("Problem saving file");
            }
        }
    }

    /**
     * Handler method for converting an image to grayscale
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void grayscale(ActionEvent actionEvent) {
        if(imageWindow.getImage() != null && !isGrayscale) {
            Image image = imageWindow.getImage();
            int width = (int)image.getWidth();
            int height = (int)image.getHeight();
            WritableImage newImage = new WritableImage(width, height);
            PixelReader reader = image.getPixelReader();
            PixelWriter writer = newImage.getPixelWriter();
            for(int row = 0; row < height; row++) {
                for(int column = 0; column < width; column++) {
                    Color color = reader.getColor(column, row);
                    double grayColor = color.getRed()*.2126 + color.getGreen()*.7152
                            + color.getBlue()*.0722;
                    color = new Color(grayColor, grayColor, grayColor, 1);
                    writer.setColor(column, row, color);
                }
            }
            imageWindow.setImage(newImage);
            isGrayscale = true;
        }
    }

    /**
     * Handler method for converting an image to its photo negative
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void negative(ActionEvent actionEvent) {
        if(imageWindow.getImage() != null) {
            Image image = imageWindow.getImage();
            int width = (int) image.getWidth();
            int height = (int) image.getHeight();
            WritableImage newImage = new WritableImage(width, height);
            PixelReader reader = image.getPixelReader();
            PixelWriter writer = newImage.getPixelWriter();
            for (int row = 0; row < height; row++) {
                for (int column = 0; column < width; column++) {
                    Color color = reader.getColor(column, row);
                    double red = 1.0 - color.getRed();
                    double green = 1.0 - color.getGreen();
                    double blue = 1.0 - color.getBlue();
                    color = new Color(red, green, blue, 1);
                    writer.setColor(column, row, color);
                }
            }
            imageWindow.setImage(newImage);
        }
    }
}
