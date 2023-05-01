package com.groupproject;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

        @Override
        public void initialize(URL url, ResourceBundle resourceBundle) {
            for (int i = 0 ; i < 3; i++) {
                String filePath = "ShoppingItem.fxml";
                try {
                    FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));

                    AnchorPane item = fxmlLoader.load();
                    ShoppingItemController itemController = fxmlLoader.getController();

                    itemController.setter("lol", 123.0, 1);
                    items.add(fxmlLoader);
                    itemBox.getChildren().add(item);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
