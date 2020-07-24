/*
 * Course: CS1021-021
 * Winter 2018
 * Lab 8: Final Project
 * Name: Stuart Harley
 * Created: 1/30/2019
 */

package harleys;

import edu.msoe.cs1021.ImageUtil;
import javafx.scene.image.*;
import javafx.scene.paint.Color;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.InputMismatchException;
import java.util.NoSuchElementException;
import java.util.Scanner;
import static edu.msoe.cs1021.ImageUtil.readImage;

/**
 * This class is responsible for loading and saving images
 */
public class ImageIO {

    /**
     * Reads in the specified image file and returns a Image object
     * containing the image
     * @param path the path of the image file
     * @return the image object specified from the path
     * @throws IllegalArgumentException if the path passed in has a file
     * extension that specifies an unsupported image format
     */
    public static Image read(Path path) throws IllegalArgumentException {
        Image image;
        try {
            if(path.getFileName().toString().endsWith(".msoe")) {
                image = readMSOE(path);
            } else {
                image = readImage(path);
            }
        } catch(IOException e) {
            throw new IllegalArgumentException("Error loading image");
        }
        return image;
    }

    /**
     * Writes the specified image to the specified path
     * @param image the specified image
     * @param path the specified path
     * @throws IllegalArgumentException if the path passed in has a file
     * extension that specifies an unsupported image format
     */
    public static void write(Image image, Path path) throws IllegalArgumentException {
        try {
            if(path.toString().endsWith(".msoe")) {
                writeMSOE(image, path);
            } else {
                ImageUtil.writeImage(path, image);
            }
        } catch (IOException e) {
            throw new IllegalArgumentException("Problem saving file");
        }
    }

    /**
     * Reads an image file in .msoe format
     * @param path the specified path
     * @return the image from the specified file
     * @throws IllegalArgumentException if the path passed in has a file
     *      * extension that specifies an unsupported image format
     */
    private static Image readMSOE(Path path) throws IllegalArgumentException {
        WritableImage image;
        try(Scanner in = new Scanner(Files.newInputStream(path))) {
            String header = in.nextLine();
            if(!header.equals("MSOE")) {
                throw new InputMismatchException("File not correctly formatted");
            }
            int width = in.nextInt();
            int height = in.nextInt();
            image = new WritableImage(width, height);
            PixelWriter writer = image.getPixelWriter();
            for(int row = 0; row < height; row++) {
                for(int column = 0; column < width; column++) {
                    Color color = stringToColor(in.next());
                    writer.setColor(column, row, color);
                }
            }
        } catch(IOException | NullPointerException | NoSuchElementException e) {
            throw new IllegalArgumentException("Error loading image");
        }
        return image;
    }

    /**
     * Writes an image file in .msoe format
     * @param image the specified image
     * @param path the specified path
     * @throws IllegalArgumentException if there is a problem saving the file
     */
    private static void writeMSOE(Image image, Path path) throws IllegalArgumentException {
        int width = (int)image.getWidth();
        int height = (int)image.getHeight();
        try(PrintWriter out = new PrintWriter(Files.newOutputStream(path))) {
            out.println("MSOE");
            out.println(width + " " + height);
            PixelReader reader = image.getPixelReader();
            for(int row = 0; row < height; row++) {
                for(int column = 0; column < width; column++) {
                    out.print(colorToString(reader.getColor(column, row)) + " ");
                }
                out.println();
            }
        } catch(IOException e) {
            throw new IllegalArgumentException("Error saving file");
        }
    }

    /**
     * Takes in a String that is a hex triplet and return a color instance of that color
     * @param hexTriplet the hex triplet color pattern
     * @return the color represented by the hex triplet
     */
    private static Color stringToColor(String hexTriplet) throws InputMismatchException {
        if(hexTriplet.length()==7 && hexTriplet.substring(0, 1).equals("#")
                && hexTriplet.substring(1).matches("[0-9A-Fa-f]+")) {
            double red = (Integer.parseInt(hexTriplet.substring(1, 3), 16)) / 255.0;
            double green = (Integer.parseInt(hexTriplet.substring(3, 5), 16)) / 255.0;
            double blue = (Integer.parseInt(hexTriplet.substring(5), 16)) / 255.0;
            return new Color(red, green, blue, 1);
        } else {
            throw new InputMismatchException("Not a valid hexTriplet");
        }
    }

    /**
     * Takes in a Color and return a String that is a hex triplet that represents the color
     * @param color the color to be represented
     * @return the hex triplet string
     */
    private static String colorToString(Color color) {
        int red = (int)(color.getRed()*255);
        int green = (int)(color.getGreen()*255);
        int blue = (int)(color.getBlue()*255);
        return String.format("#%02X%02X%02X", red, green, blue);
    }
}
