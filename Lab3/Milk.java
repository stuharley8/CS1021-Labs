/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab 3 - Interfaces
 * Name: Stuart Harley
 * Created: 12/10/2018
 */

package harleys;

import java.time.LocalDate;

/**
 * Milk class represents a gallon of milk
 * @author Stuart Harley
 * @version 12/10/2018
 */
public class Milk extends Beverage implements Sellable {

    private static final double PRICE_OF_SKIM = 2.5;
    private enum FatContent { SKIM, ONE_PERCENT, TWO_PERCENT, WHOLE, HALF_AND_HALF, CREAM }
    private final FatContent fatContent;

    /**
     * Constructor
     * @param volume Total number of fluid ounces
     * @param sellByDate Last date on which this product can be sold
     * @param fatContent A string representing the amount of fat content
     */
    public Milk(double volume, LocalDate sellByDate, String fatContent) {
        super(volume, "milk", sellByDate);
        switch (fatContent.toLowerCase()) {
            case "cream":
                this.fatContent = FatContent.CREAM;
                break;
            case "half and half":
                this.fatContent = FatContent.HALF_AND_HALF;
                break;
            case "2%":
                this.fatContent = FatContent.TWO_PERCENT;
                break;
            case "1%":
                this.fatContent = FatContent.ONE_PERCENT;
                break;
            default:
                this.fatContent = FatContent.SKIM;
        }
    }

    /**
     * String representation of the fruit
     * @return String representation of the fruit
     */
    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * Returns a description of the brand name including the fat content
     * @return a description of the brand name including the fat content
     */
    @Override
    public String getBrand() {
        String descriptor = super.getBrand();
        switch (fatContent) {
            case CREAM:
                descriptor = "cream";
                break;
            case HALF_AND_HALF:
                descriptor = "half and half";
                break;
            case WHOLE:
                descriptor = "whole " + descriptor;
                break;
            case TWO_PERCENT:
                descriptor = "2% " + descriptor;
                break;
            case ONE_PERCENT:
                descriptor = "1% " + descriptor;
                break;
            default:
                descriptor = "fat free " + descriptor;
        }
        return descriptor;
    }

    /**
     * Calculates the price of a gallon of milk
     * @return the price of the gallon of milk
     */
    @Override
    public double price() {
        double price = 0;
        switch(fatContent) {
            case CREAM:
                price = PRICE_OF_SKIM*2;
                break;
            case HALF_AND_HALF:
                price = PRICE_OF_SKIM*3;
                break;
            default:
                price = PRICE_OF_SKIM;
        }
        return price;
    }

    /**
     * Calculates the tax of a gallon of milk
     * @return 0 because milk has no tax
     */
    @Override
    public double tax() {
        return 0;
    }
}