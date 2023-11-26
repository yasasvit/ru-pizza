package com.example.rupizza;

import java.util.ArrayList;

/**
* This class is an abstract class that represents a pizza and it's properties
* @authors Yasasvi Tallapaneni, Pranav Gummaluri
*/
public abstract class Pizza {
    protected ArrayList<Topping> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;

    /**
    * This method is an abstract method to calculate the price of a pizza
    * @return a double representing the price of the pizza
    */
    public abstract double price();

}
