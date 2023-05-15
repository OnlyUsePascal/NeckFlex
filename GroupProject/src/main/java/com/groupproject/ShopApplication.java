package com.groupproject;

import com.groupproject.entity.generic.Account;
import com.groupproject.entity.generic.Cart;
import com.groupproject.entity.generic.ItemDvd;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.toolkit.DataHandler;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

import java.io.IOException;

public class ShopApplication extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        DataHandler.getData();

        //init data
        EntityHandler.currentUserGet(Account.getNewAccount("joun", "123", "dat", "pham", "google", "01234"));
        // System.out.println();
        Cart cart = EntityHandler.cartGet();
        cart.cartDetailAdd(new ItemDvd("item1", 12.4), 2);
        // cart.cartDetailAdd(new ItemDvd("item2", 1), 1);
        EntityHandler.orderAdd();

        // cart.cartDetailAdd(new ItemDvd("item55", 11.3), 3);
        // cart.cartDetailAdd(new ItemDvd("ifasdf", 1.4), 2);
        // EntityHandler.orderAdd();


        //init
        ViewHandler.currentStageSet(stage);
        String pageFile = PathHandler.getPageLoginMain();
        pageFile = PathHandler.getPageHome();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(pageFile));
        Scene scene = new Scene(fxmlLoader.load(), 1600, 900);
        stage.setTitle("Hello!");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();

        //on close
        stage.setOnCloseRequest(event -> {
            postClose(event, stage);
        });
    }

    public void postClose(WindowEvent event, Stage stage){
        // event.consume();
        //
        // DataHandler.saveData();
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}