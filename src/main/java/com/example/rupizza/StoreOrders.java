package com.example.rupizza;

import java.util.ArrayList;
import java.util.List;

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
    // NEED TO DO THIS FUNCTION
    public void export() {

    }
}
