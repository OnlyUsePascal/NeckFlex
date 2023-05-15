package com.groupproject.NotificationFadingTransition;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class scene2Controller {
    @FXML
    private Button backButton;
    public void backScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(scene1.class.getResource("/com/groupproject/scene1.fxml"));
        Stage stage = (Stage) backButton.getScene().getWindow();
        Scene scene = new Scene(loader.load());
        scene1Controller scene1Controller = loader.getController();
        scene1Controller.setLabel();
        stage.setScene(scene);
    }
//    public void setNotification() throws IOException {
//        //get current scene1Controller
//
//        scene1Controller scene1Controller = loader.getController();
//        scene1Controller.setLabel();
//    }
}
