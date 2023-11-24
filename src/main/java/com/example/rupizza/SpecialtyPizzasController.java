package com.example.rupizza;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.control.Alert;

public class SpecialtyPizzasController {
//    @FXML
//    private ToggleGroup sizesToggleGroup;
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
    private TextArea toppingsTextArea;

    @FXML
    private TextField priceTextField;

    private void showAlert() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(null);
        alert.setContentText("Pizza type and size are required.");
        alert.showAndWait();
    }
    private void showSuccessAlert() {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Order Placed Successfully");
        alert.setHeaderText(null);
        alert.setContentText("Pizza added to the order!");
        alert.showAndWait();
    }

    @FXML
    public void initialize() {
        pizzaComboBox.getItems().addAll("Deluxe", "Supreme", "Meatzza", "Seafood", "Pepperoni");

        pizzaComboBox.setOnAction(event -> updatePizzaDetails());
    }

    @FXML
    public void updatePizzaDetails() {
        String selectedPizza = pizzaComboBox.getValue();
        String imagePath = "";
        String toppingsText = "";
        double basePrice = 0;
        switch (selectedPizza) {
            case "Deluxe":
                imagePath = "deluxe-img.jpg";
                toppingsText = "Sausage, Pepperoni, Green Pepper, Onion, Mushroom";
                basePrice = 14.99;
                break;
            case "Supreme":
                imagePath = "supreme-img.jpg";
                toppingsText = "Sausage, Ham, Pepperoni, Green Pepper, Onion, Black Olive, Mushroom";
                basePrice = 15.99;
                break;
            case "Meatzza":
                imagePath = "meatzza-img.jpg";
                toppingsText = "Beef, Ham, Sausage, Pepperoni";
                basePrice = 16.99;
                break;
            case "Seafood":
                imagePath = "seafood-img.jpg";
                toppingsText = "Shrimp, Squid, Crab Meats";
                basePrice = 17.99;
                break;
            case "Pepperoni":
                imagePath = "pepperoni-img.jpg";
                toppingsText = "Pepperoni";
                basePrice = 10.99;
                break;
        }
        toppingsTextArea.setText(toppingsText);
        pizzaImageView.setImage(new Image(HelloApplication.class.getResource(imagePath).toExternalForm()));
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
                pizza = new Deluxe(selectedSize, isExtraSauce, isExtraCheese);
                break;
            case "Supreme":
                pizza = new Supreme(selectedSize, isExtraSauce, isExtraCheese);
                break;
            case "Meatzza":
                pizza = new Meatzza(selectedSize, isExtraSauce, isExtraCheese);
                break;
            case "Seafood":
                pizza = new Seafood(selectedSize, isExtraSauce, isExtraCheese);
                break;
            case "Pepperoni":
                pizza = new Pepperoni(selectedSize, isExtraSauce, isExtraCheese);
                break;
        }
        pizzaOrderService.addPizzaToOrder(pizza);
        showSuccessAlert();
    }

}
