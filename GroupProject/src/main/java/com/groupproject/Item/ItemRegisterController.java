package com.groupproject.Item;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

public class ItemRegisterController implements Initializable {

    @FXML
    private ImageView ItemRegisterImage;
//    @FXML
//    private Button LoadImageButton;
//    @FXML
//    private TextField ItemRegisterTitle;
//    @FXML
//    private ChoiceBox ItemRegisterGenre;
//    @FXML
//    private ChoiceBox ItemRegisterCategory;
//    @FXML
//    private TextField ItemRegisterStock;
//    @FXML
//    private TextField ItemRegisterFee;

    public void loadImage(){
        FileChooser fileChooser = new FileChooser();
        String [] extensions = new String[] {"*.jpg", "*.png"};

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

            ItemRegisterImage.setImage(image);
        }
    }

    public void DecreaseStock(){

    }

    public void IncreaseStock(){

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {


    }
}
