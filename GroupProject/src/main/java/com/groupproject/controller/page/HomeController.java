package com.groupproject.controller.page;

import com.groupproject.controller.component.NavBarCustomerController;
import com.groupproject.controller.component.SidebarController;
import com.groupproject.toolkit.PathHandler;
import com.groupproject.entity.runtime.ViewHandler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
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
    private AnchorPane navBarPane;
    @FXML
    private AnchorPane homeContainer;

    private SidebarController sidebarController;
    private NavBarCustomerController navBarCustomerController;
    private ItemAllController itemAllController;
    private boolean isOutside;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.setHomeController(this);

        // if (EntityHandler.getCurrentUser().isAdmin()) {
        //     setPageContent(PathHandler.getPageAdminItem());
        // } else {
        //     setPageContent(PathHandler.getPageItemTrending());
        // }
        setPageContent(PathHandler.getPageUserRecord());
        setSidebar(PathHandler.getComponentSidebar());
        setNavBarPane(PathHandler.getComponentNavBar());
    }

    public void setSidebar(String url) {
        ViewHandler.setAnchorPane(sidebarPanel, url);

        //auto closed
        sidebarPanel.setOnMouseEntered(mouseEvent -> {
            isOutside = false;
        });

        sidebarPanel.setOnMouseExited(mouseEvent -> {
            isOutside = true;
            new Thread(() -> {
                long tEnd = System.currentTimeMillis() + 2500;
                while (System.currentTimeMillis() < tEnd) {
                    if (!isOutside) break;
                }

                Platform.runLater(() -> {
                    if (isOutside && ViewHandler.sideBarIsOpen()){
                        ViewHandler.setMenuActive(null);
                    }
                });
            }).start();
        });
    }

    public void setPageContent(String url) {
        ViewHandler.setAnchorPane(pageContent, url);
    }

    public void setNavBarPane(String url) {
        ViewHandler.setAnchorPane(navBarPane, url);
        sidebarPanel.setTranslateX(-300);
    }

    public AnchorPane getPageContent() {
        return pageContent;
    }
}
