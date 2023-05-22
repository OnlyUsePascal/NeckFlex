package com.groupproject.controller.page;

import com.groupproject.controller.component.NavBarCustomerController;
import com.groupproject.controller.component.SidebarController;
import com.groupproject.toolkit.PathHandler;
import com.groupproject.entity.runtime.ViewHandler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    private Label labelSample;
    @FXML
    private AnchorPane sidebarPanel;
    @FXML
    private AnchorPane pageContent;
    @FXML
    private AnchorPane navBar;

    private SidebarController sidebarController;
    private NavBarCustomerController navBarCustomerController;
    private ItemAllController itemAllController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.setHomeController(this);

        setPageContent(PathHandler.getPageItemTrending());
        // setPageContent(PathHandler.getPageUserProfil
        // e());
        setSidebar(PathHandler.getComponentSidebar());
        setNavBar(PathHandler.getComponentNavBar());
    }

    public void setSidebar(String url) {
        ViewHandler.setAnchorPane(sidebarPanel, url);
    }

    public void setPageContent(String url) {
        ViewHandler.setAnchorPane(pageContent, url);
    }

    public void setNavBar(String url) {
        sidebarPanel.setTranslateX(-300);
        ViewHandler.setAnchorPane(navBar, url);
    }

    public AnchorPane getPageContent() {
        return pageContent;
    }
}
