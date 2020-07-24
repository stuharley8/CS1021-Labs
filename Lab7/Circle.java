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
     * @throws IllegalArgumentException if the radius is negative or zero
     */
    public Circle(double x, double y, double radius, Color color) throws IllegalArgumentException {
        super(x, y, color);
        if(radius>0) {
            this.radius = radius;
        } else {
            throw new IllegalArgumentException("Can't create a circle with a negative radius");
        }
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
