package com.example.demo;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class MainMenuController extends Application {

    public void orderSpecialtyPizzas(javafx.event.ActionEvent actionEvent) {
        
    }
    public void BuildYourOwn(ActionEvent actionEvent) {
        
    }
    public void StoreOrders(ActionEvent actionEvent) {
        
    }
    public void CurrentOrder(ActionEvent actionEvent) {
        
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        showMainMenu(primaryStage);
    }

    public void showMainMenu(Stage stage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/example/demo/MainMenu.fxml"));
            Parent root = loader.load();

            stage.setTitle("Build Your Own Pizza App");
            stage.setScene(new Scene(root, 600, 400));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    
}
