package com.groupproject.controllers;
import com.groupproject.*;
import com.groupproject.types.Account;
import com.groupproject.LoginPage;
import com.groupproject.types.SystemShop;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoginPageController implements Initializable {
    private Stage stage;
    private Scene scene;
    private Parent root;
    public LoginPageController(){}

    @FXML
    private Button button;

    @FXML
    private Button LoginPageRegister;
    @FXML
    private Label loginMessage;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    @FXML
    private ImageView loadingImage;
    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }


    public void checkLogin() throws IOException {

        if(username.getText().isEmpty() || password.getText().isEmpty()){
            loginMessage.setText("Please enter your username and password!");
            loginMessage.setTextFill(Color.RED);
            return;
        }

        System.out.println(SystemShop.getAccountLists().size());

        for(Account account : SystemShop.getAccountLists()){
            System.out.println(account.Account2Str());

            if( username.getText().equals(account.getUsername()) &&
                password.getText().equals(account.getPassword()) ){
                SystemShop.loginAccount = account;
                loginMessage.setText("Signing in...");
                loginMessage.setTextFill(Color.GREEN);
                loadingImage.setVisible(true);
                PauseTransition pause = new PauseTransition(Duration.millis(3000));
                pause.setOnFinished(event -> {
                    HelloApplication helloApplication = new HelloApplication();
                    try {
                        helloApplication.start(new Stage());
                        loadingImage.setVisible(false);
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                    closeWindow();
                });
                pause.play();
                return;
            }
        }

        loginMessage.setText("Wrong username or password!");
        loginMessage.setTextFill(Color.RED);

    }

    public void closeWindow() {
        LoginPage.window.close();
    }

    public void userRegister(ActionEvent event) throws IOException {
        RegistrationPage registrationPage = new RegistrationPage();
        registrationPage.start(new Stage());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        loadingImage.setVisible(false);
    }
}