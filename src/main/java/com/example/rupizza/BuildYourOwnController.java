package com.example.rupizza;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import java.util.ArrayList;

public class BuildYourOwnController {

    private PizzaOrderService pizzaOrderService = PizzaOrderService.getInstance();

    @FXML
    private ComboBox<String> sizeComboBox;

    @FXML
    private RadioButton tomatoSauceRadioButton;

    @FXML
    private RadioButton alfredoSauceRadioButton;

    @FXML
    private CheckBox extraSauceCheckBox;

    @FXML
    private CheckBox extraCheeseCheckBox;

    @FXML
    private ListView<String> additionalToppingsListView;

    @FXML
    private ListView<String> selectedToppingsListView;

    @FXML
    private Button addToppingButton;

    @FXML
    private Button removeToppingButton;

    @FXML
    private Button placeOrderButton;
    @FXML
    private TextField priceTextField;

    @FXML
    public void initialize() {
        initializeSizeComboBox();
        initializeToppingsListView();

        addToppingButton.setOnAction(event -> addTopping());
        removeToppingButton.setOnAction(event -> removeTopping());
        placeOrderButton.setOnAction(event -> placeOrder());
    }

    private void initializeSizeComboBox() {
        ObservableList<String> sizes = FXCollections.observableArrayList("small", "medium", "large");
        sizeComboBox.setItems(sizes);
    }

    private void initializeToppingsListView() {
        ObservableList<String> toppings = FXCollections.observableArrayList(
                "Sausage", "Chicken", "Beef", "Ham", "Pepperoni", "Shrimp", "Squid",
                "CrabMeats", "GreenPepper", "Onion", "Mushroom", "Pineapple", "BlackOlives");
        additionalToppingsListView.setItems(toppings);
    }
    @FXML
    private void addTopping() {
        String selectedTopping = additionalToppingsListView.getSelectionModel().getSelectedItem();

        if (selectedTopping == null) {
            showAlert("Please select a topping to add.");
            return;
        }

        ObservableList<String> selectedToppings = selectedToppingsListView.getItems();

        if (selectedToppings.size() >= 7) {
            showAlert("Maximum of 7 toppings can be selected.");
            return;
        }

        additionalToppingsListView.getItems().remove(selectedTopping);
        selectedToppings.add(selectedTopping);
        updatePriceField();
    }
    @FXML
    private void removeTopping() {
        String selectedTopping = selectedToppingsListView.getSelectionModel().getSelectedItem();

        if (selectedTopping == null) {
            showAlert("Please select a topping to remove.");
            return;
        }
        if (selectedToppingsListView.getItems().size() == 3) {
            showAlert("Cannot have less than 3 toppings");
            return;
        }
        additionalToppingsListView.getItems().add(selectedTopping);
        selectedToppingsListView.getItems().remove(selectedTopping);
        updatePriceField();
    }

    public void placeOrder() {
        String selectedSize = sizeComboBox.getValue();
        String selectedSauce = tomatoSauceRadioButton.isSelected() ? "tomato" : (alfredoSauceRadioButton.isSelected() ? "alfredo" : null);

        if (selectedSize == null || selectedSauce == null) {
            showAlert("Size and sauce must be chosen.");
            return;
        }

        ObservableList<String> selectedToppings = selectedToppingsListView.getItems();

        if (selectedToppings.size() < 3) {
            showAlert("At least 3 toppings must be chosen.");
            return;
        }

        ArrayList<Topping> toppings = new ArrayList<>();
        for (String topping : selectedToppings) {
            toppings.add(Topping.valueOf(topping));
        }

        boolean isExtraSauce = extraSauceCheckBox.isSelected();
        boolean isExtraCheese = extraCheeseCheckBox.isSelected();

        Pizza pizza = PizzaMaker.createPizza("buildyourown", Size.valueOf(selectedSize), Sauce.valueOf(selectedSauce), toppings, isExtraSauce, isExtraCheese);

        pizzaOrderService.addPizzaToOrder(pizza);
        showSuccessAlert();
    }

    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza added");
        alert.setHeaderText(null);
        alert.setContentText("Pizza added to the order!");
        alert.showAndWait();
    }

    private void showAlert(String content) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText(content);
        alert.showAndWait();
    }
    @FXML
    private void updatePriceField(){
        int toppingsCount = selectedToppingsListView.getItems().size();
        if (toppingsCount < 3) {
            return;
        }
        String selectedSize = sizeComboBox.getValue();
        String selectedSauce = tomatoSauceRadioButton.isSelected() ? "tomato" : (alfredoSauceRadioButton.isSelected() ? "alfredo" : null);
        if (selectedSize == null || selectedSauce == null) {
            return;
        }
        double basePrice = 0.0;
        switch (selectedSize) {
            case ("small"):
                basePrice = 8.99;
                break;
            case ("medium"):
                basePrice = 10.99;
                break;
            case ("large"):
                basePrice = 12.99;
                break;
        }
        double toppingPrice = 1.49;
        basePrice += (toppingPrice * (toppingsCount - 3));
        if (extraCheeseCheckBox.isSelected()) {
            basePrice += 1.0;
        }
        if (extraSauceCheckBox.isSelected()) {
            basePrice += 1.0;
        }
        priceTextField.setText(String.format("%.2f", basePrice));
    }
}
