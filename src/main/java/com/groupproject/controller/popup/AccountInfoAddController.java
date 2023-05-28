package com.groupproject.controller.popup;

import com.groupproject.entity.generic.Account;
import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AccountInfoAddController implements Initializable {
    @FXML
    private TextField usernameBox = new TextField();
    @FXML
    private PasswordField pwdBox = new PasswordField();
    @FXML
    private PasswordField pwdConfirmBox = new PasswordField();
    @FXML
    private TextField firstNameBox = new TextField();
    @FXML
    private TextField lastNameBox = new TextField();
    @FXML
    private TextField phoneNumberBox = new TextField();
    @FXML
    private TextField addressBox = new TextField();
    @FXML
    private Label messBox = new Label();
    @FXML
    private ComboBox<String> typeList;

    private String[] choices = {"Admin", "Customer"};
    private String username;
    private String pwd;
    private String pwdConfirm;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messBox.setText("");
        messBox.setTextFill(Color.RED);

        typeList.getItems().addAll(Arrays.asList(choices));
        typeList.setValue(choices[1]);
    }

    // --- MAIN ---
    public void createAccount(ActionEvent event) {
        if (!checkValid()) return;

        // legit
        boolean isAdmin = typeList.getValue().equals(choices[0]);
        EntityHandler.registerAccount(username, pwd, firstName, lastName, address, phoneNumber, isAdmin);

        closePopup(event);
        ViewHandler.getNoti("Add account successfully", null);

    }

    // --- BACK ---
    public boolean checkValid() {
        username = usernameBox.getText();
        pwd = pwdBox.getText();
        pwdConfirm = pwdConfirmBox.getText();

        firstName = firstNameBox.getText();
        lastName = lastNameBox.getText();
        phoneNumber = phoneNumberBox.getText();
        if (phoneNumber.isBlank()) phoneNumber = EntityHandler.blankInput;
        address = addressBox.getText();
        if (address.isBlank()) address = EntityHandler.blankInput;

        //blank
        if (username.isBlank()) {
            messBox.setText("Username cannot be blank");
            return false;
        }
        if (pwd.isBlank()) {
            messBox.setText("Password cannot be blank");
            return false;
        }
        if (firstName.isBlank()) {
            messBox.setText("First name cannot be blank");
            return false;
        }
        if (lastName.isBlank()) {
            messBox.setText("Last name cannot be blank");
            return false;
        }

        //valid
        if (!ViewHandler.checkStringAuth(username)) {
            messBox.setText("Username must only contain letter, number");
            return false;
        }
        if (!ViewHandler.checkStringAuth(pwd)) {
            messBox.setText("Password must only contain letter, number");
            return false;
        }

        if (!ViewHandler.checkStringName(firstName)) {
            messBox.setText("First name must only contain letter and space");
            return false;
        }
        if (!ViewHandler.checkStringName(lastName)) {
            messBox.setText("Last name must only contain letter and space");
            return false;
        }

        if (!ViewHandler.checkStringNumber(phoneNumber, false) && !phoneNumber.equals(EntityHandler.blankInput)){
            messBox.setText("Phone number must only contain number");
            return false;
        }
        if (!ViewHandler.checkStringNormal(address) && !address.equals(EntityHandler.blankInput)){
            messBox.setText("Address must only contain letter, number, space, comma, dot, and -");
            return false;
        }

        //valid 2
        if (EntityHandler.accountIsExist(username)) {
            messBox.setText("Username already exist");
            return false;
        }
        if (!pwd.equals(pwdConfirm)) {
            messBox.setText("Password doesn't match");
            return false;
        }


        // if (username.isBlank()) {
        //     messBox.setText("Username cannot be blank");
        //     return false;
        // }
        // if (EntityHandler.accountIsExist(username)) {
        //     messBox.setText("Username already exist");
        //     return false;
        // }
        // if (!ViewHandler.checkStringGeneral(username)) {
        //     messBox.setText("Username can only contain letter, number");
        //     return false;
        // }
        //
        // if (pwd.isBlank()) {
        //     messBox.setText("Password cannot be blank");
        //     return false;
        // }
        // if (!pwd.equals(pwdConfirm)) {
        //     messBox.setText("Password doesn't match");
        //     return false;
        // }
        //
        // if (firstName.isBlank()) {
        //     messBox.setText("First name cannot be blank");
        //     return false;
        // }
        // if (!ViewHandler.checkStringCharacterOnly(firstName)) {
        //     messBox.setText("First name cannot contain number or special character");
        //     return false;
        // }
        //
        // if (lastName.isBlank()) {
        //     messBox.setText("Last name cannot be blank");
        //     return false;
        // }
        // if (!ViewHandler.checkStringCharacterOnly(lastName)) {
        //     messBox.setText("Last name cannot contain number or special character");
        //     return false;
        // }
        //
        // if (!phoneNumber.equals(EntityHandler.blankInput) && !ViewHandler.checkStringNumberOnly(phoneNumber)) {
        //     messBox.setText("Phone number must be number only");
        //     return false;
        // }
        // if (!address.equals(EntityHandler.blankInput) && !ViewHandler.checkStringGeneral(address)) {
        //     messBox.setText("Address cannot contain special character except , .");
        //     return false;
        // }

        return true;
    }

    public void closePopup(ActionEvent event) {
        ViewHandler.closePopup(event);
    }
}
