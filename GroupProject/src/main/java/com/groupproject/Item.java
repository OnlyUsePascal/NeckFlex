package com.groupproject;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class Item {
    private String name;
    private Integer amount;
    private Double price;
    private String genre;
    private Button button;
    private Scene scene;
    private ItemPageController controller;

    public Item(String name, Integer amount, String genre, Double price) {
        this.name = name;
        this.amount = amount;
        this.genre = genre;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Integer getAmount() {
        return amount;
    }

    public String getGenre() {
        return genre;
    }

    public Double getPrice() {
        return price;
    }

    public void setButton(Button button) {
        this.button = button;
        this.button.setOnAction(event -> {
            Stage stage = new Stage();
            stage.setScene(this.scene);
            stage.setTitle(this.name);
            stage.show();
        });
    }

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public ItemPageController getController() {
        return controller;
    }

    public void setController(ItemPageController controller) {
        this.controller = controller;
    }

}
