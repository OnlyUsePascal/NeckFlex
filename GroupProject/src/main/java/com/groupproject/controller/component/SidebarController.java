package com.groupproject.controller.component;

import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    @FXML
    AnchorPane menuPane;
    boolean menuIsOpen = false;
    TranslateTransition menuToOpen, menuToClose;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        menuToOpen = new TranslateTransition(Duration.seconds(0.5), menuPane);
        menuToOpen.setByX(+300);

        menuToClose = new TranslateTransition(Duration.seconds(0.5), menuPane);
        menuToClose.setByX(-300);

        System.out.println(menuPane.getProperties().get("foo"));

    }

    public void menuActive(ActionEvent event){
        System.out.println("Menu active");
        if (menuIsOpen){
            menuIsOpen = false;
            menuToClose.play();
        } else {
            menuIsOpen = true;
            menuToOpen.play();
        }
    }

    public void changeContent(ActionEvent event){
        Button btn = (Button) event.getSource();
        System.out.println(btn.getText());
    }
}
