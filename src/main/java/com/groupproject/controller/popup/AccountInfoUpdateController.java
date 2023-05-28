package com.groupproject.controller.popup;

import com.groupproject.entity.EntityHandler;
import com.groupproject.entity.generic.Account;
import com.groupproject.controller.ViewHandler;
import com.groupproject.entity.generic.AccountCustomer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountInfoUpdateController implements Initializable {
    @FXML
    private TextField usernameBox;

    @FXML
    private Label balanceBox;
    @FXML
    private Label pointBox;
    @FXML
    private Label statusBox;

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
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private final String blankInput = EntityHandler.blankInput;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messBox.setText("");
    }

    // --- MAIN ---
    public void profileEdit(ActionEvent event) {
        if (!checkValid()) return;

        // update profile
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setPhoneNumber(phoneNumber);
        account.setAddress(address);

        // back to home
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

    public boolean checkValid() {
        firstName = firstNameBox.getText();
        lastName = lastNameBox.getText();
        phoneNumber = phoneNumberBox.getText();
        if (phoneNumber.isBlank()) phoneNumber = blankInput;
        address = addressBox.getText();
        if (address.isBlank()) address = blankInput;

        //blank
        if (firstName.isBlank()) {
            messBox.setText("First name cannot be blank");
            return false;
        }
        if (lastName.isBlank()) {
            messBox.setText("Last name cannot be blank");
            return false;
        }

        //valid
        if (!ViewHandler.checkStringName(firstName)) {
            messBox.setText("First name must only contain letter and space");
            return false;
        }
        if (!ViewHandler.checkStringName(lastName)) {
            messBox.setText("Last name must only contain letter and space");
            return false;
        }

        if (!ViewHandler.checkStringNumber(phoneNumber, false) && !phoneNumber.equals(blankInput)){
            messBox.setText("Phone number must only contain number");
            return false;
        }
        if (!ViewHandler.checkStringNormal(address) && !address.equals(blankInput)){
            messBox.setText("Address must only contain letter, number, space, comma, dot, and -");
            return false;
        }

        return true;
    }

    public void returnHome(ActionEvent event) {
        ViewHandler.refreshMenuButtonName();
        ViewHandler.closePopup(event);
        ViewHandler.getNoti("Update profile successfully", null);
    }
}
