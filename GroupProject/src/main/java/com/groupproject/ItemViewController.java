package com.groupproject;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ItemViewController implements Initializable {
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<CartItem> cart = new ArrayList<>();

    @FXML
    private VBox buttonContainer;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        items.add(new Item("adventures of dat", 12, "home video", 123.4));
        items.add(new Item("swallowing his genuine sentiments", 7, "thriller", 15.6));
        items.add(new Item("up", 3, "kids", 145.0));
        items.add(new Item("love money rock'n'roll", 4, "romance", 987.1));

        for (int i = 0 ; i < 4; i++) {
            String filePath = "ItemPage.fxml";

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
                Scene scene = new Scene(fxmlLoader.load());

                items.get(i).setController(fxmlLoader.getController());

                items.get(i).getController().setTitle(items.get(i).getName());
                items.get(i).getController().setQuantity(items.get(i).getAmount());
                items.get(i).getController().setGenre(items.get(i).getGenre());
                items.get(i).getController().setPrice(items.get(i).getPrice());
                items.get(i).getController().setCart(cart);

                Button button = new Button();
                button.setText(items.get(i).getName());

                items.get(i).setButton(button);
                items.get(i).setScene(scene);

                buttonContainer.getChildren().add(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
