package com.example.rupizza;

import java.util.ArrayList;

/**
* This class represents the Supreme specialty pizza and calculates the price with addons
* @authors Yasasvi Tallapaneni, Pranav Gummaluri
*/
public class Supreme extends Pizza{
    public Supreme(Size size, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = new ArrayList<>();
        this.toppings.add(Topping.Sausage);
        this.toppings.add(Topping.Pepperoni);
        this.toppings.add(Topping.Ham);
        this.toppings.add(Topping.GreenPepper);
        this.toppings.add(Topping.Onion);
        this.toppings.add(Topping.BlackOlives);
        this.toppings.add(Topping.Mushroom);
        this.sauce = Sauce.tomato;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    /**
    * This method calculates the price of the Supreme with any addons 
    * @return a double representing price with any addons
    */
    @Override
    public double price() {
        double basePrice = 15.99;
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
