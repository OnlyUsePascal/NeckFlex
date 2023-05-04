package com.groupproject.entity.generic;

import java.util.ArrayList;

public class ItemGame extends Item{
    public ItemGame(String id, String title, int category, int genre, int stock, double fee) {
        super(id, title, category, genre, stock, fee);
    }

    public ItemGame(ArrayList<String> infoList){
        super(infoList);
    }
}
