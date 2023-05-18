package com.groupproject.controller.component;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

import java.io.IOException;
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

    private TranslateTransition moveTileAnimation;
    private final double moveSz = 2;
    private final int listSz = 13;
    private final int rowSz = 5;
    private int pgCnt;
    private int curPg= 1;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        moveTileAnimation = new TranslateTransition(Duration.seconds(0.3), container);
        pgCnt = (((int) listSz) - rowSz) / (int) moveSz ;
        ViewHandler.lockHorizonScroll(scrollPane);
    }

    public void moveItemTile(ActionEvent event){
        // System.out.println("move item tile");
        double offset = ((Button) container.getChildren().get(0)).getWidth() * moveSz;
        Button btn = (Button) event.getSource();

        if (btn.getId().equals("moveLeft")){
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

    public void setData(ConstantItem.ItemCategory category, ArrayList<Item> itemList){
        title.setText(category.toString());

        for (int i = 0 ; i < Math.min(listSz, itemList.size()); i++){
            Button itemBox = ViewHandler.getItemBox(itemList.get(i));
            container.getChildren().add(itemBox);
        }
    }




}
