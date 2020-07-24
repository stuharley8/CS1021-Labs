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
 * An abstract class that describes a generic shape
 * which includes it's color and location on a grid.
 */
public abstract class Shape {

    private Color color;
    protected final double x;
    protected final double y;

    /**
     * Constructor for the Shape class
     * @param x starting x-coordinate of the Shape
     * @param y starting y-coordinate of the Shape
     * @param color the color of the Shape
     */
    public Shape(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.color = color;
    }

    /**
     * An abstract method definition for drawing the subclasses ini a WinPlotter object
     * @param plotter WinPlotter object that we are drawing the shape in
     */
    public abstract void draw(WinPlotterFX plotter);

    /**
     * This method sets the Pen color for the WinPlotter object to match the color of the shape
     * @param plotter WinPlotter object that we are drawing the Shape in
     */
    public void setPenColor(WinPlotterFX plotter) {
        plotter.setPenColor(color.getRed(), color.getGreen(), color.getBlue());
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
