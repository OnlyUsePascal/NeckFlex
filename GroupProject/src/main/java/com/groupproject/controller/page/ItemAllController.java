package com.groupproject.controller.page;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.HBox;

public class ItemAllController implements Initializable {
    @FXML
    HBox itemPage;

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        itemPage.setStyle("-fx-background-color: #f2f2f2");
    }

    public void moveItemTile(ActionEvent event){
        System.out.println("move item tile");
    }
}
