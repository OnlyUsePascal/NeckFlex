package com.groupproject.controller.page;

import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemInfoController implements Initializable {
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

    private Item item;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void refreshPage() {
        CartDetail cartDetail = EntityHandler.cartDetailGet(item);
        if (cartDetail != null) {
            cartAmount.setText(String.valueOf(cartDetail.getQuantity()));
        } else {
            cartAmount.setText("1");
            ViewHandler.nodeHide(updateCartBtn);
        }
    }

    public void addToCart(ActionEvent event) {
        // System.out.println("add to cart");
        int newCartQuantity = Integer.parseInt(cartAmount.getText());
        EntityHandler.cartDetailAdd(item, newCartQuantity);
        // ShopSystem.closePopup(event);
        ViewHandler.popupClose(event);
    }

    public void updateCart(ActionEvent event) {
        // System.out.println("update cart");
        int newCartQuantity = Integer.parseInt(cartAmount.getText());
        CartDetail cartDetail = EntityHandler.cartDetailGet(item);
        cartDetail.setQuantity(newCartQuantity);

        // ShopSystem.closePopup(event);
        ViewHandler.popupClose(event);
    }

    public void setData(Item _item) {
        item = _item;
        title.setText(item.getTitle());
        genre.setText(item.getGenre());
        quantity.setText(String.valueOf(item.getStock()));
        price.setText(String.valueOf(item.getPrice()));

        refreshPage();
    }

    public void incAmount(ActionEvent e) {
        cartAmount.setText(Integer.toString(Math.min(Integer.parseInt(cartAmount.getText()) + 1, item.getStock())));
    }

    public void decAmount(ActionEvent e) {
        cartAmount.setText(Integer.toString(Math.max(Integer.parseInt(cartAmount.getText()) - 1, 1)));
    }
}
