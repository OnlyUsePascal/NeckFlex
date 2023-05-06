package com.groupproject.controller.component;

import com.groupproject.controller.page.CartController;
import com.groupproject.entity.generic.CartDetail;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

import java.net.URL;
import java.util.ResourceBundle;

public class CartDetailController implements Initializable {
    @FXML
    Label titleBox;
    @FXML
    Label unitPriceBox;
    @FXML
    Label quantityBox;
    @FXML
    Label totalPriceBox;
    @FXML
    HBox hboxContainer;

    CartDetail cartDetail;
    CartController cartController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane.setTopAnchor(hboxContainer, 10.0);
        // String style = "-fx-border-width: 2px";
        // itemContainer.setStyle("-fx-border-width: 2px;");
    }

    public void setData(CartDetail cartDetail) {
        this.cartDetail = cartDetail;
        titleBox.setText(this.cartDetail.getTitle());
        unitPriceBox.setText(String.valueOf(this.cartDetail.getTotalPrice()));
        quantityBox.setText(String.valueOf(this.cartDetail.getQuantity()));
        totalPriceBox.setText(String.valueOf(this.cartDetail.getTotalPrice()));
    }

    public void updateQuantity(ActionEvent event){
        Button btn = (Button) event.getSource();
        String btnText = btn.getText();
        double itemPrice = cartDetail.getItem().getPrice();
        if (btnText.equals("+")){
            if (cartDetail.setQuantity(cartDetail.getQuantity() + 1)){
                cartController.updateBillTotalPrice(itemPrice);
            }
        } else if (btnText.equals("-")){
            if (cartDetail.setQuantity(cartDetail.getQuantity() - 1)){
                cartController.updateBillTotalPrice(-itemPrice);
            }
        }

        quantityBox.setText(String.valueOf(cartDetail.getQuantity()));
        totalPriceBox.setText(String.valueOf(cartDetail.getTotalPrice()));
    }

    public void setCartController(CartController cartController) {
        this.cartController = cartController;
    }

    public void removeCartItem(ActionEvent event){
        cartController.updateBillTotalPrice(-cartDetail.getTotalPrice());
        cartController.removeCartItem(hboxContainer);
    }

    public void updateCheckBox(ActionEvent event){
        CheckBox checkBox = (CheckBox) event.getSource();
        if (checkBox.isSelected()){
            cartController.updateBillTotalPrice(cartDetail.getTotalPrice());
        } else {
            cartController.updateBillTotalPrice(-cartDetail.getTotalPrice());
        }
    }
}
