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
 * This class describes a Point object that extends the abstract Shape object
 */
public class Point extends Shape {

    /**
     * Constructor for the Point object
     * @param x x-coordinate for the Point
     * @param y y-coordinate for the Point
     * @param color the color of the Point
     */
    public Point(double x, double y, Color color) {
        super(x, y, color);
    }

    /**
     * a method that draws the Point using the WinPlotter applet
     * @param plotter an instance of a WinPlotter object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        plotter.drawPoint(x, y);
    }
}
