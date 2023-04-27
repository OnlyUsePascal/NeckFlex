package com.groupproject;

import java.util.ArrayList;
import java.util.HashMap;

public class SystemShop {
    static private ArrayList<Account> accountLists = new ArrayList<>();
    static private HashMap<String, String> usernamePasswordMap = new HashMap<>();

    static public Account loginAccount;

    public SystemShop() {
        this.accountLists = new ArrayList<>();
        this.usernamePasswordMap = new HashMap<>();
    }

    public static void addAccount(Account account){
        accountLists.add(account);
        usernamePasswordMap.put(account.getUsername(), account.getPassword());
    }

    public static boolean checkUsername(String username){
        return usernamePasswordMap.containsKey(username);
    }

    public static ArrayList<Account> getAccountLists() {
        return accountLists;
    }

    public void displayAccount(){
        System.out.println("Display Info:");
        for (Account account : accountLists) {
            System.out.println(account);
        }
    }
}
