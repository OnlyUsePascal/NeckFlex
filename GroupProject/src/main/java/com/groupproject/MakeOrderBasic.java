package com.groupproject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MakeOrderBasic extends Application {
    public static void main(String[] args) {
        launch();
    }
    @Override
    public void start(Stage stage) throws Exception {
        FXMLLoader fxmlLoader = new FXMLLoader(MakeOrderBasic.class.getResource("MakeOrderBasic.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 1600 , 900);
        MakeOrderBasicController controller = fxmlLoader.getController();

        stage.setTitle("Make Order");
        stage.setScene(scene);
        stage.setResizable(false);

        stage.setOnCloseRequest(windowEvent -> {
            controller.saveOrderData();
        });

        stage.show();
    }
}
