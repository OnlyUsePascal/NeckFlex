package com.groupproject.controller.page;

import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;
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
import javafx.scene.paint.Color;

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

    // @FXML
    // private Label requiredUsername ;
    // @FXML
    // private Label requiredPassword ;
    // @FXML
    // private Label requiredConfirmPassword ;
    // @FXML
    // private Label requiredFirstName ;
    // @FXML
    // private Label requiredLastName ;
    // @FXML
    // private Label requiredPhoneNumber ;
    // @FXML
    // private Label requiredAddress ;


    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private AnchorPane loginContainer;

    private final String blankInput = "N/A";
    
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // messBox.setText("");
        // messBox.setTextFill(Color.RED);
    }

    public void actionSignUp(ActionEvent event) {
        username = usernameBox.getText();
        password = pwdBox.getText();
        confirmPassword = pwdConfirmBox.getText();
        firstName = firstNameBox.getText();
        lastName = lastNameBox.getText();
        phoneNumber = phoneBox.getText();
        if (phoneNumber.isBlank()) phoneNumber = blankInput;
        address = addressBox.getText();
        if (address.isBlank()) address = blankInput;

        if (!checkValid()) return;

        messBox.setText("Register successfully! Returning...");
        messBox.setStyle("-fx-font-weight: bold; -fx-text-fill: green;");

        new Thread(() -> {
            // EntityHandler.registerAccount(username, password, firstName, lastName, address, phoneNumber);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            Platform.runLater(() -> {

                toLoginMain(event);
                // ViewHandler.getNoti("Register successfully!", loginContainer);
            });
        }).start();
    }

    public boolean checkValid() {
        if (!ViewHandler.checkStringCharacterOnly(firstName)) {
            messBox.setText("First name is invalid!");
            return false;
        }

        if (!ViewHandler.checkStringCharacterOnly(lastName)) {
            messBox.setText("Last name is invalid!");
            return false;
        }

        if (!ViewHandler.checkStringGeneral(username)) {
            messBox.setText("Username is invalid!");
            return false;
        }

        if (!ViewHandler.checkStringGeneral(password)) {
            messBox.setText("Password is invalid!");
            return false;
        }

        if (!phoneNumber.equals(blankInput) && !ViewHandler.checkStringNumberOnly(phoneNumber)) {
            messBox.setText("Phone number is invalid!");
            return false;
        }

        if (!address.equals(blankInput) && !ViewHandler.checkStringGeneral(address)) {
            messBox.setText("Address is invalid!");
            return false;
        }

        if (!password.equals(confirmPassword)) {
            messBox.setText("Password doesn't match!");
            return false;
        }

        if (EntityHandler.accountIsExist(username)) {
            messBox.setText("Username already exists!");
            return false;
        }

        return true;
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

}
