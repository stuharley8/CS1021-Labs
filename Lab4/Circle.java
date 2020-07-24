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

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * This class describes a Circle object that extends the abstract Shape object
 */
public class Circle extends Shape {

    private final double radius;

    /**
     * Constructor for the Circle object
     * @param x x location of the center of the circle
     * @param y y location of the center of the circle
     * @param radius length of the radius of the circle
     * @param color the color of the circle
     */
    public Circle(double x, double y, double radius, Color color) {
        super(x, y, color);
        this.radius = radius;
    }

    /**
     * A method that draws the circle using the WinPlotter applet
     * @param plotter an instance of the WinPlotter object
     */
    @Override
    public void draw(WinPlotterFX plotter) {
        setPenColor(plotter);
        plotter.moveTo(x, y+radius);
        for(int i = 0; i <= 360; i++) {
            plotter.drawTo(sin(Math.toRadians(i))*radius+x, cos(Math.toRadians(i))*radius+y);
        }
    }
}
