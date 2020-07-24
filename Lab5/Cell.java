/*
 * CS1021 - 0-0
 * Winter 2018-2019
 * Lab Game Of Life
 * Name: Derek Riley (edits by Chris Taylor)
 * Created 11/25/2016
 */

package harleys;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * This Class represents a cell within John Conway's Game of Life.
 * The cell is arranged in a 2D grid and has 8 neighbors (other Cells).
 * It contains a Rectangle that is used to visually represent the cell in the JavaFX framework.
 */
public class Cell extends Rectangle {

    /**
     * This constant represents the scale (size) of the grid in number of pixels
     */
    public static final int SCALE = 10;

    /**
     * This constant represents the color the alive cells are painted
     */
    public static final Color ALIVE_COLOR = Color.GREEN;

    /**
     * This constant represents the color the dead cells are painted
     */
    public static final Color DEAD_COLOR = Color.BLACK;

    /**
     * This variable represents whether the cell is currently alive
     */
    private boolean alive;

    /**
     * This variable represents whether the cell will be alive in the next time tick
     */
    private boolean willBeAlive;

    /**
     * These variables represent the 8 neighbors of the cell
     */
    private Cell[][] neighbors = new Cell[3][3];
    private final static int ABOVE = 0;
    private final static int MIDDLE = 1;
    private final static int BELOW = 2;
    private final static int LEFT = 0;
    private final static int CENTER = 1;
    private final static int RIGHT = 2;

    /**
     * This method returns whether the cell is currently alive or not
     * @return true if the cell is currently alive or false if not
     */
    public boolean isAlive() {
        return alive;
    }

    /**
     * This method sets the cell to be currently alive or not
     * @param alive true if the cell is alive or false if not
     */
    public void setAlive(boolean alive) {
        this.alive = alive;
    }

    /**
     * This method sets the neighbor centered above the current cell
     * @param neighborAboveCenter northern neighbor
     */
    public void setNeighborAboveCenter(Cell neighborAboveCenter) {
        neighbors[ABOVE][CENTER] = neighborAboveCenter;
    }

    /**
     * This method sets the neighbor above and to the right of the current cell
     * @param neighborAboveRight northeastern neighbor
     */
    public void setNeighborAboveRight(Cell neighborAboveRight) {
        neighbors[ABOVE][RIGHT] = neighborAboveRight;
    }

    /**
     * This method sets the neighbor above and to the left of the current cell
     * @param neighborAboveLeft northwestern neighbor
     */
    public void setNeighborAboveLeft(Cell neighborAboveLeft) {
        neighbors[ABOVE][LEFT] = neighborAboveLeft;
    }

    /**
     * This method sets the neighbor at the same vertical level but to the right of the current cell
     * @param neighborMiddleRight eastern neighbor
     */
    public void setNeighborMiddleRight(Cell neighborMiddleRight) {
        neighbors[MIDDLE][RIGHT] = neighborMiddleRight;
    }

    /**
     * This method sets the neighbor at the same vertical level but to the left of the current cell
     * @param neighborMiddleLeft western neighbor
     */
    public void setNeighborMiddleLeft(Cell neighborMiddleLeft) {
        neighbors[MIDDLE][LEFT] = neighborMiddleLeft;
    }

    /**
     * This method sets the neighbor below and centered of the current cell
     * @param neighborBelowCenter southern neighbor
     */
    public void setNeighborBelowCenter(Cell neighborBelowCenter) {
        neighbors[BELOW][CENTER] = neighborBelowCenter;
    }

    /**
     * This method sets the neighbor below and to the right of the current cell
     * @param neighborBelowRight southeastern neighbor
     */
    public void setNeighborBelowRight(Cell neighborBelowRight) {
        neighbors[BELOW][RIGHT] = neighborBelowRight;
    }

    /**
     * This method sets the neighbor below and to the left of the current cell
     * @param neighborBelowLeft southwestern neighbor
     */
    public void setNeighborBelowLeft(Cell neighborBelowLeft) {
        neighbors[BELOW][LEFT] = neighborBelowLeft;
    }

    /**
     * This constructor creates the cell at the given position within the grid
     * @param xPosition the x-Position within the grid
     * @param yPosition the y-Position within the grid
     */
    public Cell(double xPosition, double yPosition) {
        this.setLayoutX(xPosition*SCALE);
        this.setLayoutY(yPosition*SCALE);
        this.setWidth(SCALE);
        this.setHeight(SCALE);
        this.alive = false;
        this.willBeAlive = false;
        updateColors();
    }

    /**
     * This method calculates whether the cell will be alive during the next tick.
     */
    public void determineNextTick(){
        int neighborsAlive = 0;
        for(Cell[] row : neighbors) {
            for(Cell neighbor : row) {
                if(neighbor!=null && neighbor.isAlive()) {
                    ++neighborsAlive;
                }
            }
        }
        runLifeRules(neighborsAlive);
    }

    /**
     * This method updates the cell's life status based on the predicted life status in willBeAlive.
     * The cell's Rectangle color is also updated.
     */
    public void updateTick(){
        alive = willBeAlive;
        updateColors();
    }

    /**
     * This method updates the color of the cell based on the ALIVE_COLOR or DEAD_COLOR
     */
    public void updateColors(){
        if(alive){
            this.setFill(ALIVE_COLOR);
        } else{
            this.setFill(DEAD_COLOR);
        }
    }

    /**
     * This method defines the life rules based on the number of neighbors alive
     * @param neighborsAlive this is the number of neighbors that are alive for the current cell
     */
    private void runLifeRules(int neighborsAlive){
        if(neighborsAlive==3) {
            willBeAlive = true;
        } else if(neighborsAlive<2 || neighborsAlive>3){
            willBeAlive = false;
        } else{
            willBeAlive = alive;
        }
    }
}