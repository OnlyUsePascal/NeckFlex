package com.groupproject;

public class CartItem {
    private String name;
    private int amount;
    private String genre;

    public CartItem(String name, int amount, String genre) {
        this.name = name;
        this.amount = amount;
        this.genre = genre;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
