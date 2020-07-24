/*
 * Course: CS1021-021
 * Winter 2018
 * Lab 7: Shapes Revisited
 * Name: Stuart Harley
 * Created: 1/24/2019
 */

package harleys;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.TextInputDialog;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import java.io.IOException;
import java.nio.file.Path;
import java.util.*;

/**
 * GUI class for creating shapes
 */
public class ShapeLoaderApp extends Application {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Use the shape class and its descendants to draw shapes described
     * by a file of the user's choosing.
     * @param stage Default stage given to a JavaFX program. Unused.
     */
    @Override
    public void start(Stage stage) {
        WinPlotterFX plotter = new WinPlotterFX();
        TextInputDialog dialog = new TextInputDialog("");
        dialog.setTitle("File Chooser");
        dialog.setHeaderText("Choose file");
        dialog.setContentText("File name: ");
        Optional<String> filename = dialog.showAndWait();
        try(Scanner in = new Scanner(Path.of(filename.get()))) {
            plotter.setTitle(in.nextLine().trim());
            int width = in.nextInt();
            String dimension = in.nextLine().trim();
            int height = Integer.parseInt(dimension);
            plotter.setWindowSize(width, height);
            Color backgroundColor = stringToColor(in.nextLine().trim());
            plotter.setBackgroundColor(backgroundColor.getRed(),
                    backgroundColor.getGreen(), backgroundColor.getBlue());
            List<Shape> shapes = readShapes(in);
            drawShapes(shapes, plotter);
            plotter.showPlotter();
        } catch(IOException | InputMismatchException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText("Picture Header Information Error");
            alert.setContentText("Header Information incorrectly formatted");
            alert.showAndWait();
        } catch(NoSuchElementException e){
            return;
        }
    }

    /**
     * Reads all lines after the header information (lines 4 - end) and stores each shape in a
     * List of shapes. This method uses the parseShape() method to create the appropriate shape.
     * If parseShape() throws an exception, no shape is added for that line of the input file
     * and an error alert is shown to the user indicating which line was incorrectly formatted.
     * @param in the Scanner holding lines 4 - end of the input file
     * @return a list of shapes created from the input file
     */
    private static List<Shape> readShapes(Scanner in) {
        List<Shape> shapes = new ArrayList<>();
        int currentLine = 4;
        while(in.hasNextLine()) {
            try {
                shapes.add(parseShape(in.nextLine().trim()));
            } catch(InputMismatchException e) {
                System.out.println("Shape data on line " + currentLine +
                        " incorrectly formatted");
            }
            currentLine++;
        }
        return shapes;
    }

    /**
     * Accepts a String that contains one line from the input file and return an
     * instance of the appropriate shape. The line from the input file should represent
     * one of the known shapes in the correct format. If the format does not match one of
     * the specified shapes, a InputMismatchException is thrown.
     * @param line the line of text from the input file representing the shape
     * @return a shape object represented from the file
     * @throws InputMismatchException if the format is not correct to make a shape
     */
    private static Shape parseShape(String line) throws InputMismatchException {
        try {
            Shape shape;
            String format = line.substring(0, 3).trim();
            line = line.substring(3).trim();
            Scanner scanner = new Scanner(line);
            double x = scanner.nextDouble();
            double y = scanner.nextDouble();
            String hexTriplet = scanner.next();
            Color color = stringToColor(hexTriplet);
            switch (format) {
                case "P:":
                    shape = new Point(x, y, color);
                    break;
                case "C:":
                    double radius = scanner.nextDouble();
                    shape = new Circle(x, y, radius, color);
                    break;
                case "T:":
                    double base = scanner.nextDouble();
                    double height = scanner.nextDouble();
                    shape = new Triangle(x, y, base, height, color);
                    break;
                case "R:":
                    double width = scanner.nextDouble();
                    height = scanner.nextDouble();
                    shape = new Rectangle(x, y, width, height, color);
                    break;
                case "LT:":
                    base = scanner.nextDouble();
                    height = scanner.nextDouble();
                    String label = scanner.nextLine().trim();
                    shape = new LabeledTriangle(x, y, base, height, color, label);
                    break;
                case "LR:":
                    width = scanner.nextDouble();
                    height = scanner.nextDouble();
                    label = scanner.nextLine().trim();
                    shape = new LabeledRectangle(x, y, width, height, color, label);
                    break;
                default:
                    throw new InputMismatchException("This line does not represent a Shape");
            }
            return shape;
        } catch(IllegalArgumentException | InputMismatchException e) {
            throw new InputMismatchException("This line does not represent a Shape");
        }
    }

    /**
     * Draws all of the shapes in the shape list in the designated WinPlotterFX
     * @param shapes the list of shapes to be drawn
     * @param plotter the WinPlotterFX to draw the shapes in
     */
    private static void drawShapes(List<Shape> shapes, WinPlotterFX plotter) {
        for(Shape shape : shapes) {
            shape.draw(plotter);
        }
    }

    /**
     * Takes in a String that is a hex triplet and return a color instance of that color
     * @param hexTriplet the hex triplet color pattern
     * @return the color represented by the hex triplet
     */
    private static Color stringToColor(String hexTriplet) throws InputMismatchException{
        if(hexTriplet.length()==7 && hexTriplet.substring(0, 1).equals("#")) {
            double red = (Integer.parseInt(hexTriplet.substring(1, 3), 16)) / 255.0;
            double green = (Integer.parseInt(hexTriplet.substring(3, 5), 16)) / 255.0;
            double blue = (Integer.parseInt(hexTriplet.substring(5), 16)) / 255.0;
            return new Color(red, green, blue, 1);
        } else {
            throw new InputMismatchException("Not a valid hexTriplet");
        }
    }
}