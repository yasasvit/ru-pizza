package com.example.rupizza;

import java.util.ArrayList;

/**
 * This class is a factory class for creating instances of different pizza types
 * @authors Yasasvi Tallapaneni, Pranav Gummaluri
 */
public class PizzaMaker {

    /**
     * Creates and returns an instance of a specific pizza type based on the provided parameters
     * @param pizzaType represents the type of pizza to create (e.g., "deluxe", "supreme", "meatzza")
     * @param size represents the size of the pizza
     * @param sauce which represents the type of sauce on the pizza
     * @param toppings which represents the list of toppings on the pizza
     * @param extraSauce represents whether extra sauce is requested
     * @param extraCheese represents whether extra cheese is requested
     * @return a Pizza object "pizza" which represents a specified pizza type
     */
    public static Pizza createPizza(String pizzaType, Size size, Sauce sauce, ArrayList<Topping> toppings, boolean extraSauce, boolean extraCheese) {
        switch (pizzaType.toLowerCase()) {
            case "deluxe":
                return new Deluxe(size, extraSauce, extraCheese);
            case "supreme":
                return new Supreme(size, extraSauce, extraCheese);
            case "meatzza":
                return new Meatzza(size, extraSauce, extraCheese);
            case "seafood":
                return new Seafood(size, extraSauce, extraCheese);
            case "pepperoni":
                return new Pepperoni(size, extraSauce, extraCheese);
            case "buildyourown":
                return new BuildYourOwn(size, sauce, toppings, extraSauce, extraCheese);
            default:
                throw new IllegalArgumentException("Invalid pizza type: " + pizzaType);
        }
    }
}
