package com.groupproject.controller.page;

import com.groupproject.controller.component.NavBarCustomerController;
import com.groupproject.controller.component.SidebarController;
import com.groupproject.entity.EntityHandler;
import com.groupproject.toolkit.PathHandler;
import com.groupproject.controller.ViewHandler;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

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
    private AtomicBoolean isOutside = new AtomicBoolean(true);

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.setHomeController(this);

        // if (EntityHandler.getCurrentUser().isAdmin()) {
        //     setPageContent(PathHandler.getPageAdminItem());
        // } else {
        //     setPageContent(PathHandler.getPageItemTrending());
        // }
        setPageContent(PathHandler.getPageAdminAccount());
        setSidebar(PathHandler.getComponentSidebar());
        setNavBarPane(PathHandler.getComponentNavBar());
    }

    public void setSidebar(String url) {
        ViewHandler.setAnchorPane(sidebarPanel, url);

        //auto closed
        sidebarPanel.setOnMouseEntered(mouseEvent -> {
            isOutside.set(false);
        });

        sidebarPanel.setOnMouseExited(mouseEvent -> {
            if (!ViewHandler.sideBarIsOpen()) return;

            isOutside.set(true);
            new Thread(() -> {
                long tEnd = System.currentTimeMillis() + 2000;
                while (System.currentTimeMillis() < tEnd) {
                    if (!isOutside.get()) {
                        return;
                    }
                }

                Platform.runLater(() -> {
                    if (isOutside.get() && ViewHandler.sideBarIsOpen()) {
                        ViewHandler.setMenuActive();
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
        ViewHandler.setMenuActive();
        // sidebarPanel.setTranslateX(-300);
    }

    public AnchorPane getPageContent() {
        return pageContent;
    }
}
