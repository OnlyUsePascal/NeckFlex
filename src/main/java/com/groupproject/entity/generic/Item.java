package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.EntityHandler;

import java.text.DecimalFormat;
import java.util.ArrayList;

public abstract class Item {
    private String id;
    private String title;

    private int category;
    private int genre;
    private int stock;
    // private int year;
    private String year;
    private double price;
    private String desc;
    static public int genericIdOrder = 1;


    public Item(){}
    //restore
    public Item(ArrayList<String> infoList) {
        this.id = infoList.get(ConstantItem.ItemInfo.ID.ordinal());
        this.title = infoList.get(ConstantItem.ItemInfo.TITLE.ordinal());
        this.year = getYearFromId(id);
        this.category = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.CATEGORY.ordinal()));
        this.genre = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.GENRE.ordinal()));
        this.stock = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.STOCK.ordinal()));
        this.price = Double.parseDouble(infoList.get(ConstantItem.ItemInfo.PRICE.ordinal()));
        this.desc = infoList.get(ConstantItem.ItemInfo.DESC.ordinal());
    }

    //copy
    public Item(String id, String title, int category, int genre, int stock, double price, String desc) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.genre = genre;
        this.stock = stock;
        this.year = getYearFromId(id);
        this.price = price;
        this.desc = desc;
    }

    // get new
    public Item(String title, int category, int genre, int stock, String year, double price, String desc) {
        this.id = getIdFromYear(year);
        this.title = title;
        this.category = category;
        this.genre = genre;
        this.stock = stock;
        this.year = year;
        this.price = price;
        this.desc = desc;
    }

    static public Item getRestoreItem(ArrayList<String> infoList) {
        int category = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.CATEGORY.ordinal()));
        switch (category) {
            case 0 -> {
                return new ItemDvd(infoList);
            }
            case 1 -> {
                return new ItemRecord(infoList);
            }
            case 2 -> {
                return new ItemGame(infoList);
            }
        }
        return null;
    }

    static public Item getNewItem(String title, int category,
                                  int genre, int stock,
                                  String year, double price, String desc){
        switch (category) {
            case 0 -> {
                return new ItemDvd(title, category, genre, stock, year, price, desc);
            }
            case 1 -> {
                return new ItemRecord(title, category, genre, stock, year, price, desc);
            }
            case 2 -> {
                return new ItemGame(title, category, genre, stock, year, price, desc);
            }
        }
        return null;
    }

    static public Item getCopyItem(Item item) {
        int category = item.getCategory();
        switch (category) {
            case 0 -> {
                return new ItemDvd(item.getId(), item.getTitle(),
                        item.getCategory(), item.getGenre(),
                        item.getStock(), item.getPrice(), item.getDesc());
            }
            case 1 -> {
                return new ItemRecord(item.getId(), item.getTitle(),
                        item.getCategory(), item.getGenre(),
                        item.getStock(), item.getPrice(), item.getDesc());
            }
            case 2 -> {
                return new ItemGame(item.getId(), item.getTitle(),
                        item.getCategory(), item.getGenre(),
                        item.getStock(), item.getPrice(), item.getDesc());
            }
        }
        return null;
    }

    // --- GET ---
    public static String getIdFromYear(String year) {
        DecimalFormat df = new DecimalFormat("000");
        String code = df.format(genericIdOrder);
        String formattedYear = String.format("%04d", Integer.parseInt(year));
        return "I" + code + "-" + formattedYear;
    }

    public static String getYearFromId(String id) {
        return id.substring(id.length() - 4);
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

    public String getYear() {
        return year;
    }

    public boolean isAvailable() {
        return stock > 0;
    }

    public boolean isDeleted(){
        return EntityHandler.getItemIndex(this) == -1;
    }

    public String getDesc() {
        return desc;
    }

    // --- SET ---
    public static void updateGenericId(String itemId){
        int curIdOrder = Integer.parseInt(itemId.substring(1,4));
        if (curIdOrder >= genericIdOrder) {
            genericIdOrder = curIdOrder + 1;
        }

        System.out.println("genId:" + genericIdOrder);
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

    public void updateStock(int amount) {
        this.stock += amount;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return this.id + "|" + this.title + "|" +
                this.category + "|" + this.genre + "|" +
                this.stock + "|" + this.price + "|" +
                this.desc;
    }

    public int getGenre() {
        return genre;
    }
}
