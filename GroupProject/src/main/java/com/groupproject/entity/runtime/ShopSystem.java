package com.groupproject.entity.runtime;

import com.groupproject.entity.generic.Account;

import java.util.ArrayList;
import java.util.HashMap;

public class ShopSystem {
    static private ArrayList<Account> accountList = new ArrayList<>();
    static private HashMap<String, Account> usernameToObject = new HashMap<>();


    public ShopSystem() {
        this.accountList = new ArrayList<>();
        this.usernameToObject = new HashMap<>();
    }

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



}
