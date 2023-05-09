package com.groupproject.ImportImage;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LoadingImagesController implements Initializable {

    @FXML
    private AnchorPane root;
    @FXML
    private ImageView imgView;
    @FXML
    private Button importButton;
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();
        String [] extensions = new String[] {"*.jpg", "*.png", "*.gif"};

        //Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.JPG");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.PNG");
        FileChooser.ExtensionFilter extFilterAll = new FileChooser.ExtensionFilter("All files (*.jpg), (*.png)" , extensions);
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG,extFilterAll);

        //Show open file dialog
        File file = fileChooser.showOpenDialog(null);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            System.out.println(file.toURI().toString());
            imgView.setImage(image);
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}