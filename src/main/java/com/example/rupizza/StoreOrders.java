package com.example.rupizza;

import java.util.ArrayList;
import java.util.List;
import java.io.IOException;
import java.io.BufferedWriter;
import java.io.FileWriter;

public class StoreOrders {
    private static final StoreOrders instance = new StoreOrders();
    private List<Order> orders;
    public StoreOrders() {
        this.orders = new ArrayList<>();
    }

    public List<Order> getOrders() {
        return orders;
    }
    public static StoreOrders getInstance() {
        return instance;
    }

    public void addOrder(Order order) {
        orders.add(order);
    }
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
    private String getPizzaDetails(Pizza pizza) {
        return String.format("[%s] %s %s %s %s %s",
                pizza.getClass().getSimpleName(),
                getPizzaToppings(pizza),
                pizza.size,
                pizza.extraSauce ? "extra sauce" : "",
                pizza.extraCheese ? "extra cheese" : "",
                String.format("$%.2f", pizza.price()));
    }

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
