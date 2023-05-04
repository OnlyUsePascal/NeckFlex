package com.groupproject.controller.page;

import com.groupproject.controller.component.ItemBoxController;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.toolkit.PathHandler;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.ScrollEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.io.IOException;
import java.util.ArrayList;

public class ItemAllController extends ItemController implements Initializable {
    @FXML
    HBox itemPage;
    @FXML
    ScrollPane itemPageScrollPane;
    @FXML
    VBox pageContainer;
    @FXML
    ScrollPane pageContainerSrollPane;


    TranslateTransition moveTile;
    final int pgSize = 12;
    double itemPageWidth;
    double itemPageHeight;
    int curPage;
    int pageCnt;

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        //item
        curPage = 1;
        ArrayList<Item> itemList = ShopSystem.getItemList();
        initItemToTile(itemList);
        initSizeListener();
        initScrollLock(itemPageScrollPane);

        //random
        double niceWidth = pageContainerSrollPane.getPrefWidth() - 22;
        System.out.println(niceWidth);
        pageContainer.setPrefWidth(niceWidth);

        //transition
        moveTile = new TranslateTransition(Duration.seconds(0.3), itemPage);
    }

    public void initItemToTile(ArrayList<Item> itemList){
        //itempage - vbox (page) - hbox (row) - item ( col)
        int itemCnt = itemList.size();
        // itemCnt = ;
        pageCnt = itemCnt / pgSize + ((itemCnt % pgSize != 0) ? 1 : 0);

        for (int i = 0; i < pageCnt; i++){
            //page
            VBox pagePane = new VBox();
            itemPage.getChildren().add(pagePane);

            //row
            for (int j = i * pgSize; j < Math.min((i+1) * pgSize, itemCnt);j++){
                int rowIdx = (j % pgSize) / 4;
                // System.out.println(i + " " + j + " " + idx + " " +  pagePane.getChildren().size());
                if (rowIdx >= pagePane.getChildren().size()) pagePane.getChildren().add(new HBox());

                //item
                HBox row = (HBox) pagePane.getChildren().get(rowIdx);
                initItemToRow(row, itemList.get(j));
            }
        }

    }

    public void initItemToRow(HBox row, Item item){
        try {
            FXMLLoader btnLoader = new FXMLLoader(getClass().getResource(PathHandler.getComponentItemBox()));
            Button itemBox = (Button) btnLoader.load();
            ItemBoxController itemBoxController = btnLoader.getController();

            itemBoxController.setData(item);
            row.getChildren().add(itemBox);
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    public void initSizeListener(){
        if (itemPage.getChildren().isEmpty()) return;
        VBox pagePane = (VBox) itemPage.getChildren().get(0);
        pagePane.widthProperty().addListener((obs, oldVal, newVal) -> {
            if (itemPageWidth == 0){
                itemPageWidth = newVal.doubleValue();
            }
        });

        pagePane.heightProperty().addListener((obs, oldVal, newVal) -> {
            if (itemPageHeight == 0){
                itemPageHeight = newVal.doubleValue();
                itemPageScrollPane.setMinHeight(itemPageHeight + 10);
            }
        });
    }

    public void moveItemTile(ActionEvent event){
        Button btn = (Button) event.getSource();
        String btnId = btn.getId();

        double offset = itemPageWidth;
        if (btnId.equals("moveLeft")){
            if (curPage > 1){
                curPage--;
                moveTile.setByX(offset);
                moveTile.play();
            }
        } else {
            //next page
            if (curPage < pageCnt){
                curPage++;
                moveTile.setByX(-offset);
                moveTile.play();
            }
        }
    }
}
