package com.groupproject.controller.page;

import com.groupproject.controller.component.ItemBoxController;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    @FXML
    private Label pageIndex;

    private final int rowSize = 5;
    private final int rowPerPage = 4;
    private final int pageSize = rowSize * rowPerPage;
    private int itemCnt;
    private int pageCnt;
    private int currentPage;

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        ArrayList<Item> itemList = EntityHandler.getItemList();
        itemCnt = itemList.size();
        // itemCnt = 12;
        pageCnt = itemCnt / pageSize;
        currentPage = 0;

        refreshPage();
    }

    public void refreshPage(){
        pageContainer.getChildren().clear();

        for (int i = 0; i < rowPerPage; i++) {
            HBox itemRow = new HBox();

            // item box
            int min = currentPage * pageSize + i * rowSize;
            int max = Math.min(currentPage * pageSize + (i + 1) * rowSize, itemCnt);
            for (int j = min; j < max; j++) {
                Item item = EntityHandler.getItem(j);
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

    public void changePage(ActionEvent event){
        Button button = (Button) event.getSource();
        String id = button.getId();

        if (id.equals("pagePrev")) {
            if (currentPage <= 0) return;
            currentPage--;
        } else {
            if (currentPage >= pageCnt) return;
            currentPage++;
        }

        refreshPage();
        pageIndex.setText((currentPage + 1) + "");
    }
}
