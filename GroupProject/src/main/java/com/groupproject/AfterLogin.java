package com.groupproject;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class AfterLogin{
    @FXML
    private Label hellobox;
    public void setHellobox(String name) {
        hellobox.setText("Hello " + name);
    }
}
