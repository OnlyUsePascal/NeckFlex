package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;

import java.util.ArrayList;

public class ItemGame extends Item{
    //restore
    public ItemGame(ArrayList<String> infoList){
        super(infoList);
    }

    //copy
    public ItemGame(String id, String title, int category, int genre, int stock, double price, String desc) {
        super(id, title, category, genre, stock, price, desc);
    }

    //get new
    public ItemGame(String title, int category, int genre, int stock, String year, double price, String desc) {
        super(title, category, genre, stock, year, price, desc);
    }
}
