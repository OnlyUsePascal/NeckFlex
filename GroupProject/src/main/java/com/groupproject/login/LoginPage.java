package com.groupproject.login;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class LoginPage extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        primaryStage.setResizable(false);
        Parent root = FXMLLoader.load((Objects.requireNonNull(getClass().getResource("LoginPage.fxml"))));
        primaryStage.setTitle("Login Page");
        primaryStage.setScene(new Scene(root, 1600, 900));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}