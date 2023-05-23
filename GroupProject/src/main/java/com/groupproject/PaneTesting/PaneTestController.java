package com.groupproject.PaneTesting;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class PaneTestController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private AnchorPane root1;
    @FXML
    private Label message;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {



        root.addEventHandler(MouseEvent.MOUSE_CLICKED, event ->{
            double x = event.getX();
            double y = event.getY();
            if (root1.getBoundsInParent().contains(x,y)){
                message.setText("Inside");
            }else{
                message.setText("Outside");
            }

        });
    }
}
