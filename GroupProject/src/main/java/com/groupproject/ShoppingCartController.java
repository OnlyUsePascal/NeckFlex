package com.groupproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ShoppingCartController implements Initializable {
        @FXML
        VBox itemBox;
        ArrayList<FXMLLoader> items = new ArrayList<>();

        @FXML
        private Label totalAmount;

        @FXML
        private Label totalItems;

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            Double totalAmount = 0.0;
            Integer totalItems = 0;

            for (int i = 0 ; i < 3; i++) {
                String filePath = "ShoppingItem.fxml";
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));

                    AnchorPane item = fxmlLoader.load();
                    ShoppingItemController itemController = fxmlLoader.getController();

                    itemController.setter("lol", 123.0, 1);

                    totalAmount += 123.0;
                    totalItems += 1;

                    items.add(fxmlLoader);
                    itemBox.getChildren().add(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            this.totalAmount.setText(totalAmount.toString());
            this.totalItems.setText(totalItems.toString());
        }
    }
