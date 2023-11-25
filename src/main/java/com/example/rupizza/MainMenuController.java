package com.example.rupizza;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController {

    @FXML
    private Button orderSpecialtyPizzasButton;

    @FXML
    private Button orderBuildYourOwnButton;

    @FXML
    private Button browseCurrentOrderButton;

    @FXML
    private Button browseStoreOrdersButton;

    @FXML
    void handleOrderSpecialtyPizzas(ActionEvent event) {
        loadFXML("specialty-pizzas.fxml", "Order Specialty Pizzas");
    }

    @FXML
    void handleOrderBuildYourOwn(ActionEvent event) {
        loadFXML("build-your-own.fxml", "Order Build Your Own Pizza");
    }

    @FXML
    void handleBrowseCurrentOrder(ActionEvent event) {
        loadFXML("current-order.fxml", "Browse Current Order");
    }

    @FXML
    void handleBrowseStoreOrders(ActionEvent event) {
        loadFXML("store-order.fxml", "Browse Store Orders");
    }
    private void loadFXML(String fxmlFile, String windowTitle) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = fxmlLoader.load();
            Stage stage = new Stage();
            stage.setTitle(windowTitle);
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
