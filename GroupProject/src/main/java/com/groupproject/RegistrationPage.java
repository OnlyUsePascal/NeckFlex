package com.groupproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class RegistrationPage extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(RegistrationPage.class.getResource("RegistrationPage.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 450, 357);
        stage.setTitle("Registration Page");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}