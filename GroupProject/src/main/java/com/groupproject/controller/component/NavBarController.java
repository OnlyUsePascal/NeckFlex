package com.groupproject.controller.component;

import com.groupproject.entity.runtime.ShopSystem;
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
        ShopSystem.setNavBarController(this);
        initSearchField();
        refreshMenuButtonName();
        // menuButton.setText("Hello, " + CurrentUser.getCurrentUser().getFirstName() + "!");
    }

    public void initSearchField(){
        searchField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().toString().equals("ENTER")){
                toHomeWithSearch(null);
            }
        });
    }

    public void refreshMenuButtonName(){
        menuButton.setText("Hello, " + ShopSystem.getCurrentUser().getFirstName() + "!");
    }


    public void setMenuActive(ActionEvent event){
        ShopSystem.setMenuActive();
    }

    public void toHomeWithSearch(ActionEvent event){
        System.out.println("To home with search");
    }

    public void toHome(ActionEvent event){
        ShopSystem.setPageContent(PathHandler.getPageItemTrending());
    }

    public void toUserProfile(ActionEvent event){
        ShopSystem.setPageContent(PathHandler.getPageUserProfile());
    }

    public void toLogout(ActionEvent event){
        ShopSystem.logOut();
    }

}
