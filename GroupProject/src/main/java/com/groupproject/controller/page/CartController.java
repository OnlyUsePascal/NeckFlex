package com.groupproject.controller.page;

import com.groupproject.controller.component.CartDetailController;
import com.groupproject.entity.generic.Cart;
import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.generic.ItemDvd;
import com.groupproject.entity.runtime.CurrentCart;
import com.groupproject.toolkit.ObjectHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    @FXML
    VBox itemContainer;
    @FXML
    Label billTotalPriceBox;

    double billTotalPrice = 0;
    Cart cart;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cart = CurrentCart.getCurrentCart();
        cart = new Cart();
        cart.addCartDetail(new ItemDvd("item1", 12.4), 12);
        cart.addCartDetail(new ItemDvd("item2", 1), 1);
        cart.addCartDetail(new ItemDvd("item55", 11.3), 3);
        cart.addCartDetail(new ItemDvd("ifasdf", 1.4), 2);

        for (CartDetail cartDetail : cart.getcartDetailList()){
            try {
                FXMLLoader cartItemLoader = new FXMLLoader(getClass().getResource(PathHandler.getComponentCartDetail()));
                HBox cartDetailPane = cartItemLoader.load();

                CartDetailController cartDetailController = cartItemLoader.getController();
                cartDetailController.setData(cartDetail);
                cartDetailController.setCartController(this);

                itemContainer.getChildren().add(cartDetailPane);
                updateBillTotalPrice(cartDetail.getTotalPrice());
            } catch (IOException err){
                err.printStackTrace();
            }
        }
    }

    public void updateBillTotalPrice(double itemPrice){
        // System.out.println(itemPrice);
        billTotalPrice += itemPrice;
        billTotalPrice = ObjectHandler.getDoubleRound(billTotalPrice);
        billTotalPriceBox.setText("$" + billTotalPrice);
    }

    public void removeCartItem(HBox cartItemPane){
        itemContainer.getChildren().remove(cartItemPane);
    }
}
