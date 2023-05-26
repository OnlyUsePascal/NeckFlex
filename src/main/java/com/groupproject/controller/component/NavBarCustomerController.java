package com.groupproject.controller.component;

import com.groupproject.controller.ViewHandler;
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
        super.initialize(url, resourceBundle);
        initSearchField();
    }

    public void initSearchField(){
        searchField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().toString().equals("ENTER")){
                toPageItemAllWithSearch(null);
            }
        });
    }

    public String getSearchText(){
        return searchField.getText();
    }

    public void clearSearchText(){
        searchField.clear();
    }

    // --- navigation ---
    public void toPageCart(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageCart());
    }

    public void toPageDeposit(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageUserDeposit());
    }

    public void toPageItemAllWithSearch(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageItemAll());
    }


}
