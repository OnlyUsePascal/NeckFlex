package com.groupproject.controller.component;

import com.groupproject.controller.page.HomeController;
import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.ResourceBundle;


public class NavBarCustomerController extends NavBarController {
    @FXML
    TextField searchField;

    public void toCart(ActionEvent event){
        ShopSystem.setPageContent(PathHandler.getPageCart());
    }

    public void toDeposit(ActionEvent event){
        ShopSystem.setPageContent(PathHandler.getPageUserDeposit());
    }


}
