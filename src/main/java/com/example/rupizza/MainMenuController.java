package com.example.rupizza;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
* This class is the controller for the Main Menu FXML file
* @authors Pranav Gummaluri, Yasasvi Tallapaneni
*/
public class MainMenuController {

    @FXML
    private Button orderSpecialtyPizzasButton;

    @FXML
    private Button orderBuildYourOwnButton;

    @FXML
    private Button browseCurrentOrderButton;

    @FXML
    private Button browseStoreOrdersButton;

    /**
    * This method activates when the "Order Specialty Pizzas" button in the FXML window is clicked on by the user
    * @param ActionEvent event representing the user clicking on the button
    */
    @FXML
    void handleOrderSpecialtyPizzas(ActionEvent event) {
        loadFXML("specialty-pizzas.fxml", "Order Specialty Pizzas");
    }

    /**
    * This method activates when the "Build Your Own Pizza" button in the FXML window is clicked on by the user
    * @param ActionEvent event representing the user clicking on the button
    */
    @FXML
    void handleOrderBuildYourOwn(ActionEvent event) {
        loadFXML("build-your-own.fxml", "Order Build Your Own Pizza");
    }

    /**
    * This method activates when the "Current Orders" button in the FXML window is clicked on by the user
    * @param ActionEvent event representing the user clicking on the button
    */
    @FXML
    void handleBrowseCurrentOrder(ActionEvent event) {
        loadFXML("current-order.fxml", "Browse Current Order");
    }

    /**
    * This method activates when the "Store Orders" button in the FXML window is clicked on by the user
    * @param ActionEvent event representing the user clicking on the button
    */
    @FXML
    void handleBrowseStoreOrders(ActionEvent event) {
        loadFXML("store-order.fxml", "Browse Store Orders");
    }

    /**
    * This method loads the FXML file and displays the FXML content
    * @param String xmlFile which represents the FXML file to be loaded
    * @param String windowTitle which represents the title on the FXML window
    */
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
