package com.groupproject;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.util.GregorianCalendar;

public class Item {
    private String name;
    private Integer amount;
    private Double price;
    private String genre;
    private String category;
    private Button button;
    private GregorianCalendar date;
    private Scene scene;
    private ItemPageController controller;

    public Item(String name, Integer amount, String genre, Double price, GregorianCalendar date, String category) {
        this.name = name;
        this.amount = amount;
        this.genre = genre;
        this.price = price;
        this.date = date;
        this.category = category;
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

    public String getCategory() {
        return category;
    }

    public String getDateString() {
        return date.toString();
    }

    public GregorianCalendar getDate() {
        return date;
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

    public Button getButton() {
        return button;
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
