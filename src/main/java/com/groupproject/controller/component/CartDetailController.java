package com.groupproject.controller.component;

import com.groupproject.controller.page.CartController;
import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class CartDetailController implements Initializable {
    @FXML
    private Label titleBox;
    @FXML
    private Label unitPriceBox;
    @FXML
    private Label quantityBox;
    @FXML
    private HBox hboxContainer;
    @FXML
    private Rectangle imgFrame;

    private CartDetail cartDetail;
    private CartController cartController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        AnchorPane.setTopAnchor(hboxContainer, 10.0);
    }

    // --- MAIN ---
    public void updateQuantity(ActionEvent event){
        Button btn = (Button) event.getSource();
        String btnText = btn.getText();

        int newQuantity = cartDetail.getQuantity() + (btnText.equals("+") ? 1 : -1);
        if (cartDetail.setQuantity(newQuantity)){
            cartController.refreshBill(false);

            quantityBox.setText(cartDetail.getQuantity() + "");
        }
    }

    public void removeCartDetail(ActionEvent event){
        EntityHandler.getCart().removeCartDetail(cartDetail);
        cartController.refreshCart();
    }

    // --- BACK ---
    public void setData(CartDetail cartDetail) {
        this.cartDetail = cartDetail;

        titleBox.setText(this.cartDetail.getTitle());
        unitPriceBox.setText("$" + this.cartDetail.getItem().getPrice());
        quantityBox.setText(String.valueOf(this.cartDetail.getQuantity()));
        ViewHandler.fillShapeWithImage(this.cartDetail.getItem().getImgName(), imgFrame);
    }

    public void setCartController(CartController cartController) {
        this.cartController = cartController;
    }
}
