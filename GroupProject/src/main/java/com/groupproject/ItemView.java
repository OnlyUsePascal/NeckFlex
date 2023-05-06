package com.groupproject;



import com.groupproject.types.SystemShop;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;


public class ItemView extends Application {

//    public static Stage window = new Stage();

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws IOException {
//        window = stage;
        IOStream.loadData();
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ItemView.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);

//        SystemShop.displayItem();
        stage.setResizable(false);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.show();
    }
}
