package com.groupproject.entity;

import com.groupproject.controller.ViewHandler;
import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.*;
import com.groupproject.toolkit.PathHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityHandler {
    // model
    static private ArrayList<Account> accountList = new ArrayList<>();
    static private HashMap<String, Account> usernameToObject = new HashMap<>();
    static private Account currentUser;
    static private Cart currentCart;

    static private ArrayList<Item> itemList = new ArrayList<>();
    static private ArrayList<Item> itemDvdList = new ArrayList<>();
    static private ArrayList<Item> itemRecordList = new ArrayList<>();
    static private ArrayList<Item> itemGameList = new ArrayList<>();

    static private ArrayList<BankAccount> bankAccountList = new ArrayList<>();

    static public String blankInput = "N/A";


    //===================== AUTH ======================
    // logging
    static public void logOut() {
        AnchorPane loginPane = ViewHandler.getAnchorPane(PathHandler.getPageLoginMain());
        Scene scene = new Scene(loginPane);
        ViewHandler.getCurrentStage().setScene(scene);
    }

    static public boolean logIn(String username, String pwd){
        Account account = accountFromUsername(username);

        if (account == null) return false;
        if (!account.getPassword().equals(pwd)) return false;

        setCurrentUser(account);
        return true;
    }


    //===================== ACCOUNT ======================
    static public ArrayList<Account> getAccountList() {
        return accountList;
    }

    static public Account accountFromUsername(String username) {
        return usernameToObject.get(username);
    }

    static public int accountListLength() {
        return accountList.size();
    }

    static public boolean accountIsExist(String username) {
        return (accountFromUsername(username) != null);
    }

    static public void addAccount(Account account) {
        accountList.add(account);
        usernameToObject.put(account.getUsername(), account);
    }

    static public void registerAccount(String username, String password,
                                       String firstName, String lastName,
                                       String address, String phoneNumber,
                                       boolean isAdmin){
        Account account = Account.getNewAccount(username, password, firstName, lastName, address, phoneNumber, isAdmin);
        addAccount(account);
    }

    static public void restoreAccount(ArrayList<String> infoList){
        Account account = Account.getRestoreAccount(infoList);
        addAccount(account);
    }

    static public Account getCurrentUser() {
        return currentUser;
    }

    static public void setCurrentUser(Account acc) {
        currentUser = acc;
        if (!currentUser.isAdmin()) currentCart = ((AccountCustomer) currentUser).getCart1();
    }

    //===================== ITEM ======================
    static public void restoreItem(ArrayList<String> infoList) {
        Item item = getRestoreItem(infoList);
        addItem(item);
    }

    static public void addItem(Item item) {
        itemList.add(item);

        if (item instanceof ItemDvd)
            itemDvdList.add(item);
        else if (item instanceof ItemRecord)
            itemRecordList.add(item);
        else if (item instanceof ItemGame)
            itemGameList.add(item);

        Item.updateGenericId(item.getId());
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
                                  String year, double price){
        switch (category) {
            case 0 -> {
                return new ItemDvd(title, category, genre, stock, year, price);
            }
            case 1 -> {
                return new ItemRecord(title, category, genre, stock, year, price);
            }
            case 2 -> {
                return new ItemGame(title, category, genre, stock, year, price);
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
                        item.getStock(), item.getPrice());
            }
            case 1 -> {
                return new ItemRecord(item.getId(), item.getTitle(),
                        item.getCategory(), item.getGenre(),
                        item.getStock(), item.getPrice());
            }
            case 2 -> {
                return new ItemGame(item.getId(), item.getTitle(),
                        item.getCategory(), item.getGenre(),
                        item.getStock(), item.getPrice());
            }
        }
        return null;
    }

    static public Item findItem(String id){
        for (Item item : itemList) {
            if (item.getId().equals(id))
                return item;
        }
        return null;
    }

    static public void removeItem(Item item) {
        // instance
        itemList.remove(item);
        if (item instanceof ItemDvd)
            itemDvdList.remove(item);
        else if (item instanceof ItemRecord)
            itemRecordList.remove(item);
        else if (item instanceof ItemGame)
            itemGameList.remove(item);

        // cart
        currentCart.refreshCart();
    }

    static public ArrayList<Item> getItemList() {
        return itemList;
    }

    static public ArrayList<Item> getItemDvdList() {
        return itemDvdList;
    }

    static public ArrayList<Item> getItemRecordList() {
        return itemRecordList;
    }

    static public ArrayList<Item> getItemGameList() {
        return itemGameList;
    }

    static public int getItemIndex(Item item) {
        return itemList.indexOf(item);
    }


    //===================== CART ======================
    static public Cart getCart() {
        return ((AccountCustomer) currentUser).getCart1();
    }

    static public CartDetail getCartDetail(Item item) {
        return currentCart.findCartDetail(item);
    }

    static public void addCartDetail(Item item, int quantity) {
        currentCart.addCartDetail(item, quantity);
    }


    //===================== ORDER ======================

    //===================== BANK ======================
    static public void addRestoredBankAcc(String number, String branch, double balance){
        BankAccount newBankAcc = new BankAccount(number, branch, balance);
        bankAccountList.add(newBankAcc);
    }

    static public ArrayList<BankAccount> getBankAccountList() {
        return bankAccountList;
    }

    static public BankAccount findBankAccount(String number, String brand){
        for (BankAccount bankAccount : bankAccountList) {
            if (bankAccount.correctInfo(number, brand))
                return bankAccount;
        }
        return null;
    }

}
