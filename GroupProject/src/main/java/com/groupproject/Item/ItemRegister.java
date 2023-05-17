package com.groupproject.Item;

import com.groupproject.HelloApplication;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class ItemRegister extends Application {

    public static Stage window;

    public static void main(String[] args) {
        launch(args);
    }

    //    @Override
    public void start(Stage stage) throws IOException {
        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ItemRegister.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 996, 581);
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }

}
