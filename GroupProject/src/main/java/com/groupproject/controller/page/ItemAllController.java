package com.groupproject.controller.page;

import com.groupproject.controller.component.ItemBoxController;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.util.ArrayList;

public class ItemAllController implements Initializable {
    // @FXML
    // HBox itemPage;
    // @FXML
    // ScrollPane itemPageScrollPane;
    @FXML
    private VBox pageContainer;
    @FXML
    private ScrollPane pageContainerSrollPane;

    private int rowCnt;
    private final int rowSize = 5;
    private int itemCnt;
    // TranslateTransition moveTile;
    // final int pgSize = 12;
    // double itemPageWidth;
    // double itemPageHeight;
    // int curPage;
    // int pageCnt;

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        setData();
    }

    public void setData() {
        // getData
        ArrayList<Item> itemList = EntityHandler.itemListAdd();
        itemCnt = itemList.size();
        rowCnt = itemList.size() / rowSize + ((itemCnt % rowSize != 0) ? 1 : 0);

        // init row
        for (int i = 0; i < rowCnt; i++) {
            HBox itemRow = new HBox();

            // item box
            for (int j = i * rowSize; j < Math.min((i + 1) * rowSize, itemCnt); j++) {
                Item item = itemList.get(j);
                Button itemBox = getItemBox(item);
                itemRow.getChildren().add(itemBox);
            }

            pageContainer.getChildren().add(itemRow);
        }

    }

    public Button getItemBox(Item item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getComponentItemBox()));
            Button itemBox = (Button) loader.load();
            ItemBoxController itemBoxController = loader.getController();

            itemBoxController.setData(item);

            return itemBox;
        } catch (IOException err) {
            err.printStackTrace();
            return null;
        }
    }
}
