package com.example.rupizza;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * The main class for the RU Pizza application
 * @authors Pranav Gummaluri, Yasasvi Tallapaneni
 */
public class RUPizzaMain extends Application {

    /**
     * This method is the starting point for the JavaFX application
     * @param stage The primary stage for the application
     */
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RUPizzaMain.class.getResource("main-menu.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 340, 360);
        stage.setTitle("RU Pizza <Main Menu>");
        stage.setScene(scene);
        stage.show();
    }

    /**
    * Main method to launch the JavaFX application
    */
    public static void main(String[] args) {
        launch();
    }
}
