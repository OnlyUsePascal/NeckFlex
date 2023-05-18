package com.groupproject.ImportAndShow;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.*;
import java.util.ResourceBundle;

public class scene2Controller implements Initializable {
    @FXML
    private Button backButton;
    @FXML
    private ImageView imageView;
    @FXML
    private TextField textField;
    @FXML
    String URL;
    static int count = 0;
    private Scene previousScene;
    private scene1Controller scene1Controller;

    public void setScene1Controller(scene1Controller scene1Controller) {
        this.scene1Controller = scene1Controller;
    }

    public void backScene(ActionEvent event) throws IOException {
        Item tempItem = createItem();
        scene1Controller.addItemToList(tempItem);
        scene1Controller.setLabel();
        scene1Controller.createAnchorPane(URL, count);
        scene1Controller.showItem();
        scene1Controller.addLine(tempItem.getTitle());
        count++;
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(previousScene);
        stage.show();

    }
    public void setPreScene(Scene preScene) {
        this.previousScene = preScene;
    }
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        String[] extensions = new String[]{"*.jpg", "*.png", "*.jpeg"};

        //Set extension filter
//        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
//        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterAll = new FileChooser.ExtensionFilter("All files (*.jpg), (*.png)", extensions);
        fileChooser.getExtensionFilters().addAll(extFilterAll);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            System.out.println(file.toURI().toString());
            URL = file.toURI().toString();
            System.out.println(URL);
            imageView.setImage(image);
        }
        Path from = Paths.get(file.toURI());
        String titleInput = textField.getText();
        String nameFile = titleInput + ".png";
        Path to = Paths.get("src/main/resources/com/groupproject/" + nameFile);
        CopyOption[] options = new CopyOption[]{
                StandardCopyOption.REPLACE_EXISTING,
                StandardCopyOption.COPY_ATTRIBUTES
        };
        Files.copy(from, to, options);
    }
    public Item createItem() {
        String titleInput = textField.getText();
        Item item = new Item(titleInput);
        return item;
    }

    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}

