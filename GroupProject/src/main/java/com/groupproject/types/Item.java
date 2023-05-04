package com.groupproject.types;

import java.text.DecimalFormat;

public abstract class Item {
    public String id;
    public String title;

    public String category;
    public String rentalType;
    public String loanType;
    public int numberOfCopies;
    public double rentalFee;
    public String rentalStatus;

    static public int itemID = 1;

    public Item(String id, String title, String category, String rentalType, String loanType, int numberOfCopies, double rentalFee, String rentalStatus) {
        this.id = id;
        this.title = title;
        this.category = category;
        this.rentalType = rentalType;
        this.loanType = loanType;
        this.numberOfCopies = numberOfCopies;
        this.rentalFee = rentalFee;
        this.rentalStatus = rentalStatus;
    }

    public static String convertItemId(int year) {
        DecimalFormat df = new DecimalFormat("000");
        String code = df.format(itemID);
        itemID++;
        String formattedYear = String.format("%04d", year);
        return "I" + code + "-" + formattedYear;
    }

    public String Item2Str(){
        return this.id + "|" + this.title + "|" + this.category + "|" + this.rentalType + "|" + this.loanType + "|" + this.numberOfCopies + "|" + this.rentalFee + "|" + this.rentalStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getNumberOfCopies() {
        return numberOfCopies;
    }

    public void setNumberOfCopies(int numberOfCopies) {
        this.numberOfCopies = numberOfCopies;
    }

    @Override
    public String toString() {
        return "Item{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", category='" + category + '\'' +
                ", rentalType='" + rentalType + '\'' +
                ", loanType='" + loanType + '\'' +
                ", numberOfCopies=" + numberOfCopies +
                ", rentalFee=" + rentalFee +
                ", rentalStatus='" + rentalStatus + '\'' +
                '}';
    }

    //    static public Item additem(String title, String genre, String rentalType, String loanType, int numberOfCopies, double rentalFee, String rentalStatus, int year){
//        Item newItem = new Item(convertItemId(year),title, genre, rentalType, loanType, numberOfCopies, rentalFee, rentalStatus);
//        itemID++;
//        return newItem;
//    }
}
