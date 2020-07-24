/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab 3 - Interfaces
 * Name: Stuart Harley
 * Created: 12/10/2018
 */

package harleys;

/**
 * Sellable interface holds tax values and price and tax methods
 */
public interface Sellable {

    /**
     * Milwaukee county tax rate of .5%
     */
    double MKE_COUNTY_TAX_RATE = .005;

    /**
     * Wisconsin state tax rate of 5%
     */
    double WI_STATE_TAX_RATE = .05;

    /**
     * Calculates the price of an item
     * @return the price of the item
     */
    double price();

    /**
     * Calculates the tax of an item
     * @return the tax of the item
     */
    double tax();
}