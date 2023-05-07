package com.groupproject.entity.runtime;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.*;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

public class ShopSystem {
    static private ArrayList<Account> accountList = new ArrayList<>();
    static private HashMap<String, Account> usernameToObject = new HashMap<>();
    static private Account currentUser;

    static private ArrayList<Item> itemList = new ArrayList<>();
    static private ArrayList<Item> itemDvdList = new ArrayList<>();
    static private ArrayList<Item> itemRecordList = new ArrayList<>();
    static private ArrayList<Item> itemGameList = new ArrayList<>();

    static private Cart currentUserCart;


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
        currentUserCart = currentUser.getCart();
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

    static public Item getItemFromInfo(ArrayList<String> infoList){
        int category = Integer.parseInt(infoList.get(ConstantItem.ItemInfo.CATEGORY.ordinal()));
        switch (category){
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

    static public Item getItemFromInfo(Item item){
        int category = item.getCategory();
        switch (category){
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


    //===================== ORDER ======================
    static public void makeOrder() {
        System.out.println("wtf");
        Order order = new Order(currentUser);

        //cart detail -> order detail
        for (CartDetail cartDetail : currentUserCart.getcartDetailList()) {
            OrderDetail orderDetail = new OrderDetail(cartDetail);
            order.addOrderDetail(orderDetail);
        }

        order.setTotalPrice(currentUserCart.getTotalPrice());
        currentUser.addOrder(order);

        System.out.println(order);
    }
}
