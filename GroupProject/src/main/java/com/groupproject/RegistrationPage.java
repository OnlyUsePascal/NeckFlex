package com.groupproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationPage extends Application {

    public static Stage window = new Stage();
    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(RegistrationPage.class.getResource("RegistrationPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600 , 900);
        stage.setTitle("Registration Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}