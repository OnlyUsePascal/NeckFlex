package com.groupproject.controller.popup;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Rectangle;
import javafx.stage.FileChooser;
import javafx.stage.Popup;

import java.io.File;
import java.net.URI;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ItemInfoAddController implements Initializable {
    @FXML
    private Rectangle imgFrame;
    @FXML
    private TextField titleBox;
    @FXML
    private ComboBox<String> genreBox;
    @FXML
    private ComboBox<String> categoryBox;
    @FXML
    private TextField stockBox;
    @FXML
    private TextField priceBox;
    @FXML
    private TextField yearBox;
    @FXML
    private TextArea descBox;
    @FXML
    private Label messBox;

    private URI imgPath = null;
    private String title;
    private String year;
    private String price;
    private String category;
    private String genre;
    private String stock;
    private String desc;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        messBox.setText("");

        genreBox.getItems().addAll(Arrays.asList(ConstantItem.genreList));
        genreBox.setValue(ConstantItem.genreList[0]);

        categoryBox.getItems().addAll(Arrays.asList(ConstantItem.categoryList));
        categoryBox.setValue(ConstantItem.categoryList[0]);
    }

    // --- MAIN ---
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
        ViewHandler.reOpenPopup(event);

        if (file != null) {
            Image image = new Image(file.toURI().toString());
            ImagePattern imagePattern = new ImagePattern(image);
            imgFrame.setFill(imagePattern);

            imgPath = file.toURI();
        }
    }

    public void addItem(ActionEvent event) {
        if (!checkValid()) return;

        Item item = Item.getNewItem(title, ConstantItem.categoryToIndex(category),
                ConstantItem.genreToIndex(genre), Integer.parseInt(stock),
                year, ViewHandler.getDoubleRound(Double.parseDouble(price)),
                desc);
        EntityHandler.addItem(item);

        // copy image
        String newName = item.getImgName();
        ViewHandler.copyImageToResource(imgPath, newName);

        closePopup(event);
        ViewHandler.getNoti("Add item successfully", null);
    }

    public void DecreaseStock() {
        try {
            int stock = Integer.parseInt(stockBox.getText());
            if (stock > 1) {
                stockBox.setText(String.valueOf(stock - 1));
            }
        } catch (NumberFormatException e) {
            return;
        }
    }

    public void IncreaseStock() {
        try {
            int stock = Integer.parseInt(stockBox.getText());
            stockBox.setText(String.valueOf(stock + 1));
        } catch (NumberFormatException e) {
            return;
        }
    }

    // --- BACK ---
    public boolean checkValid() {
        title = titleBox.getText();
        price = priceBox.getText();
        year = yearBox.getText();
        stock = stockBox.getText();
        desc = descBox.getText();

        category = categoryBox.getValue();
        genre = genreBox.getValue();

        // blank
        if (title.isBlank()) {
            messBox.setText("Title cannot be empty");
            return false;
        }
        if (year.isBlank()) {
            messBox.setText("Year cannot be empty");
            return false;
        }
        if (price.isBlank()) {
            messBox.setText("Price cannot be empty");
            return false;
        }
        if (stock.isBlank()) {
            messBox.setText("Stock cannot be empty");
            return false;
        }
        if (desc.isBlank()) {
            messBox.setText("Description cannot be empty");
            return false;
        }
        if (imgPath == null) {
            messBox.setText("Image cannot be empty");
            return false;
        }

        // valid
        if (!ViewHandler.checkStringNormal(title)) {
            messBox.setText("Title must contain only letters, numbers, space, comma, dot, and -");
            return false;
        }

        if (!ViewHandler.checkStringNumber(year, false)) {
            messBox.setText("Year must contain only numbers");
            return false;
        }
        if (year.length() != 4) {
            messBox.setText("Year must be 4 digits");
            return false;
        }
        if (Integer.parseInt(year) <= 0) {
            messBox.setText("Year must be positive");
            return false;
        }

        if (!ViewHandler.checkStringNumber(price, true)) {
            messBox.setText("Price must be a decimal");
            return false;
        }
        if (Double.parseDouble(price) <= 0) {
            messBox.setText("Price must be positive");
            return false;
        }

        if (imgPath == null) {
            messBox.setText("Image cannot be empty");
            return false;
        }

        if (!ViewHandler.checkStringNumber(stock, false)) {
            messBox.setText("Stock must be a number");
            return false;
        }
        if (Integer.parseInt(stock) < 0) {
            messBox.setText("Stock must be at least 0");
            return false;
        }

        if (!ViewHandler.checkStringNormal(desc)) {
            messBox.setText("Description must contain only letters, numbers, space, comma, dot, and -");
            return false;
        }

        return true;
    }


    public void closePopup(ActionEvent event) {
        ViewHandler.closePopup(event);
    }
}
