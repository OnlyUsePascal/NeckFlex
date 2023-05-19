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
        ViewHandler.setUserRecordController(this);
        refreshPage();

    }

    public void refreshPage() {
        rentingContainer.getChildren().clear();
        returnedContainer.getChildren().clear();

        // order - renting / returned ->
        for (Order order : EntityHandler.getCurrentUser().getOrderList()) {
            addOrderPane(order, returnedContainer, true);
            addOrderPane(order, rentingContainer, false);
        }
    }

    public void addOrderPane(Order order, VBox orderContainer, boolean isReturned) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getComponentOrder()));
            AnchorPane orderPane = loader.load();
            OrderController orderController = loader.getController();

            orderController.setData(order, isReturned);
            orderContainer.getChildren().add(0, orderPane); // add to start
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
