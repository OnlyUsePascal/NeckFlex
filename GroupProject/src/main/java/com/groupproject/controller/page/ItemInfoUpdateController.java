package com.groupproject.controller.page;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class ItemInfoUpdateController implements Initializable {
    @FXML
    private TextField itemInfoId;
    @FXML
    private TextField itemInfoTitle;
    @FXML
    private ChoiceBox<String> itemInfoGenre;
    @FXML
    private TextField itemInfoQuantity;

    private Item item;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //add genre options
        ObservableList<String> genreList = FXCollections.observableArrayList();
        genreList.addAll(Arrays.asList(ConstantItem.genreList));
        itemInfoGenre.setItems(genreList);
    }

    public void setData(Item _item){
        item = _item;
        itemInfoId.setText(item.getId());
        itemInfoTitle.setText(item.getTitle());
        itemInfoQuantity.setText(Integer.toString(item.getStock()));
        itemInfoGenre.setValue(item.getGenre());
    }

    public void updateItem(ActionEvent event){
        System.out.println("Update Item!!!");

        item.setTitle(itemInfoTitle.getText());
        item.setGenre(ConstantItem.genreToIndex(itemInfoGenre.getValue()));
        item.setStock(Integer.parseInt(itemInfoQuantity.getText()));

        ViewHandler.popupClose(event);
    }

    public void DecreaseStock(){
        itemInfoQuantity.setText(Integer.toString(Math.max(Integer.parseInt(itemInfoQuantity.getText())-1, 0)));
    }

    public void IncreaseStock(){
        itemInfoQuantity.setText(Integer.toString(Integer.parseInt(itemInfoQuantity.getText())+1));
    }

    public void deleteItem(ActionEvent event){
        System.out.println("Deleting Item!!!");
        EntityHandler.itemListAdd().remove(item);

        ViewHandler.popupClose(event);
    }



}
