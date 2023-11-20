package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class StoreOrderController {

    @FXML
    private ComboBox<String> orderComboBox;

    @FXML
    private TextField orderTotalTextField;

    @FXML
    private TextArea orderDetailsTextArea;

    public void initialize() {
        // Populate the ComboBox with sample data (replace with your actual data)
        orderComboBox.getItems().addAll("Order 1", "Order 2", "Order 3");

        // Set default values for demonstration purposes (replace with actual values)
        orderTotalTextField.setText("100.00");
        orderDetailsTextArea.setText("Order Details:\nPizza 1: Margherita\nPizza 2: Pepperoni\n");
    }

    @FXML
    private void cancelOrder() {
        // Handle cancel order action
        String selectedOrder = orderComboBox.getValue();
        System.out.println("Order canceled: " + selectedOrder);
    }

    @FXML
    private void exportStoreOrders() {
        // Handle export store orders action
        System.out.println("Store orders exported!");
    }
}
