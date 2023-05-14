package com.groupproject.controller.page;

import com.groupproject.controller.component.CartDetailController;
import com.groupproject.entity.Constant.ConstantOrder;
import com.groupproject.entity.generic.Account;
import com.groupproject.entity.generic.Cart;
import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CartController implements Initializable {
    @FXML
    VBox cartDetailContainer;
    @FXML
    Label billTotalPriceBox;
    @FXML
    Label userBalance;
    @FXML
    Label userBalancePoint;
    @FXML
    Label statusBox;
    @FXML
    RadioButton rent7Day;
    @FXML
    Button payPoint;

    private Cart cart;
    private Account cartOwner;
    private ArrayList<CartDetailController> cartDetailControllerList = new ArrayList<>();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cart = EntityHandler.cartGet();
        cartOwner = EntityHandler.currentUserGet();

        initPayment();
        refreshPage();
    }

    public void initPayment(){
        if (EntityHandler.currentUserGet().isGuest()){
            rent7Day.setDisable(true);
            payPoint.setDisable(true);
        } else {
            rent7Day.setDisable(false);
            if (EntityHandler.currentUserGet().isVIP()){
                payPoint.setDisable(false);
            }
        }
    }

    public void refreshPage(){
        refreshCart();
        refreshBill();
    }

    public void refreshCart(){
        //reset
        cartDetailContainer.getChildren().clear();
        cartDetailControllerList = new ArrayList<>();

        //add
        for (CartDetail cartDetail : cart.getcartDetailList()){
            try {
                FXMLLoader cartItemLoader = new FXMLLoader(getClass().getResource(PathHandler.getComponentCartDetail()));
                HBox cartDetailPane = cartItemLoader.load();
                CartDetailController cartDetailController = cartItemLoader.getController();

                //set data
                cartDetailController.setData(cartDetail);
                cartDetailController.setCartController(this);

                //add to pane
                cartDetailContainer.getChildren().add(cartDetailPane);
                cartDetailControllerList.add(cartDetailController);
            } catch (IOException err){
                err.printStackTrace();
            }
        }
    }

    public void refreshBill(){
        //price
        billTotalPriceBox.setText("$" + cart.getTotalPrice());

        //balance
        userBalance.setText("$" + cartOwner.getBalance());
        userBalancePoint.setText(cartOwner.getRewardPoint() + "");
    }

    public void checkout(ActionEvent event){
        Button btn = (Button) event.getSource();
        ConstantOrder.OrderStatus status = cart.checkout(btn.getId().equals("payCash"));

        switch (status){
            case ACCEPTED -> {
                statusBox.setText("Checkout success");

                EntityHandler.orderAdd();
                refreshPage();

            } case INSUFFICIENT_BALANCE -> {
                statusBox.setText("Insufficient balance");
            } case INSUFFICIENT_POINT -> {
                statusBox.setText("Insufficient point");
            }
        }
    }

}
