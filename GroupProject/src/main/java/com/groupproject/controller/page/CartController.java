package com.groupproject.controller.page;

import com.groupproject.controller.component.CartDetailController;
import com.groupproject.entity.Constant.ConstantOrder;
import com.groupproject.entity.generic.Account;
import com.groupproject.entity.generic.Cart;
import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.generic.ItemDvd;
import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    @FXML
    VBox itemContainer;
    @FXML
    Label billTotalPriceBox;
    @FXML
    Label userBalance;
    @FXML
    Label userBalancePoint;
    @FXML
    Label statusBox;

    private double billTotalPrice = 0;
    private Cart cart;
    private Account cartOwner;
    private ArrayList<CartDetailController> cartDetailControllerList = new ArrayList<>();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cart = ShopSystem.getCart();
        cartOwner = ShopSystem.getCurrentUser();

        //show cart detail
        for (CartDetail cartDetail : cart.getcartDetailList()){
            showCartDetail(cartDetail);
        }
        refreshBill();
        setDataOwner();

        // System.out.println(cartDetailControllerList);
    }

    public void setDataOwner(){
        userBalance.setText("$" + cartOwner.getBalance());
        userBalancePoint.setText(cartOwner.getRewardPoint() + " points");
    }

    public void showCartDetail(CartDetail cartDetail){
        try {
            FXMLLoader cartItemLoader = new FXMLLoader(getClass().getResource(PathHandler.getComponentCartDetail()));
            HBox cartDetailPane = cartItemLoader.load();

            CartDetailController cartDetailController = cartItemLoader.getController();
            cartDetailController.setData(cartDetail);
            cartDetailController.setCartController(this);

            itemContainer.getChildren().add(cartDetailPane);
            cartDetailControllerList.add(cartDetailController);
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    public void refreshBill(){
        billTotalPriceBox.setText("$" + cart.getTotalPrice());
    }

    public void removeCartDetail(CartDetailController cartDetailController){
        CartDetail cartDetail = cartDetailController.getCartDetail();
        HBox cartItemPane = cartDetailController.getCartDetailPane();

        itemContainer.getChildren().remove(cartItemPane);
        cart.removeCartDetail(cartDetail);
        cartDetailControllerList.remove(cartDetailController);

        refreshBill();
        // System.out.println(cartDetailControllerList);
    }

    public void checkout(ActionEvent event){
        Button btn = (Button) event.getSource();
        ConstantOrder.OrderStatus status = cart.checkout(btn.getId().equals("payCash"));

        switch (status){
            case ACCEPTED -> {
                statusBox.setText("Checkout success");

                //make order
                ShopSystem.makeOrder();

                //wipe cart
                for (int i = cartDetailControllerList.size() - 1; i >= 0; i--){
                    CartDetailController cartDetailController = cartDetailControllerList.get(i);
                    removeCartDetail(cartDetailController);
                }

            } case INSUFFICIENT_BALANCE -> {
                statusBox.setText("Insufficient balance");
            } case INSUFFICIENT_POINT -> {
                statusBox.setText("Insufficient point");
            }
        }
    }

}
