package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;

import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Item {
    private String id;
    private String title;

    private int category;
    private int genre;
    private int stock;
    private int year;
    private double price;

    static public int genericIdOrder = 1;

    public Item(int year, String title, int category, int genre, int stock, double price) {
        this.year = year;
        this.id = getIdFromYear(year);
        this.title = title;
        this.category = category;
        this.genre = genre;
        this.stock = stock;
        this.price = price;

        updateGenericId();
    }

    public Item(ArrayList<String> infoList) {
        this.id = infoList.get(ConstantItem.ItemInfo.ID.ordinal());
        this.title = infoList.get(ConstantItem.ItemInfo.TITLE.ordinal());
        this.year = getYearFromId(id);
        this.category = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.CATEGORY.ordinal()));
        this.genre = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.GENRE.ordinal()));
        this.stock = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.STOCK.ordinal()));
        this.price = Double.parseDouble(infoList.get(ConstantItem.ItemInfo.PRICE.ordinal()));

        updateGenericId();
    }

    public static String getIdFromYear(int year) {
        DecimalFormat df = new DecimalFormat("000");
        String code = df.format(genericIdOrder);
        String formattedYear = String.format("%04d", year);

        genericIdOrder++;
        return "I" + code + "-" + formattedYear;
    }

    public static int getYearFromId(String id) {
        return Integer.parseInt(id.substring(id.length() - 4));
    }

    public void updateGenericId(){
        int curIdOrder = Integer.parseInt(id.substring(1,4));
        if (curIdOrder >= genericIdOrder) {
            genericIdOrder = curIdOrder + 1;
        }

        System.out.println("genId:" + genericIdOrder);
    }

    public String getImgName(){
        return id + ".png";
    }

    public int getCategory() {
        return category;
    }

    public String getCategoryString() {
        return ConstantItem.categoryList[category];
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public String getGenreString() {
        return ConstantItem.genreList[genre];
    }

    public String getId() {
        return id;
    }

    public int getStock() {
        return stock;
    }

    public int getYear() {
        return year;
    }

    public boolean isAvailable() {
        return stock > 0;
    }


    public void setTitle(String title) {
        this.title = title;
    }

    public void setCategory(int category) {
        this.category = category;
    }

    public void setGenre(int genre) {
        this.genre = genre;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.title + "|" +
                this.category + "|" + this.genre + "|" +
                this.stock + "|" + this.price;
    }
}
