package com.example.rupizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

public class StoreOrderController {
    private StoreOrders storeOrders = StoreOrders.getInstance();
    private PizzaOrderService pizzaOrderService = PizzaOrderService.getInstance();

    @FXML
    private ComboBox<Integer> orderNumberComboBox;

    @FXML
    private ListView<String> orderDetailsListView;

    @FXML
    private TextField orderTotalTextField;

    @FXML
    private Button removeOrderButton;
    @FXML
    private Button exportStoreOrdersButton;

    @FXML
    public void initialize() {
        initializeOrderNumberComboBox();

        orderNumberComboBox.setOnAction(event -> updateOrderDetails());
        removeOrderButton.setOnAction(event -> removeSelectedOrder());
    }

    private void initializeOrderNumberComboBox() {
        ObservableList<Integer> orderNumbers = FXCollections.observableArrayList();
        for (Order order : storeOrders.getOrders()) {
            orderNumbers.add(order.getOrderNumber());
        }
        orderNumberComboBox.setItems(orderNumbers);
    }
    @FXML
    private void updateOrderDetails() {
        Integer selectedOrderNumber = orderNumberComboBox.getValue();
        if (selectedOrderNumber == null) {
            return;
        }
        // Find the selected order in storeOrders
        Order selectedOrder = null;
        for (Order order : storeOrders.getOrders()) {
            if (order.getOrderNumber() == selectedOrderNumber) {
                selectedOrder = order;
                break;
            }
        }

        if (selectedOrder != null) {
            ObservableList<String> orderDetailsList = FXCollections.observableArrayList();

            for (Pizza pizza : selectedOrder.getPizzas()) {
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

            double orderTotal = selectedOrder.getTotalOrderPrice();
            orderTotalTextField.setText(String.format("%.2f", orderTotal));
        }
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
    private void removeSelectedOrder() {
        Integer selectedOrderNumber = orderNumberComboBox.getValue();
        if (selectedOrderNumber == null) {
            showAlert("No Order Selected", "Please select an order to remove.");
            return;
        }
        // Remove the selected order from storeOrders
        Order selectedOrder = null;
        for (Order order : storeOrders.getOrders()) {
            if (order.getOrderNumber() == selectedOrderNumber) {
                selectedOrder = order;
                break;
            }
        }

        if (selectedOrder != null) {
            storeOrders.getOrders().remove(selectedOrder);
            orderNumberComboBox.getItems().remove(selectedOrderNumber);
            clearOrderDetails();
        }
    }
    @FXML
    private void exportStoreOrders() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Text Files", "*.txt"));
        File file = fileChooser.showSaveDialog(null);

        if (file != null) {
            storeOrders.export(file.getAbsolutePath());
        }
        showSuccessAlert();
    }
    private void clearOrderDetails() {
        orderDetailsListView.setItems(FXCollections.emptyObservableList());
        orderTotalTextField.clear();
    }
    private void showAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Store Orders exported");
        alert.setHeaderText(null);
        alert.setContentText("Store Orders exported successfully!");
        alert.showAndWait();
    }

}
