package com.groupproject.controller.component;

import com.groupproject.controller.page.HomeController;
import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.toolkit.PathHandler;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.nio.file.Path;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    @FXML
    AnchorPane menuPane;

    boolean menuIsOpen = false;
    TranslateTransition menuToOpen, menuToClose;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShopSystem.setSidebarController(this);
        initTransition();
    }

    public void initTransition(){
        double duration = 0.3;
        menuToOpen = new TranslateTransition(Duration.seconds(duration), menuPane);
        menuToOpen.setByX(+300);

        menuToClose = new TranslateTransition(Duration.seconds(duration), menuPane);
        menuToClose.setByX(-300);
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

    public void toHome(ActionEvent event){
        ShopSystem.setPageContent(PathHandler.getPageItemTrending());
        menuActive(null);
    }

    public void toItemAll(ActionEvent event){
        ShopSystem.setPageContent(PathHandler.getPageItemAll());
        menuActive(null);
    }


}
