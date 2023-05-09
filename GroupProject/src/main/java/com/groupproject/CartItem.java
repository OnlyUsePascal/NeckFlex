package com.groupproject;

public class CartItem {
    private String name;
    private int amount;
    private String genre;
    private double price;

    public CartItem(String name, int amount, String genre, double price) {
        this.name = name;
        this.amount = amount;
        this.genre = genre;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public void addAmount(int amount) {
        this.amount += amount;
    }
}
