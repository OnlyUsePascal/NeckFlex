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
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Border;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.Scanner;

public class scene1Controller implements Initializable {
    @FXML
    private Button button;
    @FXML
    private Label label;
    @FXML
    private AnchorPane anchorPane;
    private ArrayList<Item> itemList = new ArrayList<>();
    @FXML
    private TextField textField;
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

    public void deleteFile(){
        if(textField.getText().equals("")){
            System.out.println("Please enter the name of the item");
            return;
        }
        if(itemList.size() == 0){
            System.out.println("List is empty");
        }
        else {
            String itemName = textField.getText();
            String fileName = "src/main/resources/com/groupproject/" + itemName + ".png";

            removeItemFromList(itemName);
            removeLine(itemName);
            try {
                Files.delete(Paths.get(fileName));
                System.out.println("File deleted successfully");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public void removeItemFromList(String name){
        for(Item item : itemList){
            if(item.getTitle().equals(name)){
                itemList.remove(item);
                break;
            }
            else {
                System.out.println("Item not found");
            }
        }
    }
    public void addLine(String inName) {
        String name = inName + "\n";

        FileWriter file_writer;
        try {
            file_writer = new FileWriter("src/main/resources/com/groupproject/data.txt", true);
            BufferedWriter buffered_Writer = new BufferedWriter(file_writer);
            buffered_Writer.write(name);
            buffered_Writer.flush();
            buffered_Writer.close();

        } catch (IOException e) {
            System.out.println("Add line failed!!" +e);
        }

    }
    public void loadData(){
        try {
            File file = new File("src/main/resources/com/groupproject/data.txt");

            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                addItemToList(new Item(line));
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Load data failed!!" +e);
        }
    }
    private void removeLine(String name) {
        try
        {
            BufferedReader file = new BufferedReader(new FileReader("src/main/resources/com/groupproject/data.txt"));
            String line;
            String input = "";
            while ((line = file.readLine()) != null)
            {
                //System.out.println(line);
                if (line.contains(name))
                {
                    line = "";
                    System.out.println("Line deleted.");
                }
                input += line + '\n';
            }
            FileOutputStream File = new FileOutputStream("src/main/resources/com/groupproject/data.txt");
            File.write(input.getBytes());
            file.close();
            File.close();

        }
        catch (Exception e)
        {
            System.out.println("Problem reading file.");
        }
    }

    public void initialize(URL url, ResourceBundle rd){
        createLabel();
        loadData();
    }
}
