package com.groupproject.AdminInfo;


import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class RegistrationController implements Initializable {

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
    @FXML
    Button cancelButton;
    @FXML
    Button createButton;
    @FXML
    ChoiceBox choiceBox = new ChoiceBox();
    private Account account = new Account();

    boolean checkingPhoneNumber(String phoneNumber){
        try {
            Integer phoneNumberInteger = Integer.parseInt(phoneNumber);
        }catch (NumberFormatException e){
            return false;
        }
        return  true;
    }

    public void onCancelButtonClick(){
        cancelButton.getScene().getWindow().hide();
    }

    public void onCreateButtonClick(ActionEvent event) throws InterruptedException, IOException {

        String username = RegistrationPageUsername.getText();
        String password = RegistrationPagePassword.getText();
        String confirmPassword = RegistrationPageConfirmPassword.getText();
        String firstName = RegistrationPageFirstName.getText();
        String lastName = RegistrationPageLastName.getText();
        String phoneNumber = RegistrationPagePhoneNumber.getText();
        String address = RegistrationPageAddress.getText();
        System.out.println(firstName);
        System.out.printf(lastName);
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


        //Checking Password
        if(password.compareTo(confirmPassword) != 0){
            System.out.println("Password doesn't match!");
            RegistrationPageMessage.setText("Password doesn't match!");
            RegistrationPageMessage.setTextFill(Color.RED);
            return;
        }

        if(!checkingPhoneNumber(phoneNumber) && !phoneNumber.isBlank()){
            RegistrationPageMessage.setText("Invalid Phone Number");
            RegistrationPageMessage.setTextFill(Color.RED);
            return;
        }

        if(errorCounter == 0){
            String selectedChoice = choiceBox.getValue().toString();

            String temp = "";
            if(selectedChoice.equals("Admin")) {
                temp = "Admin";
            }else if(selectedChoice.equals("Customer")){
                temp = "Guest";
            }
            account= new Account(username, password, firstName, lastName, phoneNumber, address, temp);

            Button btn = (Button) event.getSource();
            Scene scene = btn.getScene();
            FXMLLoader fxmlLoader = new FXMLLoader(AdminAccountListPage.class.getResource("/com/groupproject/AdminAccountListPage.fxml"));

            RegistrationPageMessage.setText("Register successfully!");
            RegistrationPageMessage.setTextFill(Color.GREEN);
            PauseTransition pause = new PauseTransition(Duration.millis(5000));
            pause.setOnFinished(e -> {
                try {

                    scene.setRoot(fxmlLoader.load());
                    ControllerForMain controllerForMain1 = fxmlLoader.getController();
                    controllerForMain1.updateNewAccount(account);
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                    }
                }
            );
            pause.play();
//            exit();
        }

    }
//    public void exit(){
//        Stage stage = (Stage) createButton.getScene().getWindow();
//        // do what you have to do
//        stage.close();
//    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().add("Admin");
        choiceBox.getItems().add("Customer");
        choiceBox.setValue("Customer");
    }
}