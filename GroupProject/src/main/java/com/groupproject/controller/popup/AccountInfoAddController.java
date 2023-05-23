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
    @FXML
    private ChoiceBox<String> choiceList;

    private String[] choices = {"Admin", "Customer"};

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        RegistrationPageMessage.setText("");
        RegistrationPageMessage.setTextFill(Color.RED);

        choiceList.getItems().addAll(Arrays.asList(choices));
    }

    public void onCreateButtonClick(ActionEvent event) {
        String username = RegistrationPageUsername.getText();
        String password = RegistrationPagePassword.getText();
        String confirmPassword = RegistrationPageConfirmPassword.getText();
        String firstName = RegistrationPageFirstName.getText();
        String lastName = RegistrationPageLastName.getText();
        String phoneNumber = RegistrationPagePhoneNumber.getText();
        String address = RegistrationPageAddress.getText();

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

        if (!legit){
            RegistrationPageMessage.setText("Something gone wrong");
            return;
        }

        //extra err
        if(EntityHandler.accountIsExist(username)){
            RegistrationPageMessage.setText("Username already exists!");
            return;
        }

        if(password.compareTo(confirmPassword) != 0){
            RegistrationPageMessage.setText("Password doesn't match!");
            return;
        }

        //legit
        Account newAccount = Account.getNewAccount(username, password, firstName, lastName, phoneNumber, address);

        if (choiceList.getValue().equals(choices[0])){ //is admin
            newAccount.makeAdmin();
        }

        EntityHandler.addAccount(newAccount);

        closePopup(event);
        ViewHandler.getNoti("Add account successfully", null);

    }

    public void closePopup(ActionEvent event) {
        ViewHandler.closePopup(event);
    }
}
