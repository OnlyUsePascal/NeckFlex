package com.groupproject.Item;

import com.groupproject.HelloApplication;
import com.groupproject.types.SystemShop;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;
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

    public boolean UpdateItem() throws IOException {
        System.out.println(item);
        System.out.println("Update Item!!!");
        SystemShop.displayItem();
        if(SystemShop.getItemLists().contains(item)){
            item.setTitle(ItemInfoTitle.getText());
            item.setGenre(ItemInfoGenre.getValue().toString());
            item.setNumberOfCopies(Integer.parseInt(ItemInfoQuantity.getText()));

            System.out.println(item);
            System.out.println("Update success");
            for (Item item_ : SystemShop.itemLists) {
                if(item_.getId().equals(this.item.getId())){
                    item_ = this.item;
                }
            }
            SystemShop.displayItem();
            //Load ItemView
            Stage newstage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ItemView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
            newstage.setResizable(false);
            newstage.setTitle("Hello!");
            newstage.setScene(scene);
            newstage.show();
            return true;
        }else{
            System.out.println("Update fail");
            return false;
        }

    }

    public void DecreaseStock(){
        ItemInfoQuantity.setText(Integer.toString(Math.max(Integer.parseInt(ItemInfoQuantity.getText())-1, 0)));
    }

    public void IncreaseStock(){
        ItemInfoQuantity.setText(Integer.toString(Integer.parseInt(ItemInfoQuantity.getText())+1));
    }

    public boolean DeleteItem() throws IOException {
        System.out.println("Deleting Item!!!");
//        SystemShop.displayItem();
        if(SystemShop.getItemLists().contains(item)){
            SystemShop.getItemLists().remove(item);
            System.out.println("Delete success");
//            SystemShop.displayItem();
            //Load ItemView
            Stage newstage = new Stage();
            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ItemView.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
            newstage.setResizable(false);
            newstage.setTitle("Hello!");
            newstage.setScene(scene);
            newstage.show();
            return true;
        }
        return false;
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
