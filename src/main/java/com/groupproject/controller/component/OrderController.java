package com.groupproject.controller.component;

import com.groupproject.entity.generic.Order;
import com.groupproject.entity.generic.OrderDetail;
import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class OrderController implements Initializable {
    @FXML
    private VBox orderDetailContainer;
    @FXML
    private CheckBox checkBox;
    @FXML
    private Button btn;
    @FXML
    private HBox orderHeader;
    @FXML
    private VBox loadingScreen;
    // @FXML
    // private AnchorPane orderPane;
    @FXML
    private Label dateBox;

    private Order order;
    private ArrayList<OrderDetailController> orderDetailControllerList;

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

            ArrayList<Node> orderDetailPaneList = new ArrayList<>();
            for (OrderDetail orderDetail : orderDetailList){
                orderDetailPaneList.add(getOrderDetailPane(orderDetail));
            }

            // ViewHandler.fakeLoading();

            //add to order pane
            Platform.runLater(() -> {
                if (orderDetailPaneList.isEmpty()) {
                    ViewHandler.toggleNode(orderDetailContainer, false);
                    orderDetailContainer.setManaged(false);
                    return;
                }

                loadingScreen.setManaged(false);
                ViewHandler.toggleNode(loadingScreen, false);

                if (isReturned){
                    dateBox.setText("Order made: " + order.getDateString());
                } else {
                    dateBox.setText("Deadline: " + order.getDateEndString());
                    if (order.isLate()) {
                        dateBox.setStyle("-fx-text-fill: red");
                    }
                }

                orderDetailContainer.getChildren().addAll(orderDetailPaneList);
            });
        }).start();
    }

    public void toggleInteractiveNodes(boolean status) {
        btn.setDisable(status);
        checkBox.setDisable(status);
    }

    public Node getOrderDetailPane(OrderDetail orderDetail) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getComponentOrderDetail()));
            Node pane = loader.load();
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
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Return all items?");
        alert.setContentText("Are you sure you want to return all items?");

        if (alert.showAndWait().get() == ButtonType.OK) {
            int sz = orderDetailControllerList.size();
            for (int i = sz - 1; i >= 0; i--) {
                if (orderDetailControllerList.get(i).getCheckBox()) {
                    orderDetailControllerList.get(i).returnItemMulti();
                    orderDetailControllerList.remove(i);
                }
            }

            ViewHandler.getUserRecordController().refreshPage();
        }
    }

}
