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
 * This class describes a Triangle object that extends the abstract Shape object
 */
public class Triangle extends Shape {

    protected final double base;
    protected final double height;

    /**
     * Constructor for the Triangle object
     * @param x The lower left x-value of the Triangle
     * @param y The lower left y-value of the Triangle
     * @param base The length of the base of the Triangle
     * @param height The height of the Triangle
     * @param color The color of the Triangle
     * @throws IllegalArgumentException if the base and/or height is negative
     */
    public Triangle(double x, double y, double base, double height,
                    Color color) throws IllegalArgumentException {
        super(x, y, color);
        if(base>=0 && height>=0) {
            this.base = base;
            this.height = height;
        } else {
            throw new IllegalArgumentException("Can't create a triangle" +
                    " with a negative base and/or height");
        }
    }

    /**
     * A method that draws the Triangle using the WinPlotter applet
     * @param plotter an instance of a WinPlotter object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        plotter.moveTo(x, y);
        plotter.drawTo(x+.5*base, y+height);
        plotter.drawTo(x+base, y);
        plotter.drawTo(x, y);
    }
}
