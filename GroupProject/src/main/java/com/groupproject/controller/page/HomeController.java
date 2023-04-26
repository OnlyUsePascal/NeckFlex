package com.groupproject.controller.page;

import com.groupproject.controller.component.SidebarController;
import com.groupproject.toolkit.GetterFile;
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
    AnchorPane pageContent;

    SidebarController sidebarController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // menuPane.setTranslateX(-300);

        setPageContent(GetterPath.getItemTrending());
        setSidebar(GetterPath.getSidebar());
    }

    public void setSidebar(String url){
        SetterFile.setAnchorPane(sidebarPanel, url);
        AnchorPane sidebar = (AnchorPane) sidebarPanel.getChildren().get(0);
        sidebarController = (SidebarController) sidebar.getUserData();
    }

    public void setPageContent(String url) {
        SetterFile.setAnchorPane(pageContent, url);
    }

    public void menuActive(ActionEvent event){
        sidebarController.menuActive(event);
    }


    public void changeContent(ActionEvent event){
        // Button btn = (Button) event.getSource();
        // String btnId = btn.getId();
        // // System.out.println(btnId);
        //
        // try {
        //     String pageFile = "/com/groupproject/fxml/page/" + btnId + ".fxml";
        //     // System.out.println(pageFile);
        //     FXMLLoader pageView = new FXMLLoader(getClass().getResource(pageFile));
        //
        //     AnchorPane pageResult = (AnchorPane) pageView.load();
        //     // pageContent.getChildren().add(pageResult);
        //     pageContent.getChildren().set(0,pageResult);
        //
        // } catch (IOException e) {
        //     e.printStackTrace();
        // }
    }
}
