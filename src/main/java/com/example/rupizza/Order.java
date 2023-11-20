package com.example.rupizza;

import java.util.ArrayList;
import java.util.List;

public class Order {
    private static int orderCounter = 1;
    private final int orderNumber;
    private List<Pizza> pizzas;

    public Order() {
        this.orderNumber = orderCounter++;
        this.pizzas = new ArrayList<>();
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public List<Pizza> getPizzas() {
        return pizzas;
    }

    public void addPizza(Pizza pizza) {
        pizzas.add(pizza);
    }

    public double getTotalOrderPrice() {
        // NEED TO INCLUDE TAX LATER TO PRICE
        double totalPrice = 0.0;
        for (Pizza pizza : pizzas) {
            totalPrice += pizza.price();
        }
        return totalPrice;
    }
}
