package com.groupproject.ImportAndShow;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class scene2 extends Application {

    scene1Controller controller;
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader loader = new FXMLLoader(scene2.class.getResource("/com/groupproject/scene2.fxml"));
        Scene scene = new Scene(loader.load());
        controller = loader.getController();
        stage.setScene(scene);
        stage.setTitle("Scene2");
        stage.show();
    }

    public scene1Controller getController() {
        return controller;
    }

    public static void main(String[] args) {
        launch(args);
    }


}
