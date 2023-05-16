package com.groupproject.controller.component;

import com.groupproject.entity.generic.Order;
import com.groupproject.entity.generic.OrderDetail;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
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

    Order order;
    ArrayList<OrderDetailController> orderDetailControllerList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderDetailControllerList = new ArrayList<>();
    }

    public void setData(Order order, ArrayList<OrderDetail> orderDetailList){
        this.order = order;
        for (OrderDetail orderDetail : orderDetailList){
            addOrderDetailPane(orderDetail);
        }

        checkIsReturned(orderDetailList.get(0));
    }

    public void checkIsReturned(OrderDetail orderDetail){
        if (orderDetail.isReturned()){
            orderHeader.getChildren().remove(btn);
            orderHeader.getChildren().remove(checkBox);
        }
    }

    public void addOrderDetailPane(OrderDetail orderDetail){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getComponentOrderDetail()));
            AnchorPane pane = loader.load();
            OrderDetailController orderDetailController = loader.getController();

            orderDetailContainer.getChildren().add(pane);
            orderDetailController.setData(orderDetail);
            orderDetailControllerList.add(orderDetailController);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setCheckBoxAll(ActionEvent event){
        CheckBox btn = (CheckBox) event.getSource();
        boolean status = btn.isSelected();

        for (OrderDetailController orderDetailController : orderDetailControllerList){
            orderDetailController.setCheckBox(status);
        }
    }

    public void returnItemAll(ActionEvent event){
        int sz = orderDetailControllerList.size();
        for (int i = sz-1; i >= 0; i--){
            if (orderDetailControllerList.get(i).getCheckBox()){
                orderDetailControllerList.get(i).returnItem();
                orderDetailControllerList.remove(i);
            }
        }

        ViewHandler.getUserRecordController().refreshPage();
    }

}
