package com.example.rupizza;

import java.util.ArrayList;

public abstract class Pizza {
    protected ArrayList<Topping> toppings;
    protected Size size;
    protected Sauce sauce;
    protected boolean extraSauce;
    protected boolean extraCheese;
    public abstract double price();

}
