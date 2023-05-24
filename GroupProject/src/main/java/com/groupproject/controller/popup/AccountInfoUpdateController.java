package com.groupproject.controller.popup;

import com.groupproject.entity.generic.Account;
import com.groupproject.controller.ViewHandler;
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
    private final String blankInput = "N/A";


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }

    public void setData(Account account){
        this.account = account;
        displayInfo();
    }

    public void displayInfo(){
        usernameBox.setText(account.getUsername());

        statusBox.setText(account.getStatusString());
        balanceBox.setText("$" + account.getBalance());
        pointBox.setText(String.valueOf(account.getRewardPoint()));

        firstNameBox.setText(account.getFirstName());
        lastNameBox.setText(account.getLastName());
        phoneNumberBox.setText(account.getPhoneNumber());
        addressBox.setText(account.getAddress());
    }


    public void profileEdit(ActionEvent event){
        if (!checkValid()) return;

        //update profile
        account.setFirstName(firstName);
        account.setLastName(lastName);
        account.setPhoneNumber(phoneNumber);
        account.setAddress(address);

        //back to home
        returnHome(event);
    }

    public boolean checkValid(){
        firstName = firstNameBox.getText();
        lastName = lastNameBox.getText();
        phoneNumber = phoneNumberBox.getText(); if (phoneNumber.isBlank()) phoneNumber = blankInput;
        address = addressBox.getText(); if (address.isBlank()) address = blankInput;

        if (firstName.isBlank()) {
            messBox.setText("First name cannot be blank");
            return false;
        }

        if (lastName.isBlank()) {
            messBox.setText("Last name cannot be blank");
            return false;
        }

        if (!ViewHandler.checkStringCharacterOnly(firstName)) {
            messBox.setText("First name must contain only characters");
            return false;
        }

        if (!ViewHandler.checkStringCharacterOnly(lastName)) {
            messBox.setText("Last name must contain only characters");
            return false;
        }

        if (!phoneNumber.equals(blankInput) && !ViewHandler.checkStringNumberOnly(phoneNumber)) {
            messBox.setText("Phone number must contain only numbers");
            return false;
        }

        if (!address.equals(blankInput) && !ViewHandler.checkStringGeneral(address)) {
            messBox.setText("Address must contain only characters and numbers");
            return false;
        }

        return true;
    }

    public void returnHome(ActionEvent event){
        ViewHandler.refreshMenuButtonName();
        ViewHandler.closePopup(event);
        ViewHandler.getNoti("Update profile successfully", null);
    }
}
