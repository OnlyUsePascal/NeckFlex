package com.groupproject.toolkit;

import com.groupproject.entity.runtime.ShopSystem;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.File;
import java.io.IOException;

public class ObjectHandler {
    //=========== CHECK ============
    static public boolean checkStringCharacterOnly(String str){
        // System.out.println(str);
        return str.matches("[a-zA-Z ]+");
    }

    static public boolean checkStringNumberOnly(String str){
        // System.out.println(str);
        return str.matches("[0-9]+");
    }

    static public boolean checkStringGeneral(String str){
        // System.out.println(str);
        return !str.matches(".*\\|.*") && str.length() != 0;
    }

    //=========== GET ============
    static public File getFile(String url){
        System.out.println(url);
        File file = new File(url);
        if (file.exists()) {
            System.out.println("File found");
            return file;
        }
        System.out.println("File not found:");
        return null;
    }

    static public Image getImage(String url){
        try {
            Image img = new Image(ObjectHandler.class.getResourceAsStream(PathHandler.getMediaImage(url)));
            return img;
        } catch (Exception err){
            err.printStackTrace();
            return null;
        }
    }

    static public double getDoubleRound(double num){
        return Math.round(num * 100.0) / 100.0;
    }

    static public Popup getPopup(AnchorPane pane, EventHandler<WindowEvent> popupOnClose){
        Popup popup = new Popup();
        popup.setAutoHide(true);

        Stage window = ShopSystem.getCurrentStage();
        double x = window.getX() + (window.getWidth() - popup.getWidth()) / 2;
        double y = window.getY() + (window.getHeight() - popup.getHeight()) / 2;
        popup.setX(x);
        popup.setY(y);

        popup.getContent().add(pane);
        if (popupOnClose != null) popup.setOnHidden(popupOnClose);

        return popup;
    }

    //======= SET ==========
    static public void setAnchorPane(AnchorPane frame, Node node){
        // if (true) {
        if (frame.getChildren().size() == 0) {
            frame.getChildren().add(node);
        } else {
            frame.getChildren().set(0, node);
        }
    }

    static public void setAnchorPane(AnchorPane frame, String url){
        FXMLLoader fxmlLoader = new FXMLLoader(ObjectHandler.class.getResource(url));
        try {
            AnchorPane node = (AnchorPane) fxmlLoader.load();
            setAnchorPane(frame, node);
        } catch (Exception err){
            err.printStackTrace();
        }
    }

    static public void setScene(Scene scene, String url){
        FXMLLoader loader = new FXMLLoader(ObjectHandler.class.getResource(url));
        try {
            scene.setRoot(loader.load());
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    static public void setScrollLock(ScrollPane scrollPane){
        scrollPane.addEventFilter(ScrollEvent.SCROLL,new EventHandler<ScrollEvent>() {
            @Override
            public void handle(ScrollEvent event) {
                if (event.getDeltaX() != 0) {
                    event.consume();
                }
            }
        });
    }
}
