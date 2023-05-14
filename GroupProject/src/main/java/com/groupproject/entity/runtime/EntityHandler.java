package com.groupproject.entity.runtime;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.*;
import com.groupproject.toolkit.PathHandler;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;

import java.util.ArrayList;
import java.util.HashMap;

public class EntityHandler {
    //model
    static private ArrayList<Account> accountList = new ArrayList<>();
    static private HashMap<String, Account> usernameToObject = new HashMap<>();
    static private Account currentUser;
    static private Cart currentCart;

    static private ArrayList<Item> itemList = new ArrayList<>();
    static private ArrayList<Item> itemDvdList = new ArrayList<>();
    static private ArrayList<Item> itemRecordList = new ArrayList<>();
    static private ArrayList<Item> itemGameList = new ArrayList<>();


    //===================== AUTH ======================
    //logging
    static public void logOut(){
        AnchorPane loginPane = ViewHandler.anchorPaneGet(PathHandler.getPageLoginMain());
        Scene scene = new Scene(loginPane);
        ViewHandler.currentStageGet().setScene(scene);
    }

    //===================== ACCOUNT ======================
    static public ArrayList<Account> getAccountList() {
        return accountList;
    }

    static public Account getAccountFromUsername(String username) {
        return usernameToObject.get(username);
    }

    static public int getAccountListLength() {
        return accountList.size();
    }

    static public boolean checkAccountExist(String username) {
        return (getAccountFromUsername(username) != null);
    }

    static public void addAccount(Account account) {
        accountList.add(account);
        usernameToObject.put(account.getUsername(), account);
    }

    static public Account getCurrentUser() {
        return currentUser;
    }

    static public void setCurrentUser(Account acc) {
        currentUser = acc;
        currentCart = currentUser.getCart();
    }

    //===================== ITEM ======================
    static public void addItem(Item item) {
        itemList.add(item);

        if (item instanceof ItemDvd)
            itemDvdList.add(item);
        else if (item instanceof ItemRecord)
            itemRecordList.add(item);
        else if (item instanceof ItemGame)
            itemGameList.add(item);
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

    static public Item getItemFromInfo(ArrayList<String> infoList) {
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

    static public Item getItemFromInfo(Item item) {
        int category = item.getCategory();
        switch (category) {
            case 0 -> {
                return ItemDvd.getNewItemDvd(item.getTitle(), item.getGenre(), item.getPrice(), item.getYear(), item.getStock());
            }
            case 1 -> {
                return ItemRecord.getNewItemRecord(item.getTitle(), item.getGenre(), item.getPrice(), item.getYear(), item.getStock());
            }
            case 2 -> {
                return ItemGame.getNewItemGame(item.getTitle(), item.getGenre(), item.getPrice(), item.getYear(), item.getStock());
            }
        }
        return null;
    }

    //===================== CART ======================
    static public Cart getCart() {
        return currentUser.getCart();
    }

    static public CartDetail findCartDetail(Item item) {
        return currentCart.findCartDetail(item);
    }

    static public void addCartDetail(Item item, int quantity) {
        currentCart.addCartDetail(item, quantity);
    }


    //===================== ORDER ======================
    static public void makeOrder() {
        Order newOrder = Order.getNewOrder(currentCart.getcartDetailList());
        currentUser.addOrder(newOrder);
        currentCart.wipeCart();
    }
}
