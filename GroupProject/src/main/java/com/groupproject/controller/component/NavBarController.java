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
    TextField searchField;
    @FXML
    MenuButton menuButton;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.navBarControllerSet(this);
        initSearchField();
        refreshMenuButtonName();
    }

    public void initSearchField(){
        searchField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().toString().equals("ENTER")){
                toHomeWithSearch(null);
            }
        });
    }

    public void refreshMenuButtonName(){
        menuButton.setText("Hello, " + EntityHandler.currentUserGet().getFirstName() + "!");
    }


    public void setMenuActive(ActionEvent event){
        ViewHandler.setMenuActive();
    }

    public void toHomeWithSearch(ActionEvent event){
        System.out.println("To home with search");
    }

    public void toHome(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageItemTrending());
    }

    public void toUserProfile(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageUserProfile());
    }

    public void toLogout(ActionEvent event){
        EntityHandler.logOut();
    }

}
