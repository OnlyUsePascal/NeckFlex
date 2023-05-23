package com.groupproject.controller.component;

import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.net.URL;
import java.util.ResourceBundle;


public class NavBarCustomerController extends NavBarController {
    @FXML
    private TextField searchField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.setNavBarController(this);
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

    public void toCart(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageCart());
    }

    public void toDeposit(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageUserDeposit());
    }

    public void toHomeWithSearch(ActionEvent event){
        System.out.println("To home with search");
    }


}
