package com.groupproject;

import java.util.ArrayList;

public class SystemShop {
    static private ArrayList<Account> accountLists;

    public SystemShop() {
        this.accountLists = new ArrayList<>();
    }

    public static void addAccount(Account account){
        accountLists.add(account);
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
