package com.groupproject.controller.component;

import com.groupproject.toolkit.ObjectHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemBoxController implements Initializable {
    @FXML
    Rectangle imgFrame;

    @FXML
    Label itemTitle;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Image img = new Image(getClass().getResourceAsStream("banner1.jpg"));
        Image img = ObjectHandler.getImage("banner1.jpg");
        ImagePattern imgView = new ImagePattern(img);

        imgFrame.setFill(imgView);
        // itemBox.getChildren().add(imgView);
    }

    public void setTitle(String titleNew){
        itemTitle.setText(itemTitle.getText() + titleNew);
    }
}
