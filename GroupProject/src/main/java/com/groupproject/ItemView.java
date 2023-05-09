package com.groupproject;

import com.groupproject.bank.BankAccount;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

public class ItemView extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(ItemView.class.getResource("ItemView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600 , 900);
        stage.setTitle("Item View");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
