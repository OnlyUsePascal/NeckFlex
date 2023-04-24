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

import static java.lang.Thread.sleep;

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

    @FXML
    Label requiredUsername = new Label();
    @FXML
    Label requiredPassword = new Label();
    @FXML
    Label requiredConfirmPassword = new Label();
    @FXML
    Label requiredFullName = new Label();
    @FXML
    Label requiredPhoneNumber = new Label();
    @FXML
    Label requiredAddress = new Label();

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

    public void onCreateButtonClick() throws InterruptedException {

        String username = RegistrationPageUsername.getText();
        String password = RegistrationPagePassword.getText();
        String confirmPassword = RegistrationPageConfirmPassword.getText();
        String fullName = RegistrationPageFullName.getText();
        String phoneNumber = RegistrationPagePhoneNumber.getText();
        String address = RegistrationPageAddress.getText();

        int errorCounter = 0;

        if(username.isBlank()){
            requiredUsername.setVisible(true);
            errorCounter++;
        }else{
            requiredUsername.setVisible(false);
        }

        if(password.isBlank()){
            requiredPassword.setVisible(true);
            errorCounter++;
        }else{
            requiredPassword.setVisible(false);
        }

        if(confirmPassword.isBlank()){
            requiredConfirmPassword.setVisible(true);
            errorCounter++;
        }else{
            requiredConfirmPassword.setVisible(false);
        }

        if(fullName.isBlank()){
            requiredFullName.setVisible(true);
            errorCounter++;
        }else{
            requiredFullName.setVisible(false);
        }

        if(phoneNumber.isBlank()){
            requiredPhoneNumber.setVisible(true);
            errorCounter++;
        }else{
            requiredPhoneNumber.setVisible(false);
        }

        if(address.isBlank()){
            requiredAddress.setVisible(true);
            errorCounter++;
        }else{
            requiredAddress.setVisible(false);
        }

        //ERROR CHECKING
        //Checking username


        //Checking Password
        if(password.compareTo(confirmPassword) != 0){
            System.out.println("Password doesn't match!");
            RegistrationPageMessage.setText("Password doesn't match!");
            RegistrationPageMessage.setTextFill(Color.RED);
            return;
        }

        if(checkingPhoneNumber(phoneNumber) == false){
            RegistrationPageMessage.setText("Invalid Phone Number");
            RegistrationPageMessage.setTextFill(Color.RED);
            return;
        }

        if(errorCounter == 0){
            RegistrationPageMessage.setText("Register successfully!");
            RegistrationPageMessage.setTextFill(Color.GREEN);
//            sleep(3000);
            Platform.exit();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}