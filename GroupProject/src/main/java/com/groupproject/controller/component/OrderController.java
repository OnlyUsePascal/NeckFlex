package com.groupproject.controller.component;

import com.groupproject.controller.page.UserRecordController;
import com.groupproject.entity.generic.Order;
import com.groupproject.entity.generic.OrderDetail;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    Order order;
    ArrayList<OrderDetailController> orderDetailControllerList;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        orderDetailControllerList = new ArrayList<>();
    }

    public void setData(Order order){
        this.order = order;
    }

    public void addOrderDetailPane(OrderDetail orderDetail){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getComponentOrderDetail()));
            AnchorPane pane = loader.load();
            OrderDetailController orderDetailController = loader.getController();

            orderDetailContainer.getChildren().add(pane);
            orderDetailController.setDataFromOrderDetail(orderDetail);
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

        ViewHandler.userRecordControllerGet().refreshPage();
    }

}
