package com.example.rupizza;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a pizza order containing a distinct order number and a ListView box of pizzas
 * @authors Yasasvi Tallapaneni, Pranav Gummaluri
 */
public class Order {
    private static int orderCounter = 1;
    private final int orderNumber;
    private List<Pizza> pizzas;

    /**
     * Contructor makes a new Order object with a unique order number and an empty list of pizzas
     */
    public Order() {
        this.orderNumber = orderCounter++;
        this.pizzas = new ArrayList<>();
    }

    /**
     * This method gets the unique order number of the order
     * @return an int that represents the order number
     */
    public int getOrderNumber() {
        return orderNumber;
    }

    /**
     * This method gets the list of pizzas from the order
     * @return an arraylist which represents the pizza list
     */
    public List<Pizza> getPizzas() {
        return pizzas;
    }

    /**
     * This method adds a pizza to the list order
     * @param Pizza object "pizza" to be added to the order
     */
    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    /**
     * This method calculates and returns the total price of the order (excluding tax)
     * @return double which represents the total price of the order
     */
    public double getTotalOrderPrice() {
        // NEED TO INCLUDE TAX LATER TO PRICE
        double totalPrice = 0.0;
        for (Pizza pizza : pizzas) {
            totalPrice += pizza.price();
        }
        return totalPrice;
    }
}
