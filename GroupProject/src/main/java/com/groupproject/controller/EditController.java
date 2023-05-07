package com.groupproject.controller;

import com.groupproject.login.Account;
import com.groupproject.login.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class EditController implements Initializable {
    @FXML
    private Label username;
    @FXML
    private Label balance;
    @FXML
    private Label rewardPoint;
    @FXML
    private TextField firstName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField phoneNumber;
    @FXML
    private TextField address;

    @FXML
    private Label requiredFirstName;
    @FXML
    private Label requiredLastName;
    @FXML
    private Label requiredPhoneNumber;
    @FXML
    private Label requiredAddress;
    @FXML
    private Label invalidMessage;


    private Account account;


    private Button saveChanges;

    public void setAccount(Account a1){
        this.account = a1;
        display();
    }

    public void modifyInfo(ActionEvent event) throws IOException, InterruptedException {
        Account temp = this.account;
        String firstNameInput;
        String lastNameInput;
        String phoneNumberInput;
        String addressInput;
        int errorCount = 0;

        //get input
        firstNameInput = firstName.getText();
        lastNameInput = lastName.getText();
        phoneNumberInput = phoneNumber.getText();
        addressInput = address.getText();


        //check input
        if(firstNameInput.isBlank()){
            requiredFirstName.setVisible(true);
            errorCount++;
        }else{
            requiredFirstName.setVisible(false);
        }

        if(lastNameInput.isBlank()){
            requiredLastName.setVisible(true);
            errorCount++;
        }else {
            requiredLastName.setVisible(false);
        }

        if(phoneNumberInput.isBlank()){
            requiredPhoneNumber.setVisible(true);
            errorCount++;
        }else{
            requiredPhoneNumber.setVisible(false);
        }

        if(addressInput.isBlank()){
            requiredAddress.setVisible(true);
            errorCount++;
        }else{
            requiredAddress.setVisible(false);
        }

        //if input is valid
        for(int i = 0; i < firstNameInput.length(); i++){
            if(firstNameInput.charAt(i) == '|'|| !Character.isLetter(firstNameInput.charAt(i))){
                invalidMessage.setText("Invalid firstname");
                errorCount++;
                break;
            }
        }
        for(int i = 0; i < lastNameInput.length(); i++){
            if(lastNameInput.charAt(i) == '|'|| !Character.isLetter(lastNameInput.charAt(i))){
                invalidMessage.setText("Invalid lastname");
                errorCount++;
                break;
            }
        }
        for(int i = 0; i < phoneNumberInput.length(); i++){
            if( !Character.isDigit(phoneNumberInput.charAt(i))){
                invalidMessage.setText("Invalid phone number");
                errorCount++;
                break;
            }
        }
        for(int i = 0; i < addressInput.length(); i++){
            if(addressInput.charAt(i) == '|'){
                invalidMessage.setText("Invalid address");
                errorCount++;
                break;
            }
        }
        if(errorCount == 0){
            //set new info
            temp.setFirstName(firstNameInput);
            temp.setLastName(lastNameInput);
            temp.setPhoneNumber(phoneNumberInput);
            temp.setAddress(addressInput);
            //display message (BUGS)!!!!
            invalidMessage.setVisible(true);
            invalidMessage.setText("Changes saved");
            invalidMessage.setTextFill(Color.GREEN);
            Thread.sleep(2000);
            invalidMessage.setVisible(false);
            //change scene
            Button btn = (Button) event.getSource();
            Scene scene = btn.getScene();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
            scene.setRoot(fxmlLoader.load());
            // pass data to new scene
            HelloController helloController = fxmlLoader.getController();
            helloController.setAccount(temp);
            //display new info
            helloController.displayInfo();
        }
    }

    public void display(){
        username.setText(account.getUsername());
        balance.setText(String.valueOf(account.getBalance()));
        rewardPoint.setText(String.valueOf(account.getRewardPoint()));
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {
    }
}
