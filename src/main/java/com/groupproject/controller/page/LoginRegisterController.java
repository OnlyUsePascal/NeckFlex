package com.groupproject.controller.page;

import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginRegisterController implements Initializable {
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
    private TextField phoneBox;
    @FXML
    private TextField addressBox;
    @FXML
    private Label messBox;

    private String username;
    private String pwd;
    private String pwdConfirm;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private AnchorPane loginContainer;

    private final String blankInput = EntityHandler.blankInput;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messBox.setText("");
    }

    // --- MAIN ACTIONS ---
    public void actionSignUp(ActionEvent event) {


        if (!checkValid()) return;

        messBox.setText("Register successfully! Returning...");
        messBox.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");

        new Thread(() -> {
            EntityHandler.registerAccount(username, pwd, firstName, lastName, address, phoneNumber, false);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {

                toLoginMain(event);
                ViewHandler.getNoti("Register successfully!", loginContainer);
            });
        }).start();
    }

    public void toLoginMain(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getPageLoginMain()));
            Scene scene = ((Node) event.getSource()).getScene();
            AnchorPane loginMainContainer = loader.load();

            scene.setRoot(loginMainContainer);
            loginContainer = loginMainContainer;
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    // --- BACK SIDE ---
    public boolean checkValid() {
        username = usernameBox.getText();
        pwd = pwdBox.getText();
        pwdConfirm = pwdConfirmBox.getText();
        firstName = firstNameBox.getText();
        lastName = lastNameBox.getText();
        phoneNumber = phoneBox.getText();
        if (phoneNumber.isBlank()) phoneNumber = blankInput;
        address = addressBox.getText();
        if (address.isBlank()) address = blankInput;

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

        if (!ViewHandler.checkStringNumber(phoneNumber, false) && !phoneNumber.equals(blankInput)){
            messBox.setText("Phone number must only contain number");
            return false;
        }
        if (!ViewHandler.checkStringNormal(address) && !address.equals(blankInput)){
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

        return true;
    }
}
