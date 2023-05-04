package com.groupproject;

import com.groupproject.toolkit.DataHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ShopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        //init
        String pageFile = PathHandler.getPageLoginMain();
        // pageFile = PathHandler.getPageItemAll();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pageFile));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //data
        DataHandler.getData();

        //on close
        stage.setOnCloseRequest(event -> {
            postClose(event, stage);
        });
    }

    public void postClose(WindowEvent event, Stage stage){
        event.consume();

        DataHandler.saveData();
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}