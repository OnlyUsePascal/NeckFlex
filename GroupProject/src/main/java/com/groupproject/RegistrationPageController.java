package com.groupproject;


import javafx.animation.PauseTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class RegistrationPageController {

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
        if(password.equals(confirmPassword) == false){
            System.out.println("Password doesn't match!");
            errorCounter++;
        }

        if(checkingPhoneNumber(phoneNumber) == false){
            System.out.println("Invalid phone number");
            errorCounter++;
        }

        if(errorCounter == 0){
            System.out.println("Register sucessfully!");
            Platform.exit();
        }

    }

}