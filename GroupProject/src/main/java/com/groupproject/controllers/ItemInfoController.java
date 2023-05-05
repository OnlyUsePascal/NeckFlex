package com.groupproject.controllers;

import com.groupproject.types.Item;
import com.groupproject.types.SystemShop;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class ItemInfoController implements Initializable {



    @FXML
    private TextField ItemInfoId;
    @FXML
    private TextField ItemInfoTitle;
    @FXML
    private ChoiceBox ItemInfoGenre;
    @FXML
    private TextField ItemInfoQuantity;

    private Item item;

    public void assignItem(Item item){
        this.item = item;
    }

    public boolean UpdateItem(){
//        if(SystemShop.getItemLists().contains(item)){
//            item.setGenre((String) ItemInfoGenre.getValue());
//            item.setNumberOfCopies(Integer.parseInt(ItemInfoQuantity.getText()));
//            System.out.println("Update success");
//            for (Item item : SystemShop.itemLists) {
//                if(item.getId().equals(this.item.getId())){
//                    item = this.item;
//                }
//            }
//            return true;
//        }
//        return false;
    }

    public boolean DeleteItem(){
//        if(SystemShop.getItemLists().contains(item)){
//            SystemShop.itemLists.remove(item);
//            System.out.println("Delete success");
//            return true;
//        }
//        return false;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        item = SystemShop.itemInfoSelected;
        ItemInfoId.setText(item.getId());
        ItemInfoTitle.setText(item.getTitle());
        ItemInfoGenre.setItems(FXCollections.observableArrayList("0", "1", "2", "3", "4"));
        ItemInfoGenre.setValue(item.getGenre());
        ItemInfoQuantity.setText(Integer.toString(item.getNumberOfCopies()));
    }
}
