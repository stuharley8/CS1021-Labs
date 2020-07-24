/*
 * Course: CS-1021-021
 * Winter 2018
 * Lab 5 - Game of Life
 * Name: Stuart Harley
 * Created: 1/9/19
 */

package harleys;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.stage.Stage;

import java.util.List;

/**
 * This class runs the Game of Life
 */
public class Lab5 extends Application {

    private LifeGrid lifeGrid;
    private Label displayAlive;
    private Label displayDead;
    private static final int HEIGHT = 550;
    private static final int WIDTH = 500;
    private static final int DIMENSION_OF_GAME = 500;

    /**
     * Displays the visual representation of the game of life
     * @param stage the stage to show the game
     */
    @Override
    public void start(Stage stage){
        stage.setTitle("The Game of Life");
        stage.setResizable(false);
        FlowPane pane = new FlowPane();
        lifeGrid = new LifeGrid(pane, DIMENSION_OF_GAME, DIMENSION_OF_GAME);
        Button randomizer = new Button("Randomize");
        Button stepper = new Button("Step");
        displayAlive = new Label();
        displayDead = new Label();
        updateLabels();
        pane.getChildren().addAll(randomizer, stepper, displayAlive, displayDead);
        randomizer.setOnAction(actionEvent -> {
            lifeGrid.randomize();
            updateLabels();
        });
        stepper.setOnAction(actionEvent -> {
            lifeGrid.iterate();
            updateLabels();
        });
        for(List<Cell> cellList : lifeGrid.getCells()) {
            for(Cell cell : cellList) {
                cell.setOnMouseClicked(this::toggleAlive);
            }
        }
        stage.setScene(new Scene(pane, WIDTH, HEIGHT));
        stage.show();
    }

    private void toggleAlive(MouseEvent mouseEvent) {
        Cell clickedCell = (Cell)mouseEvent.getSource();
        if(clickedCell.isAlive()) {
            clickedCell.setAlive(false);
        } else {
            clickedCell.setAlive(true);
        }
        clickedCell.updateColors();
        updateLabels();
    }

    private void updateLabels() {
        displayAlive.setText("Number Alive: " + lifeGrid.getNumAlive() + " ");
        displayDead.setText("Number Dead: " + lifeGrid.getNumDead());
    }

    public static void main(String[] args) {
        launch(args);
    }
}