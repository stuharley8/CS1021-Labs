/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab 4 - Inheritance with Shapes
 * Name: Stuart Harley
 * Created: 12/19/2018
 */

package harleys;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.scene.paint.Color;

/**
 * This class describes a Triangle object that extends the abstract Shape object
 */
public class LabeledTriangle extends Triangle {

    private final String name;

    /**
     * Constructor for the Labeled Triangle object
     * @param x The lower left corner x-value of the Triangle
     * @param y The lower left corner y-value of the Triangle
     * @param base The length of the base of the Triangle
     * @param height The height of the Triangle
     * @param color The color of the Triangle
     * @param name The text assigned to the label
     */
    public LabeledTriangle(double x, double y, double base,
                            double height, Color color, String name) {
        super(x, y, base, height, color);
        this.name = name;
    }

    /**
     * a method that draws the Labeled Triangle using th WinPlotter applet
     * @param plotter an instance of a WinPlotter object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        super.draw(plotter);
        plotter.printAt(x+1.0/3*base, y+1.0/3*height, name);
    }
}
