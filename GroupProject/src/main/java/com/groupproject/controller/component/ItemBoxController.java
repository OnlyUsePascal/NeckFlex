package com.groupproject.controller.component;

import com.groupproject.controller.page.ItemInfoController;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemBoxController implements Initializable {
    @FXML
    private Rectangle imgFrame;
    @FXML
    private Label itemTitle;
    @FXML
    private Label itemPrice;

    private Item item;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setImg("banner1.jpg");

        // itemBox.getChildren().add(imgView);
    }

    public void setData(Item _item) {
        item = _item;
        setTitle(_item.getTitle());
        setPrice(_item.getPrice());
    }

    public void setImg(String url) {
        Image img = ViewHandler.getImage("banner1.jpg");
        ImagePattern imgView = new ImagePattern(img);
        imgFrame.setFill(imgView);
    }

    public void setTitle(String titleNew) {
        itemTitle.setText(titleNew);
    }

    public void setPrice(double price){
        itemPrice.setText(String.valueOf(price));
    }

    public void getPopup(ActionEvent event){
        System.out.println("popup");
        FXMLLoader itemLoader = new FXMLLoader(getClass().getResource(PathHandler.getPageItemInfo()));
        try {
            AnchorPane itemInfoPane = itemLoader.load();

            ItemInfoController itemInfoController = itemLoader.getController();
            itemInfoController.setData(item);

            ViewHandler.popupGet(itemInfoPane, null);
            // ShopSystem.showPopup(popup);
        } catch (IOException err){
            err.printStackTrace();
        }
    }
}
