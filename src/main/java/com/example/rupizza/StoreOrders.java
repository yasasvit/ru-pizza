package com.example.rupizza;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

/**
 * This class is responsible for managing store orders
 * @authors Pranav Gummaluri, Yasasvi Tallapaneni
 */
public class StoreOrders {
    private static final StoreOrders instance = new StoreOrders();
    private List<Order> orders;
    
    /**
     * Private constructor to enforce the singleton pattern
     */
    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    /**
     * This method returns the instance of the class
     * @return the ArrayList instance
     */
    public List<Order> getOrders() {
        return orders;
    }

    /**
     * This method adds an order to the list of store orders
     * @return a StoreOrders object representing the instance of the StoreOrders class
     */
    public static StoreOrders getInstance() {
        return instance;
    }

    /**
     * Adds an order to the list of store orders
     * @param Order object representing the pizza orders
     */
    public void addOrder(Order order) {
        orders.add(order);
    }

    /**
    * This method exports store orders to a text file
    * @param String representing the file to export store orders to
    */
    public void export(String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (Order order : orders) {
                writer.write("Order Number: " + order.getOrderNumber());
                writer.newLine();

                for (Pizza pizza : order.getPizzas()) {
                    writer.write(getPizzaDetails(pizza));
                    writer.newLine();
                }

                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This method returns formatted details of a pizza for exporting
     * @param Pizza object "pizza" representing the pizza selected
     * @return String representing the details of the pizza
     */
    private String getPizzaDetails(Pizza pizza) {
        return String.format("[%s] %s %s %s %s %s",
                pizza.getClass().getSimpleName(),
                getPizzaToppings(pizza),
                pizza.size,
                pizza.extraSauce ? "extra sauce" : "",
                pizza.extraCheese ? "extra cheese" : "",
                String.format("$%.2f", pizza.price()));
    }

    /**
     * This method returns formatted toppings of a pizza for exporting
     * @param Pizza object "pizza" representing the pizza selected
     * @return Formatted toppings of the pizza.
     */
    private String getPizzaToppings(Pizza pizza) {
        StringBuilder toppingsString = new StringBuilder();

        for (Topping topping : pizza.toppings) {
            toppingsString.append(topping.name()).append(", ");
        }

        if (toppingsString.length() > 2) {
            toppingsString.setLength(toppingsString.length() - 2);
        }

        return toppingsString.toString();
    }
}
