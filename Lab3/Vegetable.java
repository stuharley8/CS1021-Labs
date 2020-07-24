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
 * Vegetable class represents a vegetable with a weight
 * @author Stuart Harley
 * @version 12/10/2018
 */
public class Vegetable extends Produce implements Sellable {
    private static final double PRICE_PER_KG_SEA = 20;
    private static final double PRICE_PER_KG_PODDED = 2;
    private static final double PRICE_PER_KG_STEM = 1.5;
    private static final double PRICE_PER_KG_ROOT = .3;
    private static final double PRICE_PER_KG_DEFAULT = 1;
    private static final int MINIMUM_DAYS_TO_SELL = 5;
    private enum VeggieType { UNKNOWN, LEAFY_VEGETABLE, PODDED_VEGETABLE,
        STEM_VEGETABLE, ROOT_VEGETABLE, SEA_VEGETABLE }
    private VeggieType veggieType;

    /**
     * Constructor
     * @param name Name of the vegetable
     * @param weight Total weight of the vegetable
     * @param harvestDate Date on which the vegetable was harvested
     */
    public Vegetable(String name, double weight, LocalDate harvestDate) {
        super(name, weight, harvestDate);
        veggieType = setType(name);
    }

    /**
     * Returns the last date on which this product can be sold
     * @return the last date on which this product can be sold
     */
    @Override
    public LocalDate getSellByDate() {
        double sellByFactor = 1.0;
        switch (veggieType) {
            case SEA_VEGETABLE:
                sellByFactor = 1.2;
                break;
            case PODDED_VEGETABLE:
            case STEM_VEGETABLE:
                sellByFactor = 1.5;
                break;
            case ROOT_VEGETABLE:
                sellByFactor = 4.0;
                break;
        }
        return getHarvestDate().plus((long)(MINIMUM_DAYS_TO_SELL*sellByFactor),
                ChronoUnit.DAYS);
    }

    /**
     * Determines the type of vegetable based on the name of the vegetable
     * @param name the name of the vegetable
     * @return the type of vegetable based on the name of the vegetable
     */
    private VeggieType setType(String name) {
        VeggieType type = VeggieType.UNKNOWN;
        switch (name.toLowerCase()) {
            case "lettuce":
            case "spinach":
            case "mustard greens":
            case "collard greens":
                type = VeggieType.LEAFY_VEGETABLE;
                break;
            case "peas":
            case "green beans":
            case "snow peas":
                type = VeggieType.PODDED_VEGETABLE;
                break;
            case "asparagus":
            case "broccoli":
            case "celery":
                type = VeggieType.STEM_VEGETABLE;
                break;
            case "sweet potato":
            case "beet":
            case "yam":
                type = VeggieType.ROOT_VEGETABLE;
                break;
            case "kelp":
            case "kombu":
            case "nori":
                type = VeggieType.SEA_VEGETABLE;
                break;
        }
        return type;
    }

    /**
     * Calculates the price of a vegetable item
     * @return the price of the vegetable item
     */
    @Override
    public double price() {
        double price = 0;
        switch(veggieType) {
            case SEA_VEGETABLE:
                price = getWeightInKg()*PRICE_PER_KG_SEA;
                break;
            case PODDED_VEGETABLE:
                price = getWeightInKg()*PRICE_PER_KG_PODDED;
                break;
            case STEM_VEGETABLE:
                price = getWeightInKg()*PRICE_PER_KG_STEM;
                break;
            case ROOT_VEGETABLE:
                price = getWeightInKg()*PRICE_PER_KG_ROOT;
                break;
            default:
                price = getWeightInKg()*PRICE_PER_KG_DEFAULT;
        }
        return price;
    }

    /**
     * Calculates the tax of a vegetable item
     * @return 0 because there is no tax on vegetables
     */
    @Override
    public double tax() {
        return 0;
    }
}
