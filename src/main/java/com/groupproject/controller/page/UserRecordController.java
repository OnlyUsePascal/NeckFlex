package com.groupproject.controller.page;

import com.groupproject.controller.component.OrderController;
import com.groupproject.entity.generic.AccountCustomer;
import com.groupproject.entity.generic.Order;
import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class UserRecordController implements Initializable {
    @FXML
    private VBox rentingContainer;
    @FXML
    private VBox returnedContainer;
    @FXML
    private VBox loadingScreen;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.setUserRecordController(this);
        refreshPage();

    }

    // --- MAIN ---
    public void getInstruction(ActionEvent event) {
        FXMLLoader itemLoader = new FXMLLoader(getClass().getResource(PathHandler.getPopupInstructionRecord()));
        try {
            AnchorPane pane = itemLoader.load();

            ViewHandler.getPopup(pane, null);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    // --- BACK ---
    public void refreshPage() {
        rentingContainer.getChildren().clear();
        returnedContainer.getChildren().clear();

        loadingScreen.setVisible(true);
        new Thread(() -> {
            //get data
            ArrayList<Node> returnedList = new ArrayList<>();
            ArrayList<Node> rentingList = new ArrayList<>();

            // ViewHandler.fakeLoading();
            for (Order order : ((AccountCustomer) EntityHandler.getCurrentUser()).getOrderList1()) {
                returnedList.add(0,getOrderPane(order, true));
                rentingList.add(0,getOrderPane(order, false));
            }

            //show
            Platform.runLater(() -> {
                System.out.println("load done");
                returnedContainer.getChildren().addAll(returnedList);
                rentingContainer.getChildren().addAll(rentingList);
                loadingScreen.setVisible(false);
            });
        }).start();
    }

    public Node getOrderPane(Order order, boolean isReturned){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getComponentOrder()));
            Node orderPane = loader.load();
            OrderController orderController = loader.getController();

            orderController.setData(order, isReturned);
            return orderPane;
        } catch (IOException err) {
            err.printStackTrace();
            return null;
        }
    }
}
