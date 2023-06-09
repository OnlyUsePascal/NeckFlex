package com.groupproject;

import com.groupproject.entity.generic.Cart;
import com.groupproject.entity.EntityHandler;
import com.groupproject.toolkit.DataHandler;
import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ShopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        new Thread(() -> {
            initData();

            Platform.runLater(() -> {
                initView(stage);
            });
        }).start();
    }

    public void initData(){
        DataHandler.getData();
        // EntityHandler.setCurrentUser(EntityHandler.getAccountList().get(1));
    }

    public void initView(Stage stage){
        try {
            ViewHandler.setCurrentStage(stage);
            String pageFile = PathHandler.getPageLoginMain();
            // pageFile = PathHandler.getPageHome();
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pageFile));
            Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
            stage.setTitle("NeckFlex");
            stage.setScene(scene);
            stage.setResizable(false);
            stage.show();

            //on close
            stage.setOnCloseRequest(event -> {
                postClose(event, stage);
            });

        } catch (IOException err) {
            err.printStackTrace();
        }
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