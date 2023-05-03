package com.groupproject.controller.component;

import com.groupproject.controller.page.HomeController;
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


    // @Override
    // public void initialize(URL url, ResourceBundle resourceBundle) {
    //     searchField.setOnKeyPressed(keyEvent -> {
    //         if(keyEvent.getCode().toString().equals("ENTER")){
    //             toHomeWithSearch(null);
    //         }
    //     });
    // }

    public void toCart(ActionEvent event){
        System.out.println("to cart");
    }

    public void toDeposit(ActionEvent event){
        homeController.setPageContent(PathHandler.getPageUserDeposit());
        // System.out.println("to deposit");
    }


}
