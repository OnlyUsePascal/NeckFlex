package com.groupproject.entity.generic;

import com.groupproject.toolkit.Constant.ConstantItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Item {
    private String id;
    private String title;

    private int category;
    private int genre;
    private int stock;
    private double price;

    static public int genericId = 1;

    public Item(String title, double price){
        this.title = title;
        this.price = price;
    }

    public Item(String id, String title, int category, int genre, int stock, double price) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.genre = genre;
        this.stock = stock;
        this.price = price;
    }

    public Item(ArrayList<String> infoList) {
        this.id = infoList.get(ConstantItem.ItemInfo.ID.ordinal());
        this.title = infoList.get(ConstantItem.ItemInfo.TITLE.ordinal());
        this.category = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.CATEGORY.ordinal()));
        this.genre = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.GENRE.ordinal()));
        this.stock = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.STOCK.ordinal()));
        this.price = Double.parseDouble(infoList.get(ConstantItem.ItemInfo.FEE.ordinal()));
    }

    public static String getItemId(int year) {
        DecimalFormat df = new DecimalFormat("000");
        String code = df.format(genericId);
        genericId++;
        String formattedYear = String.format("%04d", year);
        return "I" + code + "-" + formattedYear;
    }

    public int getCategory(){
        return category;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.title + "|" + this.category + "|" + this.genre + "|" + this.stock + "|" + this.price;
    }
}
