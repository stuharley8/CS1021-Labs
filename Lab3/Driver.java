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
import java.util.Random;

public class Driver {
    private static Random rand = new Random();

    private static String[] fruitNames = {"apple", "Pear", "orange",
            "banana", "grape", "grapes", "peach", "plum", "mango",
            "strawberries", "blackberry", "kiwi"};
    private static String[] veggieNames = {"lettuce", "spinach",
            "mustard greens", "collard greens", "peas",
            "green beans", "snow peas", "asparagus", "broccoli",
            "celery", "sweet potato", "beet", "yam", "kelp",
            "kombu", "nori", "weird"};
    private static String[] fat = {"skim", "1%", "2%", "whole",
            "half and half", "cream"};
    private static SoftDrink.PackageType[] packageType = {
            SoftDrink.PackageType.SINGLE,
            SoftDrink.PackageType.SIX_PACK,
            SoftDrink.PackageType.TWELVE_PACK,
            SoftDrink.PackageType.TWENTYFOUR_PACK
    };

    public static void main(String[] args) {
        ShoppingCart cart = new ShoppingCart();
        for(String name : fruitNames) {
            cart.add(createFruit(name));
        }
        for(String name : veggieNames) {
            cart.add(createVeggie(name));
        }
        for(String fatContent : fat) {
            cart.add(createMilk(fatContent));
        }
        for(SoftDrink.PackageType type : packageType) {
            cart.add(createSoftDrink(type));
        }
        System.out.format("Cart total: $%6.2f%n", cart.cost());
        System.out.format(" Taxes due: $%6.2f%n", cart.taxDue());
        System.out.format(" Total due: $%6.2f%n", cart.cost()+cart.taxDue());
    }

    private static Fruit createFruit(String name) {
        return new Fruit(name, rand.nextDouble() * 2,
                LocalDate.now().minus(rand.nextInt(20), ChronoUnit.DAYS),
                rand.nextInt(10)+1);
    }

    private static Vegetable createVeggie(String name) {
        return new Vegetable(name, rand.nextDouble()*2,
                LocalDate.now().minus(rand.nextInt(7), ChronoUnit.DAYS));
    }

    private static Milk createMilk(String fatContent) {
        return new Milk(64*(rand.nextInt(2)+1),
                LocalDate.now().minus(rand.nextInt(7), ChronoUnit.DAYS),
                fatContent);
    }

    private static SoftDrink createSoftDrink(SoftDrink.PackageType type) {
        return new SoftDrink(8*(rand.nextInt(24)+1), "Coke",
                LocalDate.now().minus(rand.nextInt(30), ChronoUnit.DAYS),
                type);
    }
}