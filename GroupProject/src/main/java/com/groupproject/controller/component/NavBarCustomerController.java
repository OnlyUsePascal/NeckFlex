package com.groupproject.controller.component;

import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;


public class NavBarCustomerController extends NavBarController {
    @FXML
    TextField searchField;

    public void toCart(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageCart());
    }

    public void toDeposit(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageUserDeposit());
    }


}
