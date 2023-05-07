package com.groupproject.controller.page;

import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.entity.generic.Account;
import com.groupproject.toolkit.PathHandler;
import javafx.animation.PauseTransition;
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
import javafx.scene.input.KeyCode;
import javafx.scene.paint.Color;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class LoginMainController implements Initializable {
    @FXML
    private Button button;

    @FXML
    private Button LoginPageRegister;

    @FXML
    private Label loginMessage;

    @FXML
    private TextField usernameBox;

    @FXML
    private PasswordField passwordBox;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Node pane = button.getParent().getParent();
        pane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER){
                actionLogin(null);
            }
        });
    }

    public void actionLogin(ActionEvent event) {
        if(usernameBox.getText().isEmpty() || passwordBox.getText().isEmpty()){
            loginMessage.setText("Please enter your username and password!");
            loginMessage.setTextFill(Color.RED);
            return;
        }

        // System.out.println(ShopSystem.getAccountList().size());
        String curUsername = usernameBox.getText();
        String curPwd = passwordBox.getText();
        Account sysAcc = ShopSystem.getAccountFromUsername(curUsername);

        if (sysAcc == null || !sysAcc.getPassword().equals(curPwd)){
            loginMessage.setText("Wrong username or password!");
            loginMessage.setTextFill(Color.RED);
            return;
        }

        ShopSystem.setCurrentUser(sysAcc);
        loginMessage.setText("Signing in...");
        loginMessage.setTextFill(Color.GREEN);
        PauseTransition pause = new PauseTransition(Duration.millis(2000));
        pause.setOnFinished(event2 -> {
            toHome();
        });
        pause.play();
    }

    public void toHome(){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getPageHome()));
        Scene scene = button.getScene();
        try {
            scene.setRoot(loader.load());
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    public void toLoginRegister(ActionEvent event){
        FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getPageRegister()));
        Scene scene = button.getScene();
        try {
            scene.setRoot(loader.load());
        } catch (IOException err){
            err.printStackTrace();
        }
    }





}
