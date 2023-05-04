package com.groupproject.controller.component;

import com.groupproject.entity.generic.Item;
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
    @FXML
    Label itemPrice;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImg("banner1.jpg");

        // itemBox.getChildren().add(imgView);
    }

    public void setData(Item item) {
        setTitle(item.getTitle());
        setPrice(item.getFee());
    }

    public void setImg(String url) {
        Image img = ObjectHandler.getImage("banner1.jpg");
        ImagePattern imgView = new ImagePattern(img);
        imgFrame.setFill(imgView);
    }

    public void setTitle(String titleNew) {
        itemTitle.setText(titleNew);
    }

    public void setPrice(double price){
        itemPrice.setText(String.valueOf(price));
    }
}
