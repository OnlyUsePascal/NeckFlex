package com.groupproject.ImportAndShow;

import javafx.animation.FadeTransition;
import javafx.animation.PauseTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class scene1Controller implements Initializable {
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private AnchorPane anchorPane;
    private ArrayList<Item> itemList = new ArrayList<>();
    public void changeScene(ActionEvent event) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(scene2.class.getResource("/com/groupproject/scene2A.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        scene2Controller controller2 = fxmlLoader.getController();
        controller2.setPreScene(button.getScene());
        controller2.setScene1Controller(this);


        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
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
    public void createAnchorPane(String url, int a){
        AnchorPane anchorPane1 = new AnchorPane();
        double x = 100 + 200*a;
        double y = 100;
        anchorPane1.setLayoutX(x);
        anchorPane1.setLayoutY(y);
        anchorPane1.setPrefHeight(200);
        anchorPane1.setPrefWidth(200);
        anchorPane1.setBorder(Border.stroke(Color.BLACK));
        anchorPane1.setStyle("-fx-background-color: RED");
        createImageView(anchorPane1, url);
        showTitle(anchorPane1, a);
        anchorPane.getChildren().add(anchorPane1);
    }
    public void createImageView(AnchorPane pane1, String url){
        Image image = new Image(url);
        ImageView imageView = new ImageView();
        imageView.setImage(image);
        imageView.setFitHeight(100);
        imageView.setFitWidth(100);
        imageView.setPreserveRatio(true);
        pane1.getChildren().add(imageView);
    }
    public void showTitle(AnchorPane pane1,int index ){
        Label label = new Label();
        label.setLayoutX(150);
        label.setLayoutY(100);
        label.setBorder(Border.stroke(Color.ROSYBROWN));
        label.setText(itemList.get(index).getTitle());
        pane1.getChildren().add(label);
    }
    public void addItemToList(Item item){
        itemList.add(item);
        System.out.printf("Add item to list\n");
    }
    public void showItem(){
        for(int i = 0; i < itemList.size(); i++){
            System.out.println(itemList.get(i).getTitle());
        }
    }
    public void initialize(URL url, ResourceBundle rd){
        createLabel();
    }
}
