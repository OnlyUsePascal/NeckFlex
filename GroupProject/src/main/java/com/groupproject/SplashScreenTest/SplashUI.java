package com.groupproject.SplashScreenTest;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;

public class SplashUI extends Application {

    public static void main(String[] args) {
        launch(args);
    }

//    public static Stage window = new Stage();
//
//    public static void close(){
//        window.close();
//    }

    @Override
    public void start(Stage stage) throws IOException {
//        window = stage;
        FXMLLoader fxmlLoader = new FXMLLoader(SplashUI.class.getResource("/com/groupproject/SplashUI.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setScene(scene);
        stage.show();
    }
}
