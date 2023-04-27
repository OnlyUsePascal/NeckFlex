package com.groupproject;

import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeScreenController implements Initializable{
    @FXML
    private Label HomeScreenMessage = new Label();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        HomeScreenMessage.setText("Your are Login as " + SystemShop.loginAccount.Account2Str());
    }
}
