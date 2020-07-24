/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab 3 - Interfaces
 * Name: Stuart Harley
 * Created: 12/10/2018
 */

package harleys;

import java.text.DecimalFormat;
import java.time.LocalDate;

/**
 * Produce class represents a type of produce with some weight
 * @author Stuart Harley
 * @version 12/10/2018
 */
public abstract class Produce implements Sellable {
    private final String name;
    private double weightInKg;
    private final LocalDate harvestDate;
    private static final DecimalFormat FORMATTER = new DecimalFormat("###,###.#");

    public LocalDate getHarvestDate() {
        return harvestDate;
    }

    public double getWeightInKg() {
        return weightInKg;
    }

    /**
     * Constructor
     * @param name Name of the produce
     * @param weight Total weight of the produce item
     * @param harvestDate Date on which the produce was harvested
     */
    public Produce(String name, double weight, LocalDate harvestDate) {
        this.name = name;
        weightInKg = weight;
        this.harvestDate = harvestDate;
    }

    /**
     * String representation of the produce
     * @return String representation of the produce
     */
    @Override
    public String toString() {

        return name + " weighing " + FORMATTER.format(weightInKg)
                + "Kg, harvested on: " + harvestDate;
    }

    /**
     * Returns the last date on which this product can be sold
     * @return the last date on which this product can be sold
     */
    public abstract LocalDate getSellByDate();
}