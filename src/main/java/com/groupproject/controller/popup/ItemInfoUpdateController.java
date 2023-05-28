package com.groupproject.controller.popup;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.EntityHandler;
import com.groupproject.controller.ViewHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.shape.Rectangle;
import javafx.stage.Popup;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ItemInfoUpdateController implements Initializable {
    @FXML
    private TextField idBox;
    @FXML
    private TextField titleBox;
    @FXML
    private TextField yearBox;
    @FXML
    private ComboBox<String> categoryBox;
    @FXML
    private ComboBox<String> genreBox;
    @FXML
    private TextField stockBox;
    @FXML
    private TextField priceBox;
    @FXML
    private TextArea descBox;
    @FXML
    private Rectangle imgFrame;
    @FXML
    private Label messBox;

    private Item item;
    private String title;
    private String genre;
    private String price;
    private String stock;
    private String desc;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genreBox.getItems().addAll(Arrays.asList(ConstantItem.genreList));
        messBox.setText("");
    }

    // --- MAIN ---
    public void updateItem(ActionEvent event) {
        if (!checkValid()) return;

        item.setTitle(title);
        item.setGenre(ConstantItem.genreToIndex(genre));
        item.setPrice(ViewHandler.getDoubleRound(Double.parseDouble(price)));
        item.setStock(Integer.parseInt(stock));
        item.setDesc(desc);

        ViewHandler.closePopup(event);
        ViewHandler.getNoti("Update item successfully", null);
    }

    public void DecreaseStock() {
        stockBox.setText(Integer.toString(Math.max(Integer.parseInt(stockBox.getText()) - 1, 0)));
    }

    public void IncreaseStock() {
        stockBox.setText(Integer.toString(Integer.parseInt(stockBox.getText()) + 1));
    }

    public void deleteItem(ActionEvent event) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmation");
        alert.setHeaderText("Delete item?");
        alert.setContentText("Are you sure you want to delete this item?");

        Stage stage = (Stage) alert.getDialogPane().getScene().getWindow();
        stage.setAlwaysOnTop(true);
        stage.toFront();

        if (alert.showAndWait().get() == ButtonType.OK) {
            EntityHandler.removeItem(item);
            ViewHandler.refreshTable();
        } else {
            ViewHandler.reOpenPopup(event);
        }
    }

    // --- BACK ---
    public void setData(Item _item) {
        item = _item;

        titleBox.setText(item.getTitle());
        idBox.setText(item.getId());
        yearBox.setText(item.getYear());

        genreBox.setValue(item.getGenreString());
        categoryBox.setValue(item.getCategoryString());

        priceBox.setText(Double.toString(item.getPrice()));
        stockBox.setText(Integer.toString(item.getStock()));

        descBox.setText(item.getDesc());

        ViewHandler.fillShapeWithImage(item.getImgName(), imgFrame);
    }



    public boolean checkValid() {
        title = titleBox.getText();
        price = priceBox.getText();
        stock = stockBox.getText();
        desc = descBox.getText();

        genre = genreBox.getValue();

        // blank
        if (title.isBlank()) {
            messBox.setText("Title cannot be empty");
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

        // valid
        if (!ViewHandler.checkStringNormal(title)) {
            messBox.setText("Title must contain only letters, numbers, space, comma, dot, and -");
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
}
