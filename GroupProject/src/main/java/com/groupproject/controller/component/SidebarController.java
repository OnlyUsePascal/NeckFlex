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
        initTransition();
    }

    public boolean isOpen(){
        return menuIsOpen;
    }

    public void initTransition(){
        double duration = 0.3;
        menuToOpen = new TranslateTransition(Duration.seconds(duration), menuPane);
        menuToOpen.setByX(+300);

        menuToClose = new TranslateTransition(Duration.seconds(duration), menuPane);
        menuToClose.setByX(-300);
    }

    public void menuActive(ActionEvent event){
        if (event != null) event.consume();

        if (menuIsOpen){
            menuIsOpen = false;
            menuToClose.play();
        } else {
            menuIsOpen = true;
            menuToOpen.play();
        }
    }

    public void toHome(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageItemTrending());
        menuActive(null);
    }

    public void toItemAll(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageItemAll());
        menuActive(null);
    }


}
