package com.example.demo;

// CurrentOrderController.java
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

public class CurrentOrderController {

    @FXML
    private TextField orderNumberTextField;

    @FXML
    private TextArea orderInfoTextArea;

    @FXML
    private TextField subtotalTextField;

    @FXML
    private TextField salesTaxTextField;

    @FXML
    private TextField orderTotalTextField;

    public void initialize() {
        // Initialize the order number, order information, subtotal, sales tax, and order total
        int orderNumber = 123; // Replace with your actual order number
        String orderInfo = "Pizza 1: Margherita\nPizza 2: Pepperoni\n"; // Replace with your actual order information
        double subtotal = 20.00; // Replace with your actual subtotal
        double salesTax = 2.00; // Replace with your actual sales tax
        double orderTotal = 22.00; // Replace with your actual order total

        // Set values to the UI components
        orderNumberTextField.setText(String.valueOf(orderNumber));
        orderInfoTextArea.setText(orderInfo);
        subtotalTextField.setText(String.format("%.2f", subtotal));
        salesTaxTextField.setText(String.format("%.2f", salesTax));
        orderTotalTextField.setText(String.format("%.2f", orderTotal));
    }

    @FXML
    private void removePizza() {
        // Handle remove pizza action
        String selectedText = orderInfoTextArea.getSelectedText();
        String updatedOrderInfo = orderInfoTextArea.getText().replace(selectedText, "");
        orderInfoTextArea.setText(updatedOrderInfo);
    }

    @FXML
    private void placeOrder() {
        // Handle place order action
        System.out.println("Order placed!");
    }
}

