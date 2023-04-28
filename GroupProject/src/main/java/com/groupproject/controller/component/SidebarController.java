package com.groupproject.controller.component;

import com.groupproject.controller.page.HomeController;
import com.groupproject.toolkit.GetterPath;
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

    HomeController homeController;
    boolean menuIsOpen = false;
    TranslateTransition menuToOpen, menuToClose;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        double duration = 0.3;
        menuToOpen = new TranslateTransition(Duration.seconds(duration), menuPane);
        menuToOpen.setByX(+300);

        menuToClose = new TranslateTransition(Duration.seconds(duration), menuPane);
        menuToClose.setByX(-300);

        // System.out.println(menuPane.getProperties().get("foo"));


    }

    public void menuActive(ActionEvent event){
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
        homeController.changePageContent(GetterPath.getPageBtn(btn.getId()));
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }
}
