package com.example.rupizza;

import java.util.ArrayList;

/**
* This class represents the Meatzza specialty pizza and its base price
* @authors Pranav Gummaluri, Yasasvi Tallapaneni
*/
public class Meatzza extends Pizza {
    public Meatzza(Size size, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toppings.add(Topping.Sausage);
        this.toppings.add(Topping.Pepperoni);
        this.toppings.add(Topping.Ham);
        this.toppings.add(Topping.Beef);
        this.sauce = Sauce.tomato;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
    * This method defines the base price of the Meatzza and increases the price accordingly to each addon
    */
    @Override
    public double price() {
        double basePrice = 16.99;
        switch (size) {
            case Size.medium:
                basePrice += 2;
                break;
            case Size.large:
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
