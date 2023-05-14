package com.groupproject.controller.component;

import com.groupproject.controller.page.UserRecordController;
import com.groupproject.entity.generic.OrderDetail;
import com.groupproject.entity.runtime.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailController implements Initializable {
    @FXML
    Label itemTitle;
    @FXML
    Label itemTotalPrice;
    @FXML
    Label itemAmount;
    @FXML
    CheckBox checkBox;

    OrderDetail orderDetail;
    // UserRecordController userRecordController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setDataFromOrderDetail(OrderDetail orderDetail){
        this.orderDetail = orderDetail;

        itemTitle.setText(orderDetail.getItem().getTitle());
        itemTotalPrice.setText(String.valueOf(orderDetail.getPrice()));
        itemAmount.setText(String.valueOf(orderDetail.isReturned()));
    }

    public void returnItem(ActionEvent event){
        orderDetail.setReturned();
        ViewHandler.userRecordControllerGet().refreshPage();
    }

    public void returnItem(){
        orderDetail.setReturned();

    }

    public void setCheckBox(boolean status){
        checkBox.setSelected(status);
    }

    public boolean getCheckBox(){
        return checkBox.isSelected();
    }
}
