package com.groupproject.controller.page;

import com.groupproject.controller.component.ItemTrendingTileController;
import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.EntityHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemTrendingController implements Initializable {
    @FXML
    private VBox tileContainer;

    private TranslateTransition moveTile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveTile = new TranslateTransition(Duration.seconds(0.3));


        Platform.runLater(() -> {
            addItemTile(ConstantItem.ItemCategory.DVD, EntityHandler.getItemDvdList());
        });

        Platform.runLater(() -> {
            addItemTile(ConstantItem.ItemCategory.RECORD, EntityHandler.getItemRecordList());
        });

        Platform.runLater(() -> {
            addItemTile(ConstantItem.ItemCategory.GAME, EntityHandler.getItemGameList());
        });
    }

    // --- BACK ---
    public void addItemTile(ConstantItem.ItemCategory category, ArrayList<Item> itemList){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getComponentItemTrendingTile()));
            Node tile = loader.load();
            ItemTrendingTileController itemTrendingTileController = loader.getController();

            itemTrendingTileController.setData(category, itemList);
            tileContainer.getChildren().add(tile);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }
}
