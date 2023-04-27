package com.groupproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeScreen extends Application {
    public static Stage stage;
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HomeScreen.class.getResource("HomeScreen.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600 , 900);
        stage.setTitle("Home Screen");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //Close program
        stage.setOnCloseRequest(e -> {
            e.consume();
            try {
                IOStream.saveData();
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            stage.close();
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
