/*
 * Course: CS1021-021
 * Winter 2018
 * Lab 9: Final Project Continued
 * Name: Stuart Harley
 * Created: 2/7/2019
 */

package harleys;

import javafx.scene.paint.Color;

/**
 *Functional Interface dealing with transforming images
 */
@FunctionalInterface
public interface Transformable {

    /**
     * Apply method returns the color for a pixel after applying a transformation
     * @param y the y location of the pixel
     * @param color the color of the pixel
     * @return the color of the pixel after applying the transformation
     */
    Color apply(int y, Color color);
}