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

    public void mouseClickedTest(MouseEvent mouseEvent) {
        //check if mouse position is inside the root1 or outside root1
        int X = (int) mouseEvent.getSceneX();
        int Y = (int) mouseEvent.getSceneY();
        int root1X = (int) root1.getLayoutX();
        int root1Y = (int) root1.getLayoutY();
        int root1Width = (int) root1.getWidth();
        int root1Height = (int) root1.getHeight();
        if (X >= root1X && X <= root1X + root1Width && Y >= root1Y && Y <= root1Y + root1Height) {
            message.setText("Mouse is inside root1");
        } else {
            message.setText("Mouse is outside root1");
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
