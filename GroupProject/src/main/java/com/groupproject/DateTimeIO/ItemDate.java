package com.groupproject.DateTimeIO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

enum DateType {
    TWO_DAY,
    ONE_WEEK
}

class Item{
    LocalDateTime startDate;
    DateType type;

    public Item(LocalDateTime startDate, DateType type){
        this.startDate = startDate;
        this.type = type;
    }
}

public class ItemDate {

    static ArrayList<Item> itemList = new ArrayList<>();

    public static void printDate(LocalDateTime date){
        //printout Year, Month, Day
        System.out.println(date.getYear() + "," + date.getMonthValue() + "," + date.getDayOfMonth());
    }

    public static void show(){
        for(Item item : itemList){
            //print out startDate, type and endDate, if type is 2_DAY print out endDate = startDate + 2 days else endDate = startDate + 7 days
            LocalDateTime endDate = item.startDate.plusDays(item.type == DateType.TWO_DAY ? 2 : 7);
            System.out.println("Start Date: ");
            printDate(item.startDate);
            System.out.println("Type: " + item.type);
            System.out.println("End Date: ");
            printDate(endDate);
            System.out.println("====================================");

        }
    }
    public static void main(String[] args) {

        int year = 2023;
        int month = 12;
        int day = 27;
        LocalDate date = LocalDate.of(year, month, day);
        LocalDateTime dateTime1 = date.atStartOfDay();
        Item item3 = new Item(dateTime1, DateType.ONE_WEEK);

        Item item1 = new Item(LocalDateTime.now(), DateType.TWO_DAY);
        Item item2 = new Item(LocalDateTime.now(), DateType.ONE_WEEK);



        itemList.add(item1);
        itemList.add(item2);
        itemList.add(item3);
        show();

    }
}
