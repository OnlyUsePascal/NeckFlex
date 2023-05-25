package com.groupproject.controller.component;

import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;

public class SidebarController implements Initializable {

    @FXML
    private AnchorPane menuPane;

    private boolean menuIsOpen = false;
    private TranslateTransition menuToOpen, menuToClose;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.setSidebarController(this);
    }

    public boolean isOpen(){
        return menuIsOpen;
    }

    public void menuActive(){
        ViewHandler.setMenuActive();
    }

    public void toItemAll(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageItemAll());
        menuActive();
    }


}
