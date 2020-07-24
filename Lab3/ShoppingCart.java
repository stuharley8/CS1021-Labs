/*
 * Course: CS1021
 * Winter 2018
 * Lab: Lab 3 - Interfaces
 * Name: Stuart Harley
 * Created: 12/10/2018
 */

package harleys;

import java.util.ArrayList;
import java.util.List;

/**
 * ShoppingCart class represents a shopping cart holding a unspecified number of food items
 */
public class ShoppingCart {

    private List<Sellable> items = new ArrayList<>();

    /**
     * Adds a food item to the items List
     * @param item the Sellable item being added
     */
    public void add(Sellable item) {
        items.add(item);
    }

    /**
     * Calculates the cost of the items in the shopping cart
     * @return the cost of the items in the shopping cart
     */
    public double cost() {
        double cost = 0;
        for(Sellable item : items) {
            cost += item.price();
        }
        return cost;
    }

    /**
     * Calculates the tax on the items in the shopping cart
     * @return the tax of the items in the shopping cart
     */
    public double taxDue() {
        double tax = 0;
        for(Sellable item : items) {
            tax += item.tax();
        }
        return tax;
    }
}