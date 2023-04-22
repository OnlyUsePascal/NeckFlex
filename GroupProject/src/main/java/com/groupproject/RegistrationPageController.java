package com.groupproject;


import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationPageController implements Initializable {

    @FXML
    TextField RegistrationPageUsername = new TextField();

    @FXML
    PasswordField RegistrationPagePassword = new PasswordField();

    @FXML
    PasswordField RegistrationPageConfirmPassword = new PasswordField();

    @FXML
    TextField RegistrationPageFullName = new TextField();

    @FXML
    TextField RegistrationPagePhoneNumber = new TextField();

    @FXML
    TextField RegistrationPageAddress = new TextField();

    @FXML
    Label RegistrationPageMessage = new Label();

    boolean checkingPhoneNumber(String phoneNumber){
        try {
            Integer phoneNumberInteger = Integer.parseInt(phoneNumber);
        }catch (NumberFormatException e){
            return false;
        }
        return  true;
    }

    public void onCancelButtonClick(){
        Platform.exit();
    }

    public void onCreateButtonClick(){

        String username = RegistrationPageUsername.getText();
        String password = RegistrationPagePassword.getText();
        String confirmPassword = RegistrationPageConfirmPassword.getText();
        String fullName = RegistrationPageFullName.getText();
        String phoneNumber = RegistrationPagePhoneNumber.getText();
        String address = RegistrationPageAddress.getText();
        System.out.println(username + " " + password + " " + confirmPassword + " " + fullName);

        //ERROR CHECKING
        //Checking username

        int errorCounter = 0;
        //Checking Password
//        if(password.compareTo(confirmPassword) != 0){
//            System.out.println("Password doesn't match!");
//            RegistrationPageMessage.setText("Password doesn't match!");
//            RegistrationPageMessage.setTextFill(Color.RED);
//            return;
//        }

        if(checkingPhoneNumber(phoneNumber) == false){
            RegistrationPageMessage.setText("Invalid Phone Number");
            RegistrationPageMessage.setTextFill(Color.RED);
            return;
        }

        if(errorCounter == 0){
            RegistrationPageMessage.setText("Register successfully!");
            RegistrationPageMessage.setTextFill(Color.GREEN);
            Platform.exit();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}