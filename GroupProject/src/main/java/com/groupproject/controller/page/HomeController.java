package com.groupproject.controller.page;

import com.groupproject.toolkit.PathGetter;
import javafx.animation.TranslateTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    Label labelSample;

    @FXML
    AnchorPane menuPane;

    @FXML
    AnchorPane pageContent;

    boolean menuIsOpen = false;
    TranslateTransition menuToOpen, menuToClose;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // labelSample.setText("Hello World!");

        menuToOpen = new TranslateTransition(Duration.seconds(0.5), menuPane);
        menuToOpen.setByX(+300);

        menuToClose = new TranslateTransition(Duration.seconds(0.5), menuPane);
        menuToClose.setByX(-300);

        // menuPane.setTranslateX(-300);

        String pageFile = PathGetter.getItemTrending();
        pageFile = "/com/groupproject/fxml/page/ItemTrending.fxml";

        FXMLLoader pageView = new FXMLLoader(getClass().getResource(pageFile));
        try{
            AnchorPane pageResult = (AnchorPane) pageView.load();
            pageContent.getChildren().add(pageResult);
        } catch (IOException err){
            err.printStackTrace();
        }
    }

    public void menuActive(){
        System.out.println("Menu active");
        if (menuIsOpen){
            menuIsOpen = false;
            menuToClose.play();
        } else {
            menuIsOpen = true;
            menuToOpen.play();
        }
    }

    public void changeContent(ActionEvent event){
        Button btn = (Button) event.getSource();
        String btnId = btn.getId();
        // System.out.println(btnId);

        try {
            String pageFile = "/com/groupproject/fxml/page/" + btnId + ".fxml";
            // System.out.println(pageFile);
            FXMLLoader pageView = new FXMLLoader(getClass().getResource(pageFile));

            AnchorPane pageResult = (AnchorPane) pageView.load();
            // pageContent.getChildren().add(pageResult);
            pageContent.getChildren().set(0,pageResult);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
