package com.example.demo;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

public class SpecialtyPizzaController {

    public TextField priceTextField;
    @FXML
    private ComboBox<String> pizzaTypeComboBox;

    @FXML
    private ToggleGroup sizeToggleGroup;

    @FXML
    private void placeOrder() {
        // Handle place order action
        String selectedPizzaType = pizzaTypeComboBox.getValue();
        RadioButton selectedSizeButton = (RadioButton) sizeToggleGroup.getSelectedToggle();
        String selectedSize = selectedSizeButton.getText();

        System.out.println("Pizza Type: " + selectedPizzaType);
        System.out.println("Pizza Size: " + selectedSize);
    }
}
