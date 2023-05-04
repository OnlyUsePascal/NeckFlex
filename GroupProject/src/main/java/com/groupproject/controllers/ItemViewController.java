package com.groupproject.controllers;

import com.groupproject.types.Item;
import com.groupproject.types.SystemShop;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemViewController implements Initializable {

    @FXML
    public TableView<Item> TableViewItem ;
    @FXML
    public TableColumn<Item, String> itemIdColumn;
    @FXML
    public TableColumn<Item, String> itemTitleColumn;
    @FXML
    public TableColumn<Item, String> itemCategoryColumn;
//    @FXML
//    public TableColumn<Item, String> itemGenreColumn;
    @FXML
    public TableColumn<Item, Integer> itemStockColumn;

    @FXML
    public TextField searchField = new TextField();

    public void searchItemAction(){
        System.out.println(123456);
        TableViewItem.setItems(getItems(searchField.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        searchField.setText("");

        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            TableViewItem.setItems(getItems(newValue));
        });

        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        itemCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        itemStockColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfCopies"));
        TableViewItem.setItems(getItems(searchField.getText()));
    }

    public ObservableList<Item> getItems(String searchKeyword){
        System.out.println("123");
        ObservableList<Item> items = FXCollections.observableArrayList();
        for (Item item : SystemShop.getItemLists()) {
            System.out.println(item);
            if(searchKeyword.isBlank()){
                items.add(item);
            }else {
                if (item.getId().contains(searchKeyword) || item.getTitle().contains(searchKeyword)) {
                    items.add(item);
                }
            }
        }
        return items;
    }

}
