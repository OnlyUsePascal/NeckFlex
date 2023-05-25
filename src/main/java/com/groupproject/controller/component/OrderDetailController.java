package com.groupproject.controller.component;

import com.groupproject.entity.generic.Item;
import com.groupproject.entity.generic.OrderDetail;
import com.groupproject.controller.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;

import java.net.URL;
import java.util.ResourceBundle;

public class OrderDetailController implements Initializable {
    @FXML
    private Label titleBox;
    // @FXML
    // private Label priceBox;
    @FXML
    private Label amountBox;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Button btn;
    @FXML
    private HBox container;
    @FXML
    private Rectangle imgFrame;

    private OrderDetail orderDetail;
    // UserRecordController userRecordController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
    }

    public void setData(OrderDetail orderDetail) {
        this.orderDetail = orderDetail;

        titleBox.setText("     " + orderDetail.getItem().getTitle());
        amountBox.setText("Renting: " + orderDetail.getQuantity());
        // priceBox.setText("$" + orderDetail.getPrice());

        ViewHandler.fillShapeWithImage(orderDetail.getItem().getImgName(), imgFrame);
        checkIsReturned();
    }

    public void checkIsReturned() {
        if (orderDetail.isReturned()) {
            btn.setDisable(true);
            checkBox.setDisable(true);
            amountBox.setText("Rented: " + orderDetail.getQuantity());
        }
    }

    public void returnItem(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Return your item?");
        alert.setContentText("Are you sure you want to return this item?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            orderDetail.setReturned();
        }
        ViewHandler.getUserRecordController().refreshPage();
    }

    public void returnItemMulti() {
        orderDetail.setReturned();
    }

    public void setCheckBox(boolean status) {
        checkBox.setSelected(status);
    }

    public boolean getCheckBox() {
        return checkBox.isSelected();
    }
}
