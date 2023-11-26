package com.example.rupizza;

import java.util.ArrayList;

/**
* This class represents the Pepperoni specialty pizza and its base price
* @authors Yasasvi Tallapaneni, Pranav Gummaluri
*/
public class Pepperoni extends Pizza{
    public Pepperoni(Size size, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toppings.add(Topping.Pepperoni);
        this.sauce = Sauce.tomato;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
    * This method represents the Pepperoni's base price and adjusts the price for each addon
    * @return a doule which represents the price of the optioned pizza
    */
    @Override
    public double price() {
        double basePrice = 10.99;
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
