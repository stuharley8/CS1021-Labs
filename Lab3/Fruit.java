/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab 3 - Interfaces
 * Name: Stuart Harley
 * Created: 12/10/2018
 */

package harleys;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

/**
 * Fruit class represents a quantity of a type of fruit
 * @author Stuart Harley
 * @version 12/10/2018
 */
public class Fruit extends Produce implements Sellable {
    private static final double PRICE_PER_FLESHY = .8;
    private static final double PRICE_PER_STONE = .5;
    private static final double PRICE_PER_AGGREGATE = 2.3;
    private static final int MINIMUM_DAYS_TO_SELL = 20;
    private enum FruitType { UNKNOWN, FLESHY, STONE, AGGREGATE }
    private final FruitType fruitType;
    private int quantity;

    /**
     * Constructor
     * @param name Name of the fruit
     * @param weight Total weight of the fruit
     * @param harvestDate Date on which the fruit was harvested
     * @param quantity Number of fruit items
     */
    public Fruit(String name, double weight, LocalDate harvestDate, int quantity) {
        super(name, weight, harvestDate);
        fruitType = setType(name);
        this.quantity = quantity;
    }

    /**
     * Returns the last date on which this product can be sold
     * @return the last date on which this product can be sold
     */
    @Override
    public LocalDate getSellByDate() {
        int daysToSell = fruitType==FruitType.STONE
                ? (int)(1.5*MINIMUM_DAYS_TO_SELL)
                : MINIMUM_DAYS_TO_SELL;
        return getHarvestDate().plus(daysToSell, ChronoUnit.DAYS);
    }

    /**
     * Determines the type of fruit based on the name of the fruit
     * @param name the name of the fruit
     * @return the type of fruit based on the name of the fruit
     */
    private FruitType setType(String name) {
        FruitType type = FruitType.UNKNOWN;
        switch (name.toLowerCase()) {
            case "apple":
            case "pear":
            case "orange":
            case "banana":
            case "grape":
                type = FruitType.FLESHY;
                break;
            case "peach":
            case "plum":
            case "mango":
                type = FruitType.STONE;
                break;
            case "strawberries":
            case "blackberries":
            case "grapes":
                type = FruitType.AGGREGATE;
                break;
        }
        return type;
    }

    /**
     * String representation of the fruit
     * @return String representation of the fruit
     */
    @Override
    public String toString() {
        return "Quantity: " + quantity + " of " + super.toString();
    }

    /**
     * Calculates the price of a fruit item
     * @return the price of the fruit item
     */
    @Override
    public double price() {
        double price = 0;
        switch(fruitType) {
            case FLESHY:
                price = quantity*PRICE_PER_FLESHY;
                break;
            case STONE:
                price = quantity*PRICE_PER_STONE;
                break;
            case AGGREGATE:
                price = quantity*PRICE_PER_AGGREGATE;
                break;
            default:
                price = quantity*(Math.random()*1.9+.1);
        }
        return price;
    }

    /**
     * Calculates the tax of an fruit item
     * @return 0 because fruit has no tax
     */
    @Override
    public double tax() {
        return 0;
    }
}