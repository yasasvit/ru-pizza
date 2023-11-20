package com.example.demo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private void orderSpecialtyPizzas() {
        // Code to handle ordering specialty pizzas
    }

    @FXML
    private void BuildYourOwn() {
        // Code to handle ordering build your own pizza
    }

    @FXML
    private void CurrentOrder() {
        // Code to handle browsing the current order
    }
    @FXML
    private void showCurrentOrder() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com.example.demo/CurrentOrder.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage();
            stage.setTitle("Current Order");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void StoreOrders() {
        // Code to handle browsing store orders
    }
}
