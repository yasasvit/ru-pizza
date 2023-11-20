package com.example.rupizza;

import java.util.ArrayList;

public class BuildYourOwn extends Pizza {
    public BuildYourOwn(Size size, Sauce sauce, ArrayList<Topping> selectedToppings, boolean extraSauce, boolean extraCheese) {
        this.size = size;
        this.toppings = selectedToppings;
        this.sauce = sauce;
        this.extraSauce = extraSauce;
        this.extraCheese = extraCheese;
    }

    @Override
    public double price() {
        double basePrice = 15.99;
        switch (size) {
            case Size.medium:
                basePrice += 2;
                break;
            case Size.large:
                basePrice += 4;
                break;
        }
        int defaultToppingsCount = 3;
        int remainingToppings = toppings.size() - defaultToppingsCount;
        double toppingPrice = 1.49;
        basePrice += (remainingToppings * toppingPrice);
        if (extraCheese) {
            basePrice += 1;
        }
        if (extraSauce) {
            basePrice += 1;
        }
        return basePrice;
    }
}
