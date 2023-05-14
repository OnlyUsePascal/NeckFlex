package com.groupproject.controller.page;

import com.groupproject.controller.component.OrderController;
import com.groupproject.entity.generic.Order;
import com.groupproject.entity.generic.OrderDetail;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserRecordController implements Initializable {
    @FXML
    VBox rentingContainer;
    @FXML
    VBox returnedContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.userRecordControllerSet(this);
        refreshPage();

    }

    public void refreshPage(){
        rentingContainer.getChildren().clear();
        returnedContainer.getChildren().clear();

        for (Order order: EntityHandler.currentUserGet().getOrderList()){
            ArrayList<OrderDetail> renting = new ArrayList<>();
            ArrayList<OrderDetail> returned = new ArrayList<>();

            for (OrderDetail orderDetail : order.getOrderDetailList()){
                if (!orderDetail.isReturned()){
                    renting.add(orderDetail);
                } else {
                    returned.add(orderDetail);
                }
            }

            // //renting
            addOrderPane(order, renting, rentingContainer);
            addOrderPane(order, returned, returnedContainer);
        }
    }


    public Rectangle getStartLine(){
        Rectangle startLine = new Rectangle();
        startLine.setHeight(50);
        startLine.setWidth(600);
        startLine.setFill(Color.TEAL);

        return startLine;
    }

    public void addOrderPane(Order order, ArrayList<OrderDetail> orderDetailList, VBox container){
        if (orderDetailList.isEmpty()) return;

        FXMLLoader orderLoader = new FXMLLoader(getClass().getResource(PathHandler.getComponentOrder()));
        try {
            AnchorPane orderPane = orderLoader.load();
            OrderController orderController = orderLoader.getController();

            orderController.setData(order);
            for (OrderDetail orderDetail : orderDetailList){
                orderController.addOrderDetailPane(orderDetail);
            }

            container.getChildren().add(0, orderPane);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }




}
