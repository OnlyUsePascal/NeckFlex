package com.groupproject.NotificationFadingTransition;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class scene2 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(scene2.class.getResource("/com/groupproject/scene2.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Scene2");
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }


}
