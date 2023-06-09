package com.groupproject.controller.page;

import com.groupproject.controller.component.NavBarCustomerController;
import com.groupproject.controller.component.SidebarController;
import com.groupproject.entity.EntityHandler;
import com.groupproject.toolkit.PathHandler;
import com.groupproject.controller.ViewHandler;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class HomeController implements Initializable {
    @FXML
    private Label labelSample;
    @FXML
    private AnchorPane sidebarPane;
    @FXML
    private AnchorPane pageContent;
    @FXML
    private AnchorPane navBarPane;
    @FXML
    private AnchorPane homeContainer;

    private SidebarController sidebarController;
    private NavBarCustomerController navBarCustomerController;
    private ItemAllController itemAllController;

    private AtomicBoolean isMouseOutside = new AtomicBoolean(true);
    private AtomicBoolean isSidebarOpen = new AtomicBoolean(true);
    // private boolean menuIsOpen = true;
    private TranslateTransition menuToOpen, menuToClose;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.setHomeController(this);

        setSidebar(PathHandler.getComponentSidebar());
        setNavBarPane(PathHandler.getComponentNavBar());
        if (EntityHandler.getCurrentUser().isAdmin()) {
            setPageContent(PathHandler.getPageAdminItem());
        } else {
            setPageContent(PathHandler.getPageItemTrending());
            // setPageContent(PathHandler.getPageItemAll());
        }
    }

    // --- MAIN ---


    // --- BACK ---
    public void setSidebar(String url) {
        ViewHandler.setAnchorPane(sidebarPane, url);

        //enable auto closed
        sidebarPane.setOnMouseEntered(mouseEvent -> {
            if (!ViewHandler.sideBarIsOpen()) return;

            isMouseOutside.set(false);
        });

        sidebarPane.setOnMouseExited(mouseEvent -> {
            if (!ViewHandler.sideBarIsOpen()) return;

            isMouseOutside.set(true);
            new Thread(() -> {
                long tEnd = System.currentTimeMillis() + 2000;
                while (System.currentTimeMillis() < tEnd) {
                    if (!isMouseOutside.get() || !isSideBarOpen()) {
                        return;
                    }
                }

                Platform.runLater(() -> {
                    if (isMouseOutside.get() && isSideBarOpen()) {
                        setSideBarActive();
                    }
                });
            }).start();
        });

        //animation
        double duration = 0.3;
        menuToOpen = new TranslateTransition(Duration.seconds(duration), sidebarPane);
        menuToOpen.setToX(0);
        menuToClose = new TranslateTransition(Duration.seconds(duration), sidebarPane);
        menuToClose.setToX(-300);

        //init
        setSideBarActive();
    }

    public boolean isSideBarOpen() {
        return isSidebarOpen.get();
    }

    public void setSideBarActive() {
        if (isSidebarOpen.get()){
            isSidebarOpen.set(false);
            menuToClose.play();
        } else {
            isSidebarOpen.set(true);
            menuToOpen.play();
        }
    }

    public void setPageContent(String url) {
        ViewHandler.setAnchorPane(pageContent, url);
    }

    public void setNavBarPane(String url) {
        ViewHandler.setAnchorPane(navBarPane, url);
    }

    public AnchorPane getPageContent() {
        return pageContent;
    }
}
