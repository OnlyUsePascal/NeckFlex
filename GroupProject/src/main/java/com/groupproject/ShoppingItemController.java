package com.groupproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;

public class ShoppingItemController {
    @FXML
    private CheckBox isIncluded;
    @FXML
    private Label itemName;
    @FXML
    private Button deleteButton;
    @FXML
    private Button incButton;
    @FXML
    private Button decButton;
    @FXML
    private TextField amount;
    @FXML
    private Label price;

    private Double priceValue;
    public void setter(String itemName, Double price, Integer amount) {
        this.itemName.setText(itemName);
        this.priceValue = price;
        this.amount.setText(amount.toString());

        Double p = price * amount;
        this.price.setText(p.toString());
    }

    public void incAmount(ActionEvent e) {
        Integer incremented = Integer.parseInt(this.amount.getText()) + 1;
        Double p = priceValue * incremented;

        this.amount.setText(incremented.toString());
        this.price.setText(p.toString());
    }

    public void decAmount(ActionEvent e) {
        Integer decremented = Integer.parseInt(this.amount.getText()) - 1;

        if (decremented > 0) {
            Double p = priceValue * decremented;

            this.amount.setText(decremented.toString());
            this.price.setText(p.toString());
        }
    }
}