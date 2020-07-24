/*
 * Course: CS1021-021
 * Winter 2018
 * Lab 7: Shapes Revisited
 * Name: Stuart Harley
 * Created: 1/24/2019
 */
package harleys;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * This class describes a Rectangle object with a text label
 * in the center that extends the abstract Shape object
 */
public class LabeledRectangle extends Rectangle {

    private final String name;

    /**
     * Constructor for the Labeled Rectangle object
     * @param x the lower left corner x-value of the rectangle
     * @param y the lower left corner y-value of the rectangle
     * @param width the width of the rectangle
     * @param height the height of the rectangle
     * @param color the color of the rectangle
     * @param name the text assigned to the label
     */
    public LabeledRectangle(double x, double y, double width,
                            double height, Color color, String name) {
        super(x, y, width, height, color);
        this.name = name;
    }

    /**
     * a method that draws the Labeled Rectangle using the WinPlotter applet
     * @param plotter an instance of a WinPlotter object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        super.draw(plotter);
        plotter.printAt(x+.1*width, y+.5*height, name);
    }
}
