package com.groupproject.controller.page;

import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;
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
    private Label messBox;
    @FXML
    private TextField usernameBox;
    @FXML
    private PasswordField passwordBox;

    private String username;
    private String password;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messBox.setText("");

        Node pane = button.getParent().getParent();
        pane.setOnKeyPressed(event -> {
            if (event.getCode() == KeyCode.ENTER) {
                actionLogin(null);
            }
        });
    }

    // --- MAIN ACTIONS ---
    public void actionLogin(ActionEvent event) {
        username = usernameBox.getText();
        password = passwordBox.getText();
        messBox.setText("");

        // check valid
        // if (!checkValid()) return;
        if (!checkLogin()) return;

        toHome();
    }

    public void toLoginRegister(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getPageRegister()));
            Scene scene = button.getScene();

            scene.setRoot(loader.load());
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    // --- BACK SIDE ---
    public boolean checkLogin() {
        if (!EntityHandler.logIn(username, password)) {
            messBox.setText("Wrong username or password!");
            // loginMessage.setTextFill(Color.RED);
            return false;
        }

        return true;
    }

    public void toHome() {
        messBox.setText("Signing in...");

        PauseTransition pause = new PauseTransition(Duration.millis(1000));
        pause.setOnFinished(event2 -> {
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getPageHome()));
                Scene scene = button.getScene();
                scene.setRoot(loader.load());
            } catch (IOException err) {
                err.printStackTrace();
            }
        });
        pause.play();
    }


}
