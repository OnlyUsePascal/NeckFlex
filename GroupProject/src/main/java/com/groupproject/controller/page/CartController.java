package com.groupproject.controller.page;

import com.groupproject.controller.component.CartDetailController;
import com.groupproject.entity.Constant.ConstantOrder;
import com.groupproject.entity.generic.Account;
import com.groupproject.entity.generic.Cart;
import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.application.Platform;
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
    private VBox cartDetailContainer;
    @FXML
    private Label billTotalPriceBox;
    @FXML
    private Label userBalance;
    @FXML
    private Label userBalancePoint;
    @FXML
    private Label statusBox;
    @FXML
    private RadioButton rent7Day;
    @FXML
    private VBox loadingScreen;
    @FXML
    private Button payCash;
    @FXML
    private Button payPoint;

    private Cart cart;
    private Account cartOwner;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cartOwner = EntityHandler.getCurrentUser();
        cart = cartOwner.getCart();
        cart.refreshCart();

        initPayment();
        refreshCart();
    }

    public void initPayment(){
        rent7Day.setDisable(true);
        payPoint.setDisable(true);

        if (!EntityHandler.getCurrentUser().isGuest()){
            rent7Day.setDisable(false);
        }
        if (EntityHandler.getCurrentUser().isVIP()){
            payPoint.setDisable(false);
        }
    }

    public void refreshCart(){
        //reset
        cartDetailContainer.getChildren().clear();
        ViewHandler.toggleNode(loadingScreen, true);

        //add
        new Thread(() -> {
            ArrayList<HBox> cartDetailPaneList = new ArrayList<>();
            for (CartDetail cartDetail : cart.getCartDetailList()){
                cartDetailPaneList.add(getCartDetailPane(cartDetail));
            }

            ViewHandler.fakeLoading();

            Platform.runLater(() -> {
                cartDetailContainer.getChildren().addAll(cartDetailPaneList);
                refreshBill(cart.getCartDetailList().isEmpty());
                ViewHandler.toggleNode(loadingScreen, false);
            });
        }).start();
    }

    public HBox getCartDetailPane(CartDetail cartDetail){
        try {
            FXMLLoader cartItemLoader = new FXMLLoader(getClass().getResource(PathHandler.getComponentCartDetail()));
            HBox cartDetailPane = cartItemLoader.load();
            CartDetailController cartDetailController = cartItemLoader.getController();

            //set data
            cartDetailController.setData(cartDetail);
            cartDetailController.setCartController(this);

            return cartDetailPane;
        } catch (IOException err){
            err.printStackTrace();
            return null;
        }
    }

    public void refreshBill(boolean cartEmpty){
        //price
        billTotalPriceBox.setText("$" + cart.getTotalPrice());

        //balance
        userBalance.setText("$" + cartOwner.getBalance());
        userBalancePoint.setText(cartOwner.getRewardPoint() + "");

        //button
        payCash.setDisable(cartEmpty);
    }

    public void checkout(ActionEvent event){
        Button btn = (Button) event.getSource();
        ConstantOrder.OrderStatus status = cart.checkout(btn.getId().equals("payCash"));

        System.out.println(status);
        switch (status){
            case ACCEPTED -> {
                statusBox.setText("Checkout success");

                EntityHandler.addOrder();
                refreshCart();

            } case INSUFFICIENT_BALANCE -> {
                statusBox.setText("Insufficient balance");
            } case INSUFFICIENT_POINT -> {
                statusBox.setText("Insufficient point");
            } case LIMITED_AMOUNT -> {
                statusBox.setText("You can only hold " + ConstantOrder.rentingLimit + " items");
            }
        }
    }

}
