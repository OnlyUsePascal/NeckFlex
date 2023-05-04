package com.groupproject.entity.runtime;

import com.groupproject.entity.generic.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ShopSystem {
    static private ArrayList<Account> accountList = new ArrayList<>();
    static private HashMap<String, Account> usernameToObject = new HashMap<>();

    static private ArrayList<Item> itemList = new ArrayList<>();
    static private ArrayList<Item> itemDvdList = new ArrayList<>();
    static private ArrayList<Item> itemRecordList = new ArrayList<>();
    static private ArrayList<Item> itemGameList = new ArrayList<>();


    public ShopSystem() {
        this.accountList = new ArrayList<>();
        this.usernameToObject = new HashMap<>();
    }

    //===================== ACCOUNT ======================
    public static ArrayList<Account> getAccountList(){
        return accountList;
    }

    public static Account getAccountFromUsername(String username){
        return usernameToObject.get(username);
    }

    public static int getAccountListLength(){
        return accountList.size();
    }

    public static boolean checkAccountExist(String username){
        return (getAccountFromUsername(username) != null);
    }

    public static void addAccount(Account account){
        accountList.add(account);
        usernameToObject.put(account.getUsername(), account);
    }

    //===================== ITEM ======================
    public static void addItem(Item item){
        itemList.add(item);

        if (item instanceof ItemDvd)
            itemDvdList.add(item);
        else if (item instanceof ItemRecord)
            itemRecordList.add(item);
        else if (item instanceof ItemGame)
            itemGameList.add(item);
    }

    public static ArrayList<Item> getItemList(){
        return itemList;
    }
}
