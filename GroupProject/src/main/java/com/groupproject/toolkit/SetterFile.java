package com.groupproject.toolkit;

import javafx.scene.layout.AnchorPane;

public class SetterFile {
    public static void setAnchorPane(AnchorPane frame, String url){
        AnchorPane pageResult = GetterFile.getAnchorPane(url);

        if (true) {
        // if (frame.getChildren().size() == 0) {
            frame.getChildren().add(pageResult);
        } else {
            frame.getChildren().set(0, pageResult);
        }
    }

    // public static void setAnchorPane(AnchorPane frame, Controller String url){
    //     AnchorPane pageResult = GetterFile.getAnchorPane(url);
    //
    //     if (frame.getChildren().isEmpty()) {
    //         frame.getChildren().add(pageResult);
    //     } else {
    //         frame.getChildren().set(0, pageResult);
    //     }
    // }
}
