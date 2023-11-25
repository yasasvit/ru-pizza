package com.example.rupizza;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class CurrentOrderController {
    private StoreOrders storeOrders = StoreOrders.getInstance();

    private PizzaOrderService pizzaOrderService = PizzaOrderService.getInstance();
    @FXML
    private ListView<String> orderDetailsListView;

    @FXML
    private TextField orderNumberTextField;

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
    private void showAlert(String text) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        updateOrderDetails();

        placeOrderButton.setOnAction(event -> placeOrder());
    }

    private void updateOrderDetails() {
        Order currentOrder = pizzaOrderService.getCurrentOrder();

        orderNumberTextField.setText(String.valueOf(currentOrder.getOrderNumber()));

        ObservableList<String> orderDetailsList = FXCollections.observableArrayList();

        for (Pizza pizza : currentOrder.getPizzas()) {
            StringBuilder pizzaDetails = new StringBuilder();
            pizzaDetails.append("[")
                    .append(pizza.getClass().getSimpleName())
                    .append("] ")
                    .append(getToppingsString(pizza))
                    .append(" ")
                    .append(pizza.size)
                    .append(" ")
                    .append(pizza.extraSauce ? "extra sauce " : "")
                    .append(pizza.extraCheese ? "extra cheese " : "")
                    .append("$")
                    .append(String.format("%.2f", pizza.price()));

            orderDetailsList.add(pizzaDetails.toString());
        }
        orderDetailsListView.setItems(orderDetailsList);

        double currentTotalPrice = currentOrder.getTotalOrderPrice();
        currentTotalPriceTextField.setText(String.format("%.2f", currentTotalPrice));

        double njTax = 0.06625;
        double salesTax = njTax * currentTotalPrice;
        salesTaxTextField.setText(String.format("%.2f", salesTax));

        double totalPriceWithTax = currentTotalPrice + salesTax;
        totalPriceWithTaxTextField.setText(String.format("%.2f", totalPriceWithTax));

    }

    private String getToppingsString(Pizza pizza) {
        StringBuilder toppingsString = new StringBuilder();

        for (Topping topping : pizza.toppings) {
            toppingsString.append(topping.name()).append(", ");
        }

        if (!toppingsString.isEmpty()) {
            toppingsString.setLength(toppingsString.length() - 2);
        }

        return toppingsString.toString();
    }
    @FXML
    private void removePizza() {
        int selectedIndex = orderDetailsListView.getSelectionModel().getSelectedIndex();
        if (selectedIndex >= 0) {
            pizzaOrderService.getCurrentOrder().getPizzas().remove(selectedIndex);
            updateOrderDetails();
        } else{
            showAlert("Pizza needs to be selected in order to be removed.");
        }
    }
    @FXML
    private void placeOrder() {
        Order currentOrder = pizzaOrderService.getCurrentOrder();

        if (currentOrder.getPizzas().isEmpty()) {
            showAlert("No pizzas in the order.");
            return;
        }

        storeOrders.addOrder(currentOrder);

        pizzaOrderService.clearCurrentOrder();
        orderNumberTextField.setText(String.valueOf(currentOrder.getOrderNumber()));

        updateOrderDetails();

        // Reset text fields
        currentTotalPriceTextField.setText("0.00");
        salesTaxTextField.setText("0.00");
        totalPriceWithTaxTextField.setText("0.00");
        showSuccessAlert();
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order placed");
        alert.setHeaderText(null);
        alert.setContentText("Order placed successfully!");
        alert.showAndWait();
    }
}
