package com.groupproject.controller.component;

import com.groupproject.entity.generic.Order;
import com.groupproject.entity.generic.OrderDetail;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    VBox orderDetailContainer;
    @FXML
    CheckBox checkBox;
    @FXML
    Button btn;
    @FXML
    HBox orderHeader;
    @FXML
    VBox loadingScreen;
    @FXML
    AnchorPane orderPane;

    Order order;
    ArrayList<OrderDetailController> orderDetailControllerList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderDetailControllerList = new ArrayList<>();
    }

    public void setData(Order order, boolean isReturned) {
        //init
        this.order = order;
        toggleInteractiveNodes(isReturned);

        //load
        loadingScreen.setManaged(true);
        ViewHandler.toggleNode(loadingScreen, true);

        new Thread(() -> {
            //get order detail pane list
            ArrayList<OrderDetail> orderDetailList;
            if (isReturned) {
                orderDetailList = order.getReturnedOrderDetailList();
            } else {
                orderDetailList = order.getRentingOrderDetailList();
            }

            ArrayList<AnchorPane> orderDetailPaneList = new ArrayList<>();
            for (OrderDetail orderDetail : orderDetailList){
                orderDetailPaneList.add(getOrderDetailPane(orderDetail));
            }

            ViewHandler.fakeLoading();

            //add to order pane
            Platform.runLater(() -> {
                if (orderDetailPaneList.isEmpty()) {
                    ViewHandler.toggleNode(orderPane, false);
                    orderPane.setManaged(false);
                    return;
                }

                loadingScreen.setManaged(false);
                ViewHandler.toggleNode(loadingScreen, false);

                orderDetailContainer.getChildren().addAll(orderDetailPaneList);
            });
        }).start();
    }

    public void toggleInteractiveNodes(boolean status) {
        if (!status) {
            ViewHandler.toggleNode(btn, true);
            ViewHandler.toggleNode(checkBox, true);
        } else {
            ViewHandler.toggleNode(btn, false);
            ViewHandler.toggleNode(checkBox, false);
        }
    }

    public AnchorPane getOrderDetailPane(OrderDetail orderDetail) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getComponentOrderDetail()));
            AnchorPane pane = loader.load();
            OrderDetailController orderDetailController = loader.getController();

            orderDetailController.setData(orderDetail);
            orderDetailControllerList.add(orderDetailController);

            return pane;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void setCheckBoxAll(ActionEvent event) {
        CheckBox btn = (CheckBox) event.getSource();
        boolean status = btn.isSelected();

        for (OrderDetailController orderDetailController : orderDetailControllerList) {
            orderDetailController.setCheckBox(status);
        }
    }

    public void returnItemAll(ActionEvent event) {
        int sz = orderDetailControllerList.size();
        for (int i = sz - 1; i >= 0; i--) {
            if (orderDetailControllerList.get(i).getCheckBox()) {
                orderDetailControllerList.get(i).returnItem();
                orderDetailControllerList.remove(i);
            }
        }

        ViewHandler.getUserRecordController().refreshPage();
    }

}
