package com.groupproject.controller.page;

import com.groupproject.controller.component.NavBarCustomerController;
import com.groupproject.controller.component.SidebarController;
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
        sidebarPanel.setTranslateX(-300);
        // setPageContent(PathHandler.getPageItemTrending());
        setPageContent(PathHandler.getPageUserRecord());
        setSidebar(PathHandler.getComponentSidebar());
        setNavBar(PathHandler.getComponentNavBar());
    }

    public void setSidebar(String url){
        FXMLLoader sidebarLoader = new FXMLLoader(getClass().getResource(url));
        try {
            AnchorPane sidebar = (AnchorPane) sidebarLoader.load();
            sidebarController = sidebarLoader.getController();

            ObjectHandler.setAnchorPane(sidebarPanel, sidebar);
            sidebarController.setHomeController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPageContent(String url) {
        ObjectHandler.setAnchorPane(pageContent, url);
    }

    public void setNavBar(String url){
        FXMLLoader navBarLoader = new FXMLLoader(getClass().getResource(url));
        try {
            AnchorPane navBarPane = navBarLoader.load();
            navBarCustomerController = navBarLoader.getController();

            ObjectHandler.setAnchorPane(navBar, navBarPane);
            navBarCustomerController.setHomeController(this);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public AnchorPane getPageContentFrame(){
        return pageContent;
    }

    public void setMenuActive(ActionEvent event){
        sidebarController.menuActive(event);
    }


}
