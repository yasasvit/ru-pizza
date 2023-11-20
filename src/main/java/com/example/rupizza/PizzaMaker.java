package com.example.rupizza;

import java.util.ArrayList;
public class PizzaMaker {
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
