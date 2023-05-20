package com.groupproject.controller.page;

import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.generic.Account;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginRegisterController implements Initializable {
    @FXML
    private TextField RegistrationPageUsername = new TextField();
    @FXML
    private PasswordField RegistrationPagePassword = new PasswordField();
    @FXML
    private PasswordField RegistrationPageConfirmPassword = new PasswordField();
    @FXML
    private TextField RegistrationPageFirstName = new TextField();
    @FXML
    private TextField RegistrationPageLastName = new TextField();
    @FXML
    private TextField RegistrationPagePhoneNumber = new TextField();
    @FXML
    private TextField RegistrationPageAddress = new TextField();
    @FXML
    private Label RegistrationPageMessage = new Label();
    @FXML
    private Label requiredUsername = new Label();
    @FXML
    private Label requiredPassword = new Label();
    @FXML
    private Label requiredConfirmPassword = new Label();
    @FXML
    private Label requiredFirstName = new Label();
    @FXML
    private Label requiredLastName = new Label();
    @FXML
    private Label requiredPhoneNumber = new Label();
    @FXML
    private Label requiredAddress = new Label();


    private String username;
    private String password;
    private String confirmPassword;
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String address;
    private AnchorPane loginContainer;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RegistrationPageMessage.setText("");
        RegistrationPageMessage.setTextFill(Color.RED);
    }

    public void onCreateButtonClick(ActionEvent event) {
        username = RegistrationPageUsername.getText();
        password = RegistrationPagePassword.getText();
        confirmPassword = RegistrationPageConfirmPassword.getText();
        firstName = RegistrationPageFirstName.getText();
        lastName = RegistrationPageLastName.getText();
        phoneNumber = RegistrationPagePhoneNumber.getText();
        address = RegistrationPageAddress.getText();

        if (!checkValid()){
            RegistrationPageMessage.setText("Something gone wrong");
            return;
        }

        if(!password.equals(confirmPassword)){
            RegistrationPageMessage.setText("Password doesn't match!");
            return;
        }

        if(EntityHandler.accountIsExist(username)){
            RegistrationPageMessage.setText("Username already exists!");
            return;
        }

        //legit
        new Thread(() -> {
            EntityHandler.registerAccount(username, password, firstName, lastName, address, phoneNumber);

            Platform.runLater(() -> {
                toPageLoginMain(event);
                ViewHandler.getNoti("Register successfully!", loginContainer);
            });
        }).start();
    }

    public boolean checkValid(){
        boolean legit = true;
        boolean status = true;
        RegistrationPageMessage.setText("");

        //format
        status = ViewHandler.checkStringGeneral(username);
        requiredUsername.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringGeneral(password);
        requiredPassword.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringGeneral(confirmPassword);
        requiredConfirmPassword.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringCharacterOnly(firstName);
        requiredFirstName.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringCharacterOnly(lastName);
        requiredLastName.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringNumberOnly(phoneNumber);
        requiredPhoneNumber.setVisible(!status);
        legit &= status;

        status = ViewHandler.checkStringGeneral(address);
        requiredAddress.setVisible(!status);
        legit &= status;

        return legit;
    }

    public void toPageLoginMain(ActionEvent event){
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
