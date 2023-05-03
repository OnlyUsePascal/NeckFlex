package com.groupproject.controller.page;

import com.groupproject.controller.component.NavBarController;
import com.groupproject.entity.runtime.CurrentUser;
import com.groupproject.entity.generic.Account;
import com.groupproject.toolkit.ObjectHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    @FXML
    private Label usernameBox;
    @FXML
    private Label balanceBox;
    private Account account;
    @FXML
    private Label rewardPointBox;
    @FXML
    private TextField firstNameBox;
    @FXML
    private TextField lastNameBox;
    @FXML
    private TextField phoneNumberBox;
    @FXML
    private TextField addressBox;
    @FXML
    private Button button;
    @FXML
    private Label requiredFirstName;
    @FXML
    private Label requiredLastName;
    @FXML
    private Label requiredPhoneNumber;
    @FXML
    private Label requiredAddress;
    @FXML
    private Label invalidMessage;

    private HomeController homeController;
    private NavBarController navBarController;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        this.account = CurrentUser.getCurrentUser();
        // this.account = Account.getNewAccount("joun", "123", "dat", "nguyen", "123", "123");
        displayInfo();

    }

    public void setHomeController(HomeController homeController) {
        this.homeController = homeController;
    }

    public void setNavBarController(NavBarController navBarController) {
        this.navBarController = navBarController;
    }

    public void displayInfo(){
        usernameBox.setText(account.getUsername());
        balanceBox.setText(String.valueOf(account.getBalance()));
        rewardPointBox.setText(String.valueOf(account.getRewardPoint()));
        firstNameBox.setText(account.getFirstName());
        lastNameBox.setText(account.getLastName());
        phoneNumberBox.setText(account.getPhoneNumber());
        addressBox.setText(account.getAddress());
    }

    public void profileEdit(ActionEvent event){
        // System.out.println("toUserProfileEdit");
        boolean legit = true;
        boolean status = true;

        status = ObjectHandler.checkStringCharacterOnly(firstNameBox.getText());
        requiredFirstName.setVisible(!status);
        legit &= status;

        status = ObjectHandler.checkStringCharacterOnly(lastNameBox.getText());
        requiredLastName.setVisible(!status);
        legit &= status;

        status = ObjectHandler.checkStringNumberOnly(phoneNumberBox.getText());
        requiredPhoneNumber.setVisible(!status);
        legit &= status;

        status = ObjectHandler.checkStringGeneral(addressBox.getText());
        requiredAddress.setVisible(!status);
        legit &= status;

        if (!legit) {
            invalidMessage.setVisible(true);
            return;
        }
        
        invalidMessage.setVisible(false);

        //account
        this.account.setFirstName(firstNameBox.getText());
        this.account.setLastName(lastNameBox.getText());
        this.account.setPhoneNumber(phoneNumberBox.getText());
        this.account.setAddress(addressBox.getText());

        System.out.println(this.account);

        homeController.setPageContent(PathHandler.getPageItemTrending());
        navBarController.setmenuButtonName(account.getFirstName());
    }

}
















