package com.groupproject;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private Label usernameBox;
    @FXML
    private Label balanceBox;
    private Account account;
    @FXML
    private Label rewardPointBox;
    @FXML
    private Label firstNameBox;
    @FXML
    private Label lastNameBox;
    @FXML
    private Label phoneNumberBox;
    @FXML
    private Label addressBox;
    @FXML
    private Button button;

    public void EditInfo(ActionEvent event) throws IOException {
        //switch to new scene
        Button btn = (Button) event.getSource();
        Scene scene = btn.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(EditPage.class.getResource("editpage.fxml"));
        scene.setRoot(fxmlLoader.load());

        //pass data to new scene
        EditController editController = fxmlLoader.getController();
        editController.setAccount(account);
    }

    public void setAccount(Account account) {
        this.account = account;
    }
    public void displayInfo(){
        usernameBox.setText(account.getUsername());
        balanceBox.setText(String.valueOf(account.getBalance()));
        rewardPointBox.setText(String.valueOf(account.getRewardPoint()));
        firstNameBox.setText(account.getFirstName());
        lastNameBox.setText(account.getLastName());
        phoneNumberBox.setText(account.getPhone());
        addressBox.setText(account.getAddress());
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        account = SystemShop.loginAccount;
        displayInfo();
    }
}