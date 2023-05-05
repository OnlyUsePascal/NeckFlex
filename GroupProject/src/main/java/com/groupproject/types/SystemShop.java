package com.groupproject.types;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemShop {
    static private ArrayList<Account> accountLists = new ArrayList<>();

    static private ArrayList<DVD> dvdLists = new ArrayList<>();

    static private ArrayList<VideoGame> videoGameLists = new ArrayList<>();
    static private ArrayList<MovieRecord> movieRecordLists = new ArrayList<>();
    static private HashMap<String, String> usernamePasswordMap = new HashMap<>();

    static public ArrayList<Item> itemLists = new ArrayList<>();

    static public Item itemInfoSelected;

    static public Account loginAccount;

    public SystemShop() {
        this.accountLists = new ArrayList<>();
        this.usernamePasswordMap = new HashMap<>();
    }

    public static void addAccount(Account account){
        accountLists.add(account);
        usernamePasswordMap.put(account.getUsername(), account.getPassword());
    }

    public static void addDVD(DVD dvd){
        dvdLists.add(dvd);
    }

    public static void addVideoGame(VideoGame videoGame){
        videoGameLists.add(videoGame);
    }

    public static void addMovieRecord(MovieRecord movieRecord){
        movieRecordLists.add(movieRecord);
    }

    public static void addItem(Item item){
        itemLists.add(item);
    }

    public static boolean checkUsername(String username){
        return usernamePasswordMap.containsKey(username);
    }

    public static ArrayList<Account> getAccountLists() {
        return accountLists;
    }

    public static ArrayList<Item> getItemLists() {
        return itemLists;
    }

    public void displayAccount(){
        System.out.println("Display Info:");
        for (Account account : accountLists) {
            System.out.println(account);
        }
    }

    public static void displayItem(){
        System.out.println("Display Info:");
        for (Item item : itemLists) {
            System.out.println(item);
        }
    }
}
