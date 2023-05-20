package com.groupproject.controller.popup;

import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ResourceBundle;

public class ItemInfoCartController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private Label genre;
    @FXML
    private Label quantity;
    @FXML
    private Label price;
    @FXML
    private Label cartResponse;
    @FXML
    private TextField cartAmount;
    @FXML
    private Button updateCartBtn;
    @FXML
    private Rectangle imgFrame;

    private Item item;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void refreshPage() {
        CartDetail cartDetail = EntityHandler.getCartDetail(item);
        if (cartDetail != null) {
            cartAmount.setText(String.valueOf(cartDetail.getQuantity()));
        } else {
            cartAmount.setText("1");
            ViewHandler.toggleNode(updateCartBtn, false);
        }
    }

    public void addToCart(ActionEvent event) {
        int newCartQuantity = Integer.parseInt(cartAmount.getText());
        EntityHandler.addCartDetail(item, newCartQuantity);

        ViewHandler.closePopup(event);
    }

    public void updateCart(ActionEvent event) {
        int newCartQuantity = Integer.parseInt(cartAmount.getText());
        CartDetail cartDetail = EntityHandler.getCartDetail(item);
        cartDetail.setQuantity(newCartQuantity);

        ViewHandler.closePopup(event);
    }

    public void setData(Item _item) {
        item = _item;
        title.setText(item.getTitle());
        genre.setText(item.getGenreString());
        quantity.setText(String.valueOf(item.getStock()));
        price.setText(String.valueOf(item.getPrice()));
        ViewHandler.fillShapeWithImage(item.getImgName(), imgFrame);

        refreshPage();
    }

    public void incAmount(ActionEvent e) {
        cartAmount.setText(Integer.toString(Math.min(Integer.parseInt(cartAmount.getText()) + 1, item.getStock())));
    }

    public void decAmount(ActionEvent e) {
        cartAmount.setText(Integer.toString(Math.max(Integer.parseInt(cartAmount.getText()) - 1, 1)));
    }
}