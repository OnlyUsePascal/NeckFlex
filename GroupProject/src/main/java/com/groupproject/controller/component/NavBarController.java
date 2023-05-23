package com.groupproject.controller.component;

import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class NavBarController implements Initializable {
    @FXML
    private TextField searchField;
    @FXML
    private MenuButton menuButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.setNavBarController(this);
        refreshMenuButtonName();
    }

    public void refreshMenuButtonName(){
        menuButton.setText("Hello, " + EntityHandler.getCurrentUser().getFirstName() + "!");
    }


    public void setMenuActive(ActionEvent event){
        ViewHandler.setMenuActive(event);
    }


    public void toPageHome(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageItemTrending());
    }

    public void toPageUserProfile(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageUserProfile());
    }

    public void toLogout(ActionEvent event){
        EntityHandler.logOut();
    }

}
