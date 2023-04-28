package com.groupproject.controller.page;

import com.groupproject.controller.component.SidebarController;
import com.groupproject.toolkit.GetterPath;
import com.groupproject.toolkit.SetterFile;
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
    FXMLLoader sidebarLoader;

    @FXML
    AnchorPane pageContent;
    @FXML
    FXMLLoader pageContentLoader;

    SidebarController sidebarController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        sidebarPanel.setTranslateX(-300);
        setPageContent(GetterPath.getPageItemTrending());
        setSidebar(GetterPath.getComponentSidebar());
    }

    public void setSidebar(String url){
        sidebarLoader = new FXMLLoader(getClass().getResource(url));
        try {
            AnchorPane sidebar = (AnchorPane) sidebarLoader.load();
            sidebarController = (SidebarController) sidebarLoader.getController();

            SetterFile.setAnchorPane(sidebarPanel, sidebar);
            sidebarController.setHomeController(this);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setPageContent(String url) {
        pageContentLoader = new FXMLLoader(getClass().getResource(url));
        try {
            AnchorPane page = (AnchorPane) pageContentLoader.load();
            SetterFile.setAnchorPane(pageContent, page);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void menuActive(ActionEvent event){
        sidebarController.menuActive(event);
    }

    public void changePageContent(String url){
        // System.out.println(url);
        pageContentLoader = new FXMLLoader(getClass().getResource(url));
        try {
            AnchorPane page = (AnchorPane) pageContentLoader.load();
            SetterFile.setAnchorPane(pageContent, page);
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
