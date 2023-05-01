package com.groupproject;

import java.text.DecimalFormat;

public abstract class Item {
    private String id;
    private String title;

    private String category;
    private String rentalType;
    private String loanType;
    private int numberOfCopies;
    private double rentalFee;
    private String rentalStatus;

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
