package com.groupproject.controller.popup;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ItemInfoAddController implements Initializable {
    @FXML
    private ImageView itemRegisterImage;
    @FXML
    private TextField itemRegisterTitle;
    @FXML
    private ChoiceBox<String> itemRegisterGenre;
    @FXML
    private ChoiceBox<String> itemRegisterCategory;
    @FXML
    private TextField itemRegisterStock;
    @FXML
    private TextField itemRegisterPrice;
    @FXML
    private TextField itemRegisterYear;
    @FXML
    private Label errorMessage;

    private URI imgPath;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        itemRegisterGenre.getItems().addAll(Arrays.asList(ConstantItem.genreList));
        itemRegisterCategory.getItems().addAll(Arrays.asList(ConstantItem.categoryList));
    }

    public void loadImage(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        String[] extensions = new String[]{"*.jpg", "*.png"};

        // Set extension filter
        FileChooser.ExtensionFilter extFilterJPG = new FileChooser.ExtensionFilter("JPG files (*.jpg)", "*.jpg");
        FileChooser.ExtensionFilter extFilterPNG = new FileChooser.ExtensionFilter("PNG files (*.png)", "*.png");
        FileChooser.ExtensionFilter extFilterAll = new FileChooser.ExtensionFilter("All files (*.jpg), (*.png)", extensions);
        fileChooser.getExtensionFilters().addAll(extFilterJPG, extFilterPNG, extFilterAll);

        // Show open file dialog
        File file = fileChooser.showOpenDialog(null);
        Popup popup = (Popup) ViewHandler.getWindow(event);
        popup.show(ViewHandler.getCurrentStage());

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            itemRegisterImage.setImage(image);

            imgPath = file.toURI();
        }
    }

    public void addItem(ActionEvent event) {
        if (!checkAll()) return;

        String title = itemRegisterTitle.getText();
        int category = ConstantItem.categoryToIndex(itemRegisterCategory.getValue());
        int genre = ConstantItem.genreToIndex(itemRegisterGenre.getValue());
        int stock = Integer.parseInt(itemRegisterStock.getText());
        int year = Integer.parseInt(itemRegisterYear.getText());
        double price = Double.parseDouble(itemRegisterPrice.getText());

        Item item = EntityHandler.getNewItem(title, category, genre, stock, year, price);
        EntityHandler.addItem(item);

        //copy image
        new Thread(() -> {
            String newName = item.getImgName();
            ViewHandler.copyImageToResource(imgPath, newName);
        }).start();

        closePopup(event);
    }

    public void DecreaseStock() {
        try {
            int stock = Integer.parseInt(itemRegisterStock.getText());
            if (stock > 1) {
                itemRegisterStock.setText(String.valueOf(stock - 1));
            }
        } catch (NumberFormatException e) {
            return;
        }
    }

    public void IncreaseStock() {
        try {
            int stock = Integer.parseInt(itemRegisterStock.getText());
            itemRegisterStock.setText(String.valueOf(stock + 1));
        } catch (NumberFormatException e) {
            return;
        }
    }

    public boolean checkTitle() {
        if (itemRegisterTitle.getText().isEmpty()) {
            errorMessage.setText("Title cannot be empty");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkGenre() {
        if (itemRegisterGenre.getValue() == null) {
            errorMessage.setText("Genre cannot be empty");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkCategory() {
        if (itemRegisterCategory.getValue() == null) {
            errorMessage.setText("Category cannot be empty");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkStock() {
        try {
            Integer.parseInt(itemRegisterStock.getText());
        } catch (NumberFormatException e) {
            errorMessage.setText("Stock must be a number");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkFee() {
        try {
            Double.parseDouble(itemRegisterPrice.getText());
        } catch (NumberFormatException e) {
            errorMessage.setText("Invalid fee");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkImage() {
        if (itemRegisterImage.getImage() == null) {
            errorMessage.setText("Please load your image");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }
        return true;
    }

    public boolean checkYear() {
        try {
            Integer.parseInt(itemRegisterYear.getText());
        } catch (NumberFormatException e) {
            errorMessage.setText("Invalid year");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }

        if (itemRegisterYear.getText().length() != 4) {
            errorMessage.setText("Invalid year");
            errorMessage.setTextFill(javafx.scene.paint.Color.RED);
            return false;
        }

        return true;
    }

    public boolean checkAll() {
        if (checkTitle() == false) {
            return false;
        }
        if (checkGenre() == false) {
            return false;
        }
        if (checkCategory() == false) {
            return false;
        }
        if (checkStock() == false) {
            return false;
        }
        if (checkFee() == false) {
            return false;
        }
        if (checkImage() == false) {
            return false;
        }
        if (checkYear() == false) {
            return false;
        }
        return true;
    }



    public void closePopup(ActionEvent event) {
        ViewHandler.closePopup(event);
    }


}
