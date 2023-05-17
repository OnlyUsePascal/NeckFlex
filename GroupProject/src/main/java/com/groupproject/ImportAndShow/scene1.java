package com.groupproject.ImportAndShow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class scene1 extends Application {
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(scene1.class.getResource("/com/groupproject/scene1A.fxml"));
        Scene scene = new Scene(loader.load());
        stage.setScene(scene);
        stage.setTitle("Scene1");
        stage.show();
    }

    public static void main(String[] args) {
            launch(args);
    }
}
