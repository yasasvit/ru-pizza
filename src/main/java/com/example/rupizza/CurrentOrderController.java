package com.example.rupizza;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CurrentOrderController {
    private PizzaOrderService pizzaOrderService = PizzaOrderService.getInstance();

    @FXML
    private TextField orderNumberTextField;

    @FXML
    private TextArea ordersTextArea;

    @FXML
    private TextField currentTotalPriceTextField;

    @FXML
    private TextField salesTaxTextField;

    @FXML
    private TextField totalPriceWithTaxTextField;

    @FXML
    private Button placeOrderButton;
    @FXML
    private Button removePizzaButton;

    @FXML
    public void initialize() {
        updateOrderDetails();

        placeOrderButton.setOnAction(event -> placeOrder());
    }

    private void updateOrderDetails() {
        Order currentOrder = pizzaOrderService.getCurrentOrder();

        orderNumberTextField.setText(String.valueOf(currentOrder.getOrderNumber()));

        StringBuilder ordersText = new StringBuilder();
        for (Pizza pizza : currentOrder.getPizzas()) {
            ordersText.append(pizza.getClass().getSimpleName()).append("\n");
            // You might want to display more details about each pizza here
        }
        ordersTextArea.setText(ordersText.toString());

        double currentTotalPrice = currentOrder.getTotalOrderPrice();
        currentTotalPriceTextField.setText(String.format("%.2f", currentTotalPrice));
        double njTax = 0.06625;
        double salesTax = njTax * currentTotalPrice;
        salesTaxTextField.setText(String.format("%.2f", salesTax));

        double totalPriceWithTax = currentTotalPrice + salesTax;
        totalPriceWithTaxTextField.setText(String.format("%.2f", totalPriceWithTax));
    }

    private void placeOrder() {
        // Implement the logic for placing the order (if needed)
        // You might want to clear the current order or do other actions
    }
}
