/*
 * Course: CS1021-021
 * Winter 2018
 * Lab 9: Final Project Continued
 * Name: Stuart Harley
 * Created: 2/7/2019
 */

package harleys;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;
import java.nio.file.Path;

/**
 * Controller class for the lab9 fxml
 */
public class Lab9Controller {

    private Stage kernelStage;

    private Stage stage;

    private Image currentImage;

    private boolean isGrayscale;

    @FXML
    private Button showFilter;

    @FXML
    private ImageView imageWindow;

    void setPrimaryStage(Stage stage) {
        this.stage = stage;
    }

    void setKernelStage(Stage kernelStage) {
        this.kernelStage = kernelStage;
    }

    public ImageView getImageWindow() {
        return imageWindow;
    }

    /**
     * Handler method for loading an image
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void load(ActionEvent actionEvent) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Image Chooser");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Image Files",
                "*.gif", "*.jpg", "*.png", "*.tiff", "*.msoe", "*.bmsoe"));
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
     * This method applies the specified transformation to each pixel in the image to produce
     * a transformed image
     * @param image the image to be transformed
     * @param transform the behavior of the desired transformation
     * @return the transformed image
     */
    private static Image transformImage(Image image, Transformable transform) {
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        WritableImage newImage = new WritableImage(width, height);
        PixelReader reader = image.getPixelReader();
        PixelWriter writer = newImage.getPixelWriter();
        for(int row = 0; row < height; row++) {
            for(int column = 0; column < width; column++) {
                Color color = reader.getColor(column, row);
                color = transform.apply(row, color);
                writer.setColor(column, row, color);
            }
        }
        return newImage;
    }

    /**
     * Handler method for converting an image to grayscale
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void grayscale(ActionEvent actionEvent) {
        if(imageWindow.getImage() != null && !isGrayscale) {
            Image image = imageWindow.getImage();
            Transformable grayTransform = ((y, color) -> color.grayscale());
            imageWindow.setImage(transformImage(image, grayTransform));
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
            Transformable negativeTransform = ((y, color) -> color.invert());
            imageWindow.setImage(transformImage(image, negativeTransform));
        }
    }

    /**
     * Handler method for adding a red filter to an image
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void red(ActionEvent actionEvent) {
        if(imageWindow.getImage() != null) {
            Image image = imageWindow.getImage();
            Transformable redTransform = ((y, color) -> new Color(color.getRed(),
                    0, 0, color.getOpacity()));
            imageWindow.setImage(transformImage(image, redTransform));
        }
    }

    /**
     * Handler method for performing a red-gray shift on an image
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void redGray(ActionEvent actionEvent) {
        if(imageWindow.getImage() != null) {
            Image image = imageWindow.getImage();
            Transformable redGrayTransform = ((y, color) -> {
                Color transformedColor;
                if(y%2==0) {
                    transformedColor = new Color(color.getRed(),
                            0, 0, color.getOpacity());
                } else {
                    transformedColor = color.grayscale();
                }
                return transformedColor;
            });
            imageWindow.setImage(transformImage(image, redGrayTransform));
        }
    }

    /**
     * Handler method for showing filter kernel window
     * @param actionEvent the ActionEvent
     */
    @FXML
    public void showFilter(ActionEvent actionEvent) {
        if(showFilter.getText().equals("Show Filter")) {
            kernelStage.setX(stage.getX());
            kernelStage.setY(stage.getY() + stage.getHeight());
            kernelStage.show();
            showFilter.setText("Hide Filter");
        } else {
            kernelStage.close();
            showFilter.setText("Show Filter");
        }
    }
}
