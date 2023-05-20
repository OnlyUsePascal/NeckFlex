package com.groupproject.controller.popup;

import javafx.fxml.FXML;
import javafx.scene.control.Label;

public class NotiController {
    @FXML
    private Label notiText;

    public void setText(String text){
        notiText.setText(text);
    }
}
