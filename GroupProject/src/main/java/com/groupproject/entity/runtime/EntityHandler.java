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
    static public ArrayList<Account> accountListGet() {
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

    static public void accountAdd(Account account) {
        accountList.add(account);
        usernameToObject.put(account.getUsername(), account);
    }

    static public Account currentUserGet() {
        return currentUser;
    }

    static public void currentUserGet(Account acc) {
        currentUser = acc;
        currentCart = currentUser.getCart();
    }

    //===================== ITEM ======================
    static public void itemAdd(Item item) {
        itemList.add(item);

        if (item instanceof ItemDvd)
            itemDvdList.add(item);
        else if (item instanceof ItemRecord)
            itemRecordList.add(item);
        else if (item instanceof ItemGame)
            itemGameList.add(item);
    }

    static public ArrayList<Item> itemListAdd() {
        return itemList;
    }

    static public ArrayList<Item> itemDvdListGet() {
        return itemDvdList;
    }

    static public ArrayList<Item> itemRecordListGet() {
        return itemRecordList;
    }

    static public ArrayList<Item> itemGameListGet() {
        return itemGameList;
    }

    static public Item itemFromInfo(ArrayList<String> infoList) {
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

    static public Item itemFromInfo(Item item) {
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
    static public Cart cartGet() {
        return currentUser.getCart();
    }

    static public CartDetail cartDetailGet(Item item) {
        return currentCart.cartDetailFind(item);
    }

    static public void cartDetailAdd(Item item, int quantity) {
        currentCart.cartDetailAdd(item, quantity);
    }


    //===================== ORDER ======================
    static public void orderAdd() {
        Order newOrder = Order.getNewOrder(currentCart.cartDetailListGet());
        currentUser.addOrder(newOrder);
        currentCart.cartWipe();
    }
}
