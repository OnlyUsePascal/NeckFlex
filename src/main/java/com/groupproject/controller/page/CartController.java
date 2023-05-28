package com.groupproject.controller.page;

import com.groupproject.controller.component.CartDetailController;
import com.groupproject.entity.Constant.ConstantOrder;
import com.groupproject.entity.generic.AccountCustomer;
import com.groupproject.entity.generic.Cart;
import com.groupproject.entity.generic.CartDetail;
import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
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
    private Label messBox;
    @FXML
    private RadioButton rent7Day;
    @FXML
    private VBox loadingScreen;
    @FXML
    private Button payCash;
    @FXML
    private Button payPoint;

    private Cart cart;
    private AccountCustomer cartOwner;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        cartOwner = (AccountCustomer) EntityHandler.getCurrentUser();
        cart = cartOwner.getCart1();
        cart.refreshCart();

        initPayment();
        refreshCart();

        messBox.setText("");
    }

    // --- MAIN ---
    public void checkout(ActionEvent event) {
        Button btn = (Button) event.getSource();
        ConstantOrder.OrderDuration duration = rent7Day.isSelected() ?
                ConstantOrder.OrderDuration.ONE_WEEK : ConstantOrder.OrderDuration.TWO_DAYS;

        ConstantOrder.OrderStatus status = cart.checkout(btn.getId().equals("payCash"), duration);

        // System.out.println(status);
        // base on status
        switch (status) {
            case ACCEPTED -> {
                new Thread(() -> {
                    Platform.runLater(() -> {
                        messBox.setText("Checkout success");
                        refreshCart();
                    });
                }).start();
            }
            case INSUFFICIENT_BALANCE -> {
                messBox.setText("Insufficient balance");
            }
            case INSUFFICIENT_POINT -> {
                messBox.setText("Insufficient point");
            }
            case LIMITED_AMOUNT -> {
                messBox.setText("You can only hold " + ConstantOrder.rentingLimit + " items");
            }
        }
    }

    public void getInstruction(ActionEvent event){
        FXMLLoader itemLoader = new FXMLLoader(getClass().getResource(PathHandler.getPopupInstructionCart()));
        try {
            AnchorPane pane = itemLoader.load();

            ViewHandler.getPopup(pane, null);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    // --- BACK ---
    public void initPayment() {
        rent7Day.setDisable(true);
        payPoint.setDisable(true);

        if (!EntityHandler.getCurrentUser().isGuest()) {
            rent7Day.setDisable(false);
        }
        if (EntityHandler.getCurrentUser().isVIP()) {
            payPoint.setDisable(false);
        }
    }

    public void refreshCart() {
        // reset
        cartDetailContainer.getChildren().clear();
        ViewHandler.toggleNode(loadingScreen, true);

        // add
        new Thread(() -> {
            ArrayList<HBox> cartDetailPaneList = new ArrayList<>();
            for (CartDetail cartDetail : cart.getCartDetailList()) {
                cartDetailPaneList.add(getCartDetailPane(cartDetail));
            }

            // ViewHandler.fakeLoading();

            Platform.runLater(() -> {
                cartDetailContainer.getChildren().addAll(cartDetailPaneList);
                refreshBill(cart.getCartDetailList().isEmpty());
                ViewHandler.toggleNode(loadingScreen, false);
            });
        }).start();
    }

    public HBox getCartDetailPane(CartDetail cartDetail) {
        try {
            FXMLLoader cartItemLoader = new FXMLLoader(getClass().getResource(PathHandler.getComponentCartDetail()));
            HBox cartDetailPane = cartItemLoader.load();
            CartDetailController cartDetailController = cartItemLoader.getController();

            // set data
            cartDetailController.setData(cartDetail);
            cartDetailController.setCartController(this);

            return cartDetailPane;
        } catch (IOException err) {
            err.printStackTrace();
            return null;
        }
    }

    public void refreshBill(boolean cartEmpty) {
        // price
        billTotalPriceBox.setText("$" + cart.getTotalPrice());

        // balance
        userBalance.setText("$" + cartOwner.getBalance1());
        userBalancePoint.setText(cartOwner.getRewardPoint1() + "");

        // button
        payCash.setDisable(cartEmpty);
    }
}
