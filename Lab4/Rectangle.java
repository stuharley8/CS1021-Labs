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
     */
    public Rectangle(double x, double y, double width, double height, Color color) {
        super(x, y, color);
        this.height = height;
        this.width = width;
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
