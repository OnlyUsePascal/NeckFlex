package com.groupproject;


import javafx.animation.PauseTransition;
import javafx.animation.SequentialTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.util.Duration;

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
    TextField RegistrationPageFirstName = new TextField();
    @FXML
    TextField RegistrationPageLastName = new TextField();

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
    Label requiredFirstName = new Label();
    @FXML
    Label requiredLastName = new Label();
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
        String firstName = RegistrationPageFirstName.getText();
        String lastName = RegistrationPageLastName.getText();
        String phoneNumber = RegistrationPagePhoneNumber.getText();
        String address = RegistrationPageAddress.getText();
//        System.out.println(firstName);
//        System.out.printf(lastName);
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

        if(firstName.isBlank()){
            requiredFirstName.setVisible(true);
            errorCounter++;
        }else{
            requiredFirstName.setVisible(false);
        }

        if(lastName.isBlank()){
            requiredLastName.setVisible(true);
            errorCounter++;
        }else {
            requiredLastName.setVisible(false);
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
        if(SystemShop.checkUsername(username) == true){
            RegistrationPageMessage.setText("Username already exists!");
            RegistrationPageMessage.setTextFill(Color.RED);
            return;
        }


        //Checking Password
        if(password.compareTo(confirmPassword) != 0){
            System.out.println("Password doesn't match!");
            RegistrationPageMessage.setText("Password doesn't match!");
            RegistrationPageMessage.setTextFill(Color.RED);
            return;
        }

        if(checkingPhoneNumber(phoneNumber) == false && phoneNumber.isBlank() == false){
            RegistrationPageMessage.setText("Invalid Phone Number");
            RegistrationPageMessage.setTextFill(Color.RED);
            return;
        }

        if(errorCounter == 0){
            RegistrationPageMessage.setText("Register successfully!");
            RegistrationPageMessage.setTextFill(Color.GREEN);
            Account newAccount = Account.registerCustomer(username, password, firstName, lastName, phoneNumber, address);
            SystemShop.addAccount(newAccount);
            PauseTransition pause = new PauseTransition(Duration.millis(3000));
            pause.setOnFinished(event -> closeWindow());
            pause.play();
        }
    }

    public void closeWindow() {
        RegistrationPage.window.close();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}