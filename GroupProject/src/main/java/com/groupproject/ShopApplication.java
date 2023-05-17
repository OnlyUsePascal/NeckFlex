package com.groupproject;

import com.groupproject.entity.generic.Cart;
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
        // EntityHandler.setCurrentUser(Account.getNewAccount("joun", "123", "dat", "pham", "google", "01234"));
        EntityHandler.setCurrentUser(EntityHandler.getAccountList().get(0));
        // System.out.println();
        Cart cart = EntityHandler.getCart();
        // cart.addCartDetail(EntityHandler.getItemList().get(12), 2);
        // cart.addCartDetail(EntityHandler.getItemList().get(60), 10);
        // EntityHandler.addOrder();


        //init
        ViewHandler.setCurrentStage(stage);
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
        event.consume();

        DataHandler.saveData();
        stage.close();
    }

    public static void main(String[] args) {
        launch();
    }
}