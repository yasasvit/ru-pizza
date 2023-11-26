package com.example.rupizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import java.io.File;

/**
 * This class is the controller class for managing store orders
 * @authors Yasasvi Tallapaneni, Pranav Gummaluri
 */
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

    /**
     * This method initializes the controller
     */
    @FXML
    public void initialize() {
        initializeOrderNumberComboBox();

        orderNumberComboBox.setOnAction(event -> updateOrderDetails());
        removeOrderButton.setOnAction(event -> removeSelectedOrder());
    }

    /**
     * This method initializes the order number ComboBox with available order numbers
     */
    private void initializeOrderNumberComboBox() {
        ObservableList<Integer> orderNumbers = FXCollections.observableArrayList();
        for (Order order : storeOrders.getOrders()) {
            orderNumbers.add(order.getOrderNumber());
        }
        orderNumberComboBox.setItems(orderNumbers);
    }

    /**
     * This method updates order details based on the selected order number
     */
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

    /**
     * This method retrieves a formatted string of toppings from a pizza
     * @param Pizza object "pizza" representing the pizza selected
     * @return A string representing the toppings of the pizza that are selected
     */
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

    /**
     * This method removes the selected order from storeOrders
     */
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

    /**
     * This method exports store orders to a text file
     */
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

    /**
     * This method clears order details from the UI
     */
    private void clearOrderDetails() {
        orderDetailsListView.setItems(FXCollections.emptyObservableList());
        orderTotalTextField.clear();
    }

    /**
     * This method displays an alert with the given title and content text
     * @param String object "title" representing the title of the alert box
     * @param String object "contentText" representing the alert text
     */
    private void showAlert(String title, String contentText) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(contentText);
        alert.showAndWait();
    }

    /**
     * This method displays a success alert for exporting store orders
     */
    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Store Orders exported");
        alert.setHeaderText(null);
        alert.setContentText("Store Orders exported successfully!");
        alert.showAndWait();
    }

}
