package com.groupproject.controller.page;

import com.groupproject.controller.component.ItemTrendingTileController;
import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
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
    HBox itemTileDvd;
    @FXML
    HBox itemTileRecord;
    @FXML
    HBox itemTileGame;
    @FXML
    ScrollPane itemPageDvd;
    @FXML
    ScrollPane itemPageRecord;
    @FXML
    ScrollPane itemPageGame;
    @FXML
    private VBox tileContainer;

    private TranslateTransition moveTile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveTile = new TranslateTransition(Duration.seconds(0.3));


        new Thread(() -> {
            Platform.runLater(() -> {
                addItemTile(ConstantItem.ItemCategory.DVD, EntityHandler.getItemDvdList());
            });
        }).start();

        new Thread(() -> {
            Platform.runLater(() -> {
                addItemTile(ConstantItem.ItemCategory.RECORD, EntityHandler.getItemGameList());
            });
        }).start();

        // new Thread(() -> {
        //     Platform.runLater(() -> {
        //         addItemTile(itemTileGame, EntityHandler.itemGameListGet());
        //         ViewHandler.scrollPaneLockScroll(itemPageGame);
        //     });
        // }).start();
    }

    public void addItemTile(ConstantItem.ItemCategory category, ArrayList<Item> itemList){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getComponentItemTrendingTile()));
            Node tile = loader.load();
            ItemTrendingTileController itemTrendingTileController = loader.getController();

            tileContainer.getChildren().add(tile);
            itemTrendingTileController.setData(category, itemList);
        } catch (IOException err) {
            err.printStackTrace();
        }
        // try{
        //     for (Item item : itemList.subList(0,4)){
        //         FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(PathHandler.getComponentItemBox()));
        //         Button itemBox = (Button) fxmlLoader.load();
        //
        //         ItemBoxController itemBoxController = fxmlLoader.getController();
        //         itemBoxController.setData(item);
        //         itemTile.getChildren().add(itemBox);
        //     }
        // } catch (IOException err) {
        //     err.printStackTrace();
        // }
    }

    public void moveItemTile(ActionEvent event){
        //get btn
        Button btn = (Button) event.getSource();
        String btnId = btn.getId();

        //get item tile from button
        HBox tileTitleContainer = (HBox) btn.getParent();
        VBox itemTileContainer = (VBox) tileTitleContainer.getParent();
        ScrollPane scrollPane = (ScrollPane) itemTileContainer.getChildren().get(1);
        HBox itemTile = (HBox) scrollPane.getContent();
        moveTile.setNode(itemTile);

        //move
        double offset = ((Button) itemTileDvd.getChildren().get(0)).getWidth() * 4;
        if (btnId.equals("moveLeft")){
            moveTile.setByX(offset);
        } else {
            moveTile.setByX(-offset);
        }

        moveTile.play();
    }
}