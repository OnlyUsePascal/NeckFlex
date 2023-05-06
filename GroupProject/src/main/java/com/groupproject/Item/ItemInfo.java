package com.groupproject.Item;

import com.groupproject.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemInfo extends Application {

    public static void main(String[] args) {
        launch(args);
    }

//    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ItemInfo.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 726, 512);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}
