package com.groupproject.controller.component;

import com.groupproject.controller.page.HomeController;
import com.groupproject.controller.page.UserProfileController;
import com.groupproject.entity.runtime.CurrentUser;
import com.groupproject.toolkit.ObjectHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class NavBarController implements Initializable {
    @FXML
    TextField searchField;

    @FXML
    MenuButton menuButton;

    HomeController homeController;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchField.setOnKeyPressed(keyEvent -> {
            if(keyEvent.getCode().toString().equals("ENTER")){
                toHomeWithSearch(null);
            }
        });

        setmenuButtonName(CurrentUser.getCurrentUser().getFirstName());
        // menuButton.setText("Hello, " + CurrentUser.getCurrentUser().getFirstName() + "!");
    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public void setmenuButtonName(String name){
        menuButton.setText("Hello, " + name + "!");
    }


    public void toMenu(ActionEvent event){
        homeController.setMenuActive(event);
    }

    public void toHomeWithSearch(ActionEvent event){
        System.out.println("To home with search");
    }

    public void toHome(ActionEvent event){
        System.out.println("to home");
    }

    public void toUserProfile(ActionEvent event){
        // homeController.setPageContent(PathHandler.getPageUserProfile());
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getPageUserProfile()));
        try {
            AnchorPane page = loader.load();
            ObjectHandler.setAnchorPane(homeController.getPageContentFrame(), page);

            UserProfileController userProfileController = loader.getController();
            userProfileController.setHomeController(homeController);
            userProfileController.setNavBarController(this);
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    public void toLoginMain(ActionEvent event){
        System.out.println("to login main");
    }

}
