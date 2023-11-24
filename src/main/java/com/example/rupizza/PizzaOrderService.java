package com.example.rupizza;

import java.util.ArrayList;
import java.util.List;

public class PizzaOrderService {
    private static PizzaOrderService instance;
    private Order currentOrder;

    private PizzaOrderService() {
        currentOrder = new Order();
    }

    public static PizzaOrderService getInstance() {
        if (instance == null) {
            instance = new PizzaOrderService();
        }
        return instance;
    }

    public Order getCurrentOrder() {
        return currentOrder;
    }

    public void addPizzaToOrder(Pizza pizza) {
        currentOrder.addPizza(pizza);
    }
}
