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
 * This class describes a Rectangle object that extends the abstract Shape object
 */
public class Rectangle extends Shape {

    protected final double height;
    protected final double width;

    /**
     * Constructor for the Rectangle object
     * @param x the lower left corner x-value of the Rectangle
     * @param y the lower left corner y-value of the Rectangle
     * @param width the width of the Rectangle
     * @param height the height of the Rectangle
     * @param color the color of the Rectangle
     * @throws IllegalArgumentException if the height or width is negative
     */
    public Rectangle(double x, double y, double width, double height,
                     Color color) throws IllegalArgumentException {
        super(x, y, color);
        if(height>=0 && width>=0) {
            this.height = height;
            this.width = width;
        } else {
            throw new IllegalArgumentException("Can't create a rectangle " +
                    "with a negative height and/or width");
        }
    }

    /**
     * a method that draws the Rectangle using the WinPlotter applet
     * @param plotter an instance of a WinPlotter object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        plotter.moveTo(x, y);
        plotter.drawTo(x, y+height);
        plotter.drawTo(x+width, y+height);
        plotter.drawTo(x+width, y);
        plotter.drawTo(x, y);
    }
}
