package com.groupproject.toolkit;

import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;

public class SetterFile {
    public static void setAnchorPane(AnchorPane frame, Node node){
        // if (true) {
        if (frame.getChildren().size() == 0) {
            frame.getChildren().add(node);
        } else {
            frame.getChildren().set(0, node);
        }
    }
}
