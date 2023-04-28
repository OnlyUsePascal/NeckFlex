package com.groupproject.controller.page;

import com.groupproject.controller.component.ItemBoxController;
import com.groupproject.entity.generic.Item;
import com.groupproject.toolkit.GetterPath;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
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

    TranslateTransition moveTile;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveTile = new TranslateTransition(Duration.seconds(0.3));
        // addItemTile(itemTileDvd, null);
        // addItemTile(itemTileGame, null);
        // addItemTile(itemTileRecord, null);

        new Thread(() -> {
            Platform.runLater(() -> addItemTile(itemTileDvd, null));
        }).start();

        new Thread(() -> {
            Platform.runLater(() -> addItemTile(itemTileGame, null));
        }).start();

        new Thread(() -> {
            Platform.runLater(() -> addItemTile(itemTileRecord, null));
        }).start();
    }

    public void addItemTile(HBox itemTile, ArrayList<Item> itemList){
        try{
            for (int i = 0; i < 2; i++) {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(GetterPath.getComponentItemBox()));
                Button itemBox = (Button) fxmlLoader.load();

                ItemBoxController itemBoxController = fxmlLoader.getController();
                itemBoxController.setTitle("" + i);
                itemTile.getChildren().add(itemBox);
                // itemTileDvd.getChildren().addAll
            }

        } catch (IOException err) {
            err.printStackTrace();
        }
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
        double offset = ((Button) itemTileDvd.getChildren().get(0)).getWidth();
        if (btnId.equals("moveLeft")){
            moveTile.setByX(offset);
        } else {
            moveTile.setByX(-offset);
        }

        moveTile.play();
    }
}
