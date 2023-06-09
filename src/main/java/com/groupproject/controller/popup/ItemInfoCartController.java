package com.groupproject.controller.popup;

import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemInfoCartController implements Initializable {
    @FXML
    private Label titleBox;
    @FXML
    private Label genreBox;
    @FXML
    private Label priceBox;
    @FXML
    private Label messBox;
    @FXML
    private Label stockBox;
    @FXML
    private TextField quantityBox;
    @FXML
    private Button updateCartBtn;
    @FXML
    private Rectangle imgFrame;
    @FXML
    private HBox amountPane;
    @FXML
    private Label descBox;

    private Item item;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if (EntityHandler.getCurrentUser().isAdmin()) {
            amountPane.setDisable(true);
            ViewHandler.toggleNode(updateCartBtn, false);
        }
    }

    // --- MAIN ---
    public void addToCart(ActionEvent event) {
        int newCartQuantity = Integer.parseInt(quantityBox.getText());
        EntityHandler.addCartDetail(item, newCartQuantity);

        ViewHandler.closePopup(event);
        ViewHandler.getNoti("Cart updated!", null);
    }

    public void updateCart(ActionEvent event) {
        int newCartQuantity = Integer.parseInt(quantityBox.getText());
        CartDetail cartDetail = EntityHandler.getCartDetail(item);
        cartDetail.setQuantity(newCartQuantity);

        ViewHandler.closePopup(event);
        ViewHandler.getNoti("Cart updated!", null);
    }

    public void incAmount(ActionEvent e) {
        quantityBox.setText(Integer.toString(Math.min(Integer.parseInt(quantityBox.getText()) + 1, item.getStock())));
    }

    public void decAmount(ActionEvent e) {
        quantityBox.setText(Integer.toString(Math.max(Integer.parseInt(quantityBox.getText()) - 1, 1)));
    }

    // --- BACK ---
    public void refreshPage() {
        CartDetail cartDetail = EntityHandler.getCartDetail(item);
        if (cartDetail != null) {
            quantityBox.setText(String.valueOf(cartDetail.getQuantity()));
        } else {
            quantityBox.setText("1");
            ViewHandler.toggleNode(updateCartBtn, false);
        }
    }

    public void setData(Item _item) {
        item = _item;

        titleBox.setText(item.getTitle());
        genreBox.setText(item.getGenreString());
        stockBox.setText("Stock: " + item.getStock());
        priceBox.setText("$" + item.getPrice());
        descBox.setText(item.getDesc());
        ViewHandler.fillShapeWithImage(item.getImgName(), imgFrame);


        if (!item.isAvailable()){
            amountPane.setDisable(true);
            ViewHandler.toggleNode(updateCartBtn, false);
        }
        if (!EntityHandler.getCurrentUser().isAdmin()) refreshPage();
    }
}
