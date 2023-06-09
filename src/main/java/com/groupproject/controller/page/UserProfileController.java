package com.groupproject.controller.page;

import com.groupproject.entity.generic.Account;
import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;
import com.groupproject.entity.generic.AccountCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class UserProfileController implements Initializable {
    @FXML
    private Label balanceBox;
    @FXML
    private Label pointBox;
    @FXML
    private Label statusBox;

    @FXML
    private TextField usernameBox;
    @FXML
    private PasswordField pwdBox;
    @FXML
    private PasswordField pwdConfirmBox;
    @FXML
    private TextField firstNameBox;
    @FXML
    private TextField lastNameBox;
    @FXML
    private TextField phoneNumberBox;
    @FXML
    private TextField addressBox;
    @FXML
    private Label messBox;

    private Account account;

    private String username;
    private String pwd;
    private String pwdConfirm;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    private final String blankInput = EntityHandler.blankInput;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messBox.setText("");

        setData(EntityHandler.getCurrentUser());
        displayInfo();
    }

    // --- MAIN ---
    public void profileEdit(ActionEvent event) {
        pwd = pwdBox.getText();
        pwdConfirm = pwdConfirmBox.getText();

        firstName = firstNameBox.getText();
        lastName = lastNameBox.getText();
        phoneNumber = phoneNumberBox.getText();
        if (phoneNumber.isBlank()) phoneNumber = blankInput;
        address = addressBox.getText();
        if (address.isBlank()) address = blankInput;

        if (!checkValid()) return;

        updateAccount();
        returnHome(event);
    }

    // --- BACK ---
    public void setData(Account account) {
        this.account = account;
        displayInfo();
    }

    public void displayInfo() {
        usernameBox.setText(account.getUsername());
        firstNameBox.setText(account.getFirstName());
        lastNameBox.setText(account.getLastName());
        phoneNumberBox.setText(account.getPhoneNumber());
        addressBox.setText(account.getAddress());
        statusBox.setText(account.getStatusString());

        if (!account.isAdmin()) {
            balanceBox.setText("$" + ((AccountCustomer) account).getBalance1());
            pointBox.setText("" + ((AccountCustomer) account).getRewardPoint1());
        }
    }

    public void updateAccount() {
        if (!pwd.isBlank()) account.setPwd(pwd);

        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setPhoneNumber(phoneNumber);
        account.setAddress(address);
    }

    public boolean checkValid() {
        // blank
        if (firstName.isBlank()) {
            messBox.setText("First name cannot be blank");
            return false;
        }
        if (lastName.isBlank()) {
            messBox.setText("Last name cannot be blank");
            return false;
        }

        // valid
        if (!pwd.isBlank()){
            if (!ViewHandler.checkStringAuth(pwd)) {
                messBox.setText("Password must only contain letter, number");
                return false;
            }
            if (!pwd.equals(pwdConfirm)) {
                messBox.setText("Password doesn't match");
                return false;
            }
        }

        if (!ViewHandler.checkStringName(firstName)) {
            messBox.setText("First name must only contain letter and space");
            return false;
        }
        if (!ViewHandler.checkStringName(lastName)) {
            messBox.setText("Last name must only contain letter and space");
            return false;
        }

        if (!ViewHandler.checkStringNumber(phoneNumber, false) && !phoneNumber.equals(blankInput)) {
            messBox.setText("Phone number must only contain number");
            return false;
        }
        if (!ViewHandler.checkStringNormal(address) && !address.equals(blankInput)) {
            messBox.setText("Address must only contain letter, number, space, comma, dot");
            return false;
        }

        // valid 2

        return true;
    }

    public void returnHome(ActionEvent event) {
        ViewHandler.refreshMenuButtonName();
        pwdBox.clear();
        pwdConfirmBox.clear();
        messBox.setText("");
        ViewHandler.getNoti("Update Profile successfully!", null);
    }
}
















