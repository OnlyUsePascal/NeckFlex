package com.groupproject;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
import java.security.ProtectionDomain;
import java.util.Objects;


public class HelloController{
    private Stage stage;
    private Scene scene;
    private Parent root;
    public HelloController(){}

    @FXML
    private Button button;
    @FXML
    private Label wronglogin;
    @FXML
    private TextField username;
    @FXML
    private PasswordField password;
    public void userLogin(ActionEvent event) throws IOException {
        checkLogin();
    }
    public void checkLogin() throws IOException {

        if(username.getText().equals("dat") && password.getText().equals("123")){

            FXMLLoader loader = new FXMLLoader(Objects.requireNonNull(getClass().getResource("afterlogin.fxml")));
            root = loader.load();
            AfterLogin afterLogin = loader.getController();
            afterLogin.setHellobox(username.getText());
            stage = (Stage) button.getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
        else if(username.getText().isEmpty() && password.getText().isEmpty()){
            wronglogin.setText("Please enter username and password");
        }
        else{
            wronglogin.setText("Wrong username or password");
        }
    }
}