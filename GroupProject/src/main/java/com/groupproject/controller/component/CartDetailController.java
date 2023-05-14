package com.groupproject.controller.component;

import com.groupproject.controller.page.CartController;
import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.runtime.EntityHandler;
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

        int newQuantity = cartDetail.getQuantity() + (btnText.equals("+") ? 1 : -1);
        cartDetail.setQuantity(newQuantity);

        cartController.refreshBill();
        quantityBox.setText(String.valueOf(cartDetail.getQuantity()));
        totalPriceBox.setText(String.valueOf(cartDetail.getTotalPrice()));
    }

    public void setCartController(CartController cartController) {
        this.cartController = cartController;
    }

    public void removeCartDetail(ActionEvent event){
        EntityHandler.cartGet().cartDetailRemove(cartDetail);
        cartController.refreshPage();
    }

    public void updateCheckBox(ActionEvent event){
        CheckBox checkBox = (CheckBox) event.getSource();
        double newPrice = cartDetail.getTotalPrice();

        if (!checkBox.isSelected()) newPrice *= -1;
        cartDetail.getCart().totalPriceUpdate(newPrice);

        cartController.refreshBill();
    }

    public HBox getCartDetailPane() {
        return hboxContainer;
    }

    public CartDetail getCartDetail() {
        return cartDetail;
    }
}
