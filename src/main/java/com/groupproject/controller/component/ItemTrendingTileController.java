package com.groupproject.controller.component;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
import com.groupproject.controller.ViewHandler;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.util.Duration;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemTrendingTileController implements Initializable {
    @FXML
    private Label title;
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private HBox container;
    @FXML
    private VBox loadingScreen;

    private TranslateTransition moveTileAnimation;
    private final double moveSz = 1;
    private final int listSz = 9;
    private final int rowSz = 5;
    private int pgCnt;
    private int curPg = 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveTileAnimation = new TranslateTransition(Duration.seconds(0.3), container);
        pgCnt = (((int) listSz) - rowSz) / (int) moveSz + 1;
        ViewHandler.lockHorizonScroll(scrollPane);
    }

    // --- MAIN ---
    public void moveItemTile(ActionEvent event) {
        if (container.getChildren().isEmpty()) return;
        double offset = ((Button) container.getChildren().get(0)).getWidth() * moveSz + container.getSpacing();
        Button btn = (Button) event.getSource();

        if (btn.getId().equals("moveLeft")) {
            if (curPg == 1) return;
            curPg -= 1;
            moveTileAnimation.setByX(offset);
        } else {
            if (curPg == pgCnt) return;
            curPg += 1;
            moveTileAnimation.setByX(-offset);
        }

        moveTileAnimation.play();
    }

    // --- BACK ---
    public void setData(ConstantItem.ItemCategory category, ArrayList<Item> itemList) {
        title.setText(category.toString());
        ViewHandler.toggleNode(loadingScreen, true);

        new Thread(() -> {
            ArrayList<Button> itemBoxList = getItemBoxList(itemList);
            // ViewHandler.fakeLoading();

            Platform.runLater(() -> {
                container.getChildren().addAll(itemBoxList);
                ViewHandler.toggleNode(loadingScreen, false);
            });
        }).start();
    }

    public ArrayList<Button> getItemBoxList(ArrayList<Item> itemList) {
        ArrayList<Button> itemBoxList = new ArrayList<>();
        // get available item
        for (Item item : itemList) {
            if (item.isAvailable()) {
                Button itemBox = ViewHandler.getItemBox(item);
                itemBoxList.add(itemBox);

                if (itemBoxList.size() == listSz) break;
            }

        }

        // shuffle itemBoxList
        for (int i = 0; i < itemBoxList.size(); i++) {
            int j = (int) (Math.random() * itemBoxList.size());
            Button temp = itemBoxList.get(i);
            itemBoxList.set(i, itemBoxList.get(j));
            itemBoxList.set(j, temp);
        }

        return itemBoxList;
    }

}
