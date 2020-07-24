/*
 * Course: CS1021 - 0x0
 * Winter 2018-2019
 * Lab 3 - A Christmas Wish...
 * Name: Various
 * Created: 2/2/2018
 */

package harleys;

import edu.msoe.winplotterfx.WinPlotterFX;
import javafx.application.Application;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 * This class creates shapes and "tells" them to draw themselves in a
 * provided WinPlotterFX window.
 * @author hornick, taylor, and jones
 * @version 20180228.1
 */
public class ShapeCreatorApp extends Application {
    /**
     * Launches the JavaFX application
     * @param args ignored
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Use the Shape class and its descendants to draw a happy Christmas scene.
     * @param stage Default stage given to a JavaFX program. Unused.
     */
    @Override
    public void start(Stage stage) {
        WinPlotterFX plotter = new WinPlotterFX();

        // Initialize the WinPlotterFX object
        initWinPlotter(plotter);

        // Draw the happy scene
        drawTree(plotter);
        drawPresents(plotter);

        // Show the happy scene
        plotter.showPlotter();
    }

    /**
     * This method initializes the WinPlotterFX with the desired title, size, background color and
     * grid options.
     * @param plotter The object where the scene will be drawn
     */
    private void initWinPlotter(WinPlotterFX plotter) {
        plotter.setWindowTitle("A Christmas Wish...");
        plotter.setWindowSize(800, 600);
        plotter.setBackgroundColor(Color.BLACK.getRed(), Color.BLACK.getGreen(),
                Color.BLACK.getBlue());
        plotter.setGrid(true, 80, 80, Color.GRAY);
    }

    /**
     * This method draws the Christmas tree, using the Rectangle and
     * Triangle classes
     * @param plotter The object where the tree will be drawn
     */
    private static void drawTree(WinPlotterFX plotter) {
        Shape treeTrunk = new Rectangle(520, 40, 80, 160, Color.ORANGE);
        treeTrunk.draw(plotter);

        // Create some triangles to form the tree branches
        Shape treeBranch1 = new Triangle(360, 120, 400, 200, Color.GREEN);
        treeBranch1.draw(plotter);
        Shape treeBranch2 = new Triangle(400, 200, 320, 160, Color.GREEN);
        treeBranch2.draw(plotter);
        Shape treeBranch3 = new Triangle(440, 280, 240, 120, Color.GREEN);
        treeBranch3.draw(plotter);
        Shape treeBranch4 = new Triangle(480, 360, 160, 80, Color.GREEN);
        treeBranch4.draw(plotter);

        // Draw ornaments and top decoration on the tree
        drawOrnaments(plotter);
        drawHeadPiece(plotter);
    }

    /**
     * This method draws ornaments on the tree, using the Point class
     * @param plotter The object where the tree will be drawn
     */
    private static void drawOrnaments(WinPlotterFX plotter) {
        // Creates a generic shape, then assign it various Point objects and draws them
        Shape ornament = new Point(440, 155, Color.ORANGE);
        ornament.draw(plotter);
        ornament = new Point(660, 180, Color.RED);
        ornament.draw(plotter);
        ornament = new Point(635, 220, Color.BLUE);
        ornament.draw(plotter);
        ornament = new Point(570, 270, Color.YELLOW);
        ornament.draw(plotter);
        ornament = new Point(515, 315, Color.MAGENTA);
        ornament.draw(plotter);
        ornament = new Point(535, 375, Color.CYAN);
        ornament.draw(plotter);
    }

    /**
     * This method draws the head piece for the tree using the Circle class
     * @param plotter The object where the tree will be drawn
     */
    private static void drawHeadPiece(WinPlotterFX plotter) {
        new Circle(560, 480, 40, Color.RED).draw(plotter);
        new Circle(560, 480, 32, Color.ORANGE).draw(plotter);
        new Circle(560, 480, 24, Color.YELLOW).draw(plotter);
        new Circle(560, 480, 16, Color.WHITE).draw(plotter);
    }

    /**
     * This method draws some Christmas presents by the tree
     * @param plotter The object where the tree will be drawn
     */
    private static void drawPresents(WinPlotterFX plotter) {
        Shape present1 = new LabeledRectangle(40, 40, 300, 160, Color.BLUE, "To: Sean");
        Shape present2 = new LabeledRectangle(60, 200, 140, 140, Color.CYAN, "To: Sean");
        Shape present3 = new LabeledTriangle(210, 200, 120, 80, Color.YELLOW, "To: Sean");
        present3.draw(plotter);
        present2.draw(plotter);
        present1.setColor(Color.RED);
        present1.draw(plotter);
    }
}