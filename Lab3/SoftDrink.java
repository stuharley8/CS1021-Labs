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
 * Soft Drink class represents a volume of soda
 * @author Stuart Harley
 * @version 12/10/2018
 */
public class SoftDrink extends Beverage implements Sellable {

    /**
     * Package types for soft drinks
     */
    public enum PackageType { SINGLE, SIX_PACK, TWELVE_PACK, TWENTYFOUR_PACK }

    private final PackageType packaging;
    private static final double PRICE_PER_FLUID_OUNCE = .08;

    /**
     * Constructor
     * @param volume Total number of fluid ounces
     * @param brand The brand name of the beverage
     * @param sellByDate Last date on which this product can be sold
     * @param packaging Type of case of drinks
     */
    public SoftDrink(double volume, String brand, LocalDate sellByDate,
                     PackageType packaging) {
        super(volume, brand, sellByDate);
        this.packaging = packaging;
    }

    /**
     * Returns the number of containers that are part of this soft drink object
     * @return the number of containers that are part of this soft drink object
     */
    @Override
    public int getNumberOfContainers() {
        int quantity = 1;
        switch (packaging) {
            case SIX_PACK:
                quantity = 6;
                break;
            case TWELVE_PACK:
                quantity = 12;
                break;
            case TWENTYFOUR_PACK:
                quantity = 24;
                break;
        }
        return quantity;
    }

    /**
     * Calculates the price of a soft drink item
     * @return the price of the soft drink item, rounded to the nearest 10 cents
     */
    @Override
    public double price() {
        double price = getVolumeInFlOz()*PRICE_PER_FLUID_OUNCE;
        price *= (12*getNumberOfContainers()/getVolumeInFlOz());
        price *= Math.pow(.99, getNumberOfContainers());
        price = (int)((price+.05)*10);
        price /= 10.0;
        return price;
    }

    /**
     * Calculates the tax of a soft drink item
     * @return the tax of the soft drink item
     */
    @Override
    public double tax() {
        double tax = price()*MKE_COUNTY_TAX_RATE;
        tax += price()*WI_STATE_TAX_RATE;
        return tax;
    }

}