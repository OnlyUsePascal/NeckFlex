package com.groupproject;

import com.groupproject.toolkit.GetterPath;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class ShopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        String pageFile = GetterPath.getPageHome();

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pageFile));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);

        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}