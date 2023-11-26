package com.example.rupizza;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TextField;

/**
 * This is the controller class for the Specialty Pizzas view
 * @authors Yasasvi Tallapaneni, Pranav Gummaluri
 */
public class SpecialtyPizzasController {
    private PizzaOrderService pizzaOrderService = PizzaOrderService.getInstance();
    @FXML
    private RadioButton smallRadioButton;

    @FXML
    private RadioButton mediumRadioButton;

    @FXML
    private RadioButton largeRadioButton;

    @FXML
    private CheckBox extraSauceCheckBox;

    @FXML
    private CheckBox extraCheeseCheckBox;

    @FXML
    private ComboBox<String> pizzaComboBox;

    @FXML
    private ImageView pizzaImageView;

    @FXML
    private ListView<String> toppingsListView;

    @FXML
    private TextField priceTextField;
    @FXML
    private TextField sauceTextField;

    /**
     * This method displays an error alert when pizza type and size are not selected
     */
    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Pizza type and size are required.");
        alert.showAndWait();
    }

    /**
     * Displays an success alert when pizza is added to the order
     */
    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Pizza added");
        alert.setHeaderText(null);
        alert.setContentText("Pizza added to the order!");
        alert.showAndWait();
    }

    /**
     * This method initializes the controller
     */
    @FXML
    public void initialize() {
        pizzaComboBox.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");
        toppingsListView.setItems(FXCollections.emptyObservableList());
        pizzaComboBox.setOnAction(event -> updatePizzaDetails());
    }

    /**
     * This method updates the pizza details based on the specified pizza type
     */
    @FXML
    public void updatePizzaDetails() {
        String selectedPizza = pizzaComboBox.getValue();
        if (selectedPizza == null) {
            return;
        }
        String imagePath = "";
        String toppingsText = "";
        double basePrice = 0;
        ObservableList<String> toppingsList = FXCollections.observableArrayList();
        switch (selectedPizza) {
            case "Deluxe":
                imagePath = "deluxe-img.jpg";
                toppingsList.addAll("Sausage", "Pepperoni", "Green Pepper", "Onion", "Mushroom");
                basePrice = 14.99;
                sauceTextField.setText("Tomato");
                break;
            case "Supreme":
                imagePath = "supreme-img.jpg";
                toppingsList.addAll("Sausage", "Ham", "Pepperoni", "Green Pepper", "Onion", "Black Olive", "Mushroom");
                sauceTextField.setText("Tomato");
                basePrice = 15.99;
                break;
            case "Meatzza":
                imagePath = "meatzza-img.jpg";
                toppingsList.addAll("Beef", "Ham", "Sausage", "Pepperoni");
                basePrice = 16.99;
                sauceTextField.setText("Tomato");
                break;
            case "Seafood":
                imagePath = "seafood-img.jpg";
                toppingsList.addAll("Shrimp", "Squid", "Crab Meats");
                basePrice = 17.99;
                sauceTextField.setText("Alfredo");
                break;
            case "Pepperoni":
                imagePath = "pepperoni-img.jpg";
                toppingsList.addAll("Pepperoni");
                basePrice = 10.99;
                sauceTextField.setText("Tomato");
                break;
        }
        toppingsListView.setItems(toppingsList);
        pizzaImageView.setImage(new Image(RUPizzaMain.class.getResource(imagePath).toExternalForm()));
        if (smallRadioButton.isSelected()) {
        } else if (mediumRadioButton.isSelected()) {
            basePrice += 2.0;
        } else if (largeRadioButton.isSelected()) {
            basePrice += 4.0;
        } else {
            return;
        }
        if (extraCheeseCheckBox.isSelected()) {
            basePrice += 1.0;
        }
        if (extraSauceCheckBox.isSelected()) {
            basePrice += 1.0;
        }
        priceTextField.setText(String.format("%.2f", basePrice));
    }

    /**
     * This method places an order for the specified specialty pizza
     */
    @FXML
    public void placeSpecialtyPizzaOrder() {
        String selectedPizza = pizzaComboBox.getValue();
        Size selectedSize = null;
        if (smallRadioButton.isSelected()) {
            selectedSize = Size.small;
        } else if (mediumRadioButton.isSelected()) {
            selectedSize = Size.medium;
        } else if (largeRadioButton.isSelected()) {
            selectedSize = Size.large;
        }
        if (selectedPizza == null || selectedSize == null) {
            showAlert();
            return;
        }
        boolean isExtraSauce = extraSauceCheckBox.isSelected();
        boolean isExtraCheese = extraCheeseCheckBox.isSelected();
        Pizza pizza = null;
        switch (selectedPizza) {
            case "Deluxe":
                pizza = PizzaMaker.createPizza("deluxe", selectedSize, null, null, isExtraSauce, isExtraCheese);
                break;
            case "Supreme":
                pizza = PizzaMaker.createPizza("supreme",selectedSize, null, null, isExtraSauce, isExtraCheese);
                break;
            case "Meatzza":
                pizza = PizzaMaker.createPizza("meatzza", selectedSize, null, null, isExtraSauce, isExtraCheese);
                break;
            case "Seafood":
                pizza = PizzaMaker.createPizza("seafood", selectedSize, null, null, isExtraSauce, isExtraCheese);
                break;
            case "Pepperoni":
                pizza = PizzaMaker.createPizza("pepperoni", selectedSize, null, null, isExtraSauce, isExtraCheese);
                break;
        }
        pizzaOrderService.addPizzaToOrder(pizza);
        showSuccessAlert();
    }
}
