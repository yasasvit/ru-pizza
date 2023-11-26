package com.example.rupizza;

import java.util.ArrayList;
import java.util.List;

/**
 * This class manages the current pizza order
 * @authors Pranav Gummaluri, Yasasvi Tallapaneni
 */
public class PizzaOrderService {
    private static PizzaOrderService instance;
    private Order currentOrder;

    /**
     * A private constructor to create an instance of PizzaOrderService and initialize the current order
     */
    private PizzaOrderService() {
        currentOrder = new Order();
    }

    /**
     * This method gets the instance of PizzaOrderService. If it does not exist, creates a new instance
     * @return the current instance of PizzaOrderService.
     */
    public static PizzaOrderService getInstance() {
        if (instance == null) {
            instance = new PizzaOrderService();
        }
        return instance;
    }

    /**
     * This method gets the current pizza order
     * @return Order object "order" representing the pizza order
     */
    public Order getCurrentOrder() {
        return currentOrder;
    }

    /**
     * This method clears the current pizza order, and make a new empty one
     */
    public void clearCurrentOrder() {
        currentOrder = new Order();
    }

    /**
     * This method adds a pizza to the current order
     * @param Pizza object "pizza" to be added to the order
     */
    public void addPizzaToOrder(Pizza pizza) {
        currentOrder.addPizza(pizza);
    }
}
