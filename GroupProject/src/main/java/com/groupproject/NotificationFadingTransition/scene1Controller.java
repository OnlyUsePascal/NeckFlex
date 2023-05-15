package com.groupproject.NotificationFadingTransition;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class scene1Controller implements Initializable {
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private AnchorPane anchorPane;
    public void changeScene() throws IOException {
        FXMLLoader loader = new FXMLLoader(scene2.class.getResource("/com/groupproject/scene2.fxml"));
        Scene scene = new Scene(loader.load());
        Stage stage = (Stage) button.getScene().getWindow();
        stage.setScene(scene);
    }
    public void createLabel(){
        Label label = new Label();
        label.setLayoutX(300);
        label.setLayoutY(300);
        label.setBorder(Border.stroke(Color.ROSYBROWN));
        anchorPane.getChildren().add(label);
        this.label = label;
        label.setText("Hello");
    }
    public void setLabel() {
        label.setText("*Successully*");
        label.setFont(javafx.scene.text.Font.font(40));
        label.setTextFill(Color.RED);
        //Instantiating FadeTransition class
        fadeIn(label);
        //Pause transition for 2 seconds
        PauseTransition pause = new PauseTransition(Duration.seconds(2));
        pause.setOnFinished(event -> fadeOut(label));
        pause.play();

    }
    public void fadeIn(Node node){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(0);
        fadeTransition.setToValue(5);
        fadeTransition.play();
    }
    public void fadeOut(Node node){
        FadeTransition fadeTransition = new FadeTransition();
        fadeTransition.setDuration(Duration.millis(1000));
        fadeTransition.setNode(node);
        fadeTransition.setFromValue(5);
        fadeTransition.setToValue(0);
        fadeTransition.play();
    }
    public void initialize(URL url, ResourceBundle rd){
        createLabel();
    }
}
