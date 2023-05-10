package com.groupproject.controller.page;

import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.ShopSystem;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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

    private Item item;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initData();

    }

    public void initData(){
        cartAmount.setText("1");

    }

    public void addToCart(ActionEvent event) {

        System.out.println("add to cart");

        int newCartQuantity = Integer.parseInt(cartAmount.getText());
        //find existing
        CartDetail cartDetail = ShopSystem.findCartDetail(item);
        if (cartDetail != null){
            newCartQuantity += cartDetail.getQuantity();
            if (newCartQuantity > item.getStock()){
                cartResponse.setText("Insufficient stock");
            } else {
                // cartResponse.setText("Update cart detail");
                System.out.println("update cart detail");
                cartDetail.setQuantity(newCartQuantity);
                ShopSystem.closePopup(event);
            }
        } else {
            if (newCartQuantity > item.getStock()){
                cartResponse.setText("Insufficient stock");
            } else {
                System.out.println("add new cart detail");
                // cartResponse.setText("Add new cart detail");
                ShopSystem.addCartDetail(item, newCartQuantity);
                ShopSystem.closePopup(event);
            }

        }
    }

    public void setData(Item _item){
        item = _item;
        title.setText(item.getTitle());
        genre.setText(item.getGenre());
        quantity.setText(String.valueOf(item.getStock()));
        price.setText(String.valueOf(item.getPrice()));

    }

    public void incAmount(ActionEvent e) {
        cartAmount.setText(Integer.toString(Integer.parseInt(this.cartAmount.getText()) + 1));
        // Integer incremented = Integer.parseInt(this.cartAmount.getText()) + 1;
        // this.cartAmount.setText(incremented.toString());
    }

    public void decAmount(ActionEvent e) {
        cartAmount.setText(Integer.toString(Math.max(Integer.parseInt(cartAmount.getText())-1, 1)));
    }
}
