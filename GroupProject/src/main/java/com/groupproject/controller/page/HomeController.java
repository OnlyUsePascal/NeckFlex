package com.groupproject.controller.page;

import com.groupproject.controller.component.NavBarCustomerController;
import com.groupproject.controller.component.SidebarController;
import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.toolkit.PathHandler;
import com.groupproject.toolkit.ObjectHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    Label labelSample;
    @FXML
    AnchorPane sidebarPanel;
    @FXML
    AnchorPane pageContent;
    @FXML
    AnchorPane navBar;

    SidebarController sidebarController;
    NavBarCustomerController navBarCustomerController;
    ItemAllController itemAllController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ShopSystem.setHomeController(this);

        setPageContent(PathHandler.getPageItemTrending());
        // setPageContent(PathHandler.getPageAdminAccount());
        setSidebar(PathHandler.getComponentSidebar());
        setNavBar(PathHandler.getComponentNavBar());
    }

    public void setSidebar(String url){
        ObjectHandler.setAnchorPane(sidebarPanel, url);
    }

    public void setPageContent(String url) {
        ObjectHandler.setAnchorPane(pageContent, url);
    }

    public void setNavBar(String url){
        sidebarPanel.setTranslateX(-300);
        ObjectHandler.setAnchorPane(navBar, url);
    }
}
