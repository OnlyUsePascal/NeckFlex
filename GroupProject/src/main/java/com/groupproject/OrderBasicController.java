package com.groupproject;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class OrderBasicController {
    @FXML
    private Label date;

    @FXML
    private Label type;

    @FXML
    private Button returnButton;

    private Label outerResponseLabel;
    private Order outerOrder;
    private BiConsumer<Order, AnchorPane> outerReturnOrderFunc;
    private AnchorPane outerPlane;

    public void setDate(String date) {
        this.date.setText(date);
    }

    public void setType(String type) {
        this.type.setText(type);
    }

    public void setOuterResponseLabel(Label outerResponseLabel) {
        this.outerResponseLabel = outerResponseLabel;
    }

    public void setOuterOrder(Order outerOrder) {
        this.outerOrder = outerOrder;
    }

    public void setOuterReturnOrderFunc(BiConsumer<Order, AnchorPane> outerReturnOrderFunc) {
        this.outerReturnOrderFunc = outerReturnOrderFunc;
    }

    public AnchorPane getOuterPlane() {
        return outerPlane;
    }

    public void setOuterPlane(AnchorPane outerPlane) {
        this.outerPlane = outerPlane;
    }

    public void returnOrder(Event event) {
        if (this.outerOrder.checkOvertime() < 0) {
            outerResponseLabel.setText("success");
            outerResponseLabel.setTextFill(Color.GREEN);
        } else {
            outerResponseLabel.setText("not success");
            outerResponseLabel.setTextFill(Color.RED);
        }

        outerReturnOrderFunc.accept(outerOrder, outerPlane);
    }
}
