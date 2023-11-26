package com.example.rupizza;

import java.util.ArrayList;

/**
* This class represents the Seafood specialty pizza and its price
* @authors Yasasvi Tallapaneni, Pranav Gummaluri
*/
public class Seafood extends Pizza{
    public Seafood(Size size, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toppings.add(Topping.Shrimp);
        this.toppings.add(Topping.Squid);
        this.toppings.add(Topping.CrabMeats);
        this.sauce = Sauce.alfredo;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
    * This method calculates the price of the pizza with any addon options
    * @return a double representing the price of the optioned pizza
    */
    @Override
    public double price() {
        double basePrice = 17.99;
        switch (size) {
            case medium:
                basePrice += 2;
                break;
            case large:
                basePrice += 4;
                break;
        }
        if (extraCheese) {
            basePrice += 1;
        }
        if (extraSauce) {
            basePrice += 1;
        }
        return basePrice;
    }
}
