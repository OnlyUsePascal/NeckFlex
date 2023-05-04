package com.groupproject.entity.generic;

import java.util.ArrayList;

public class ItemRecord extends Item{
    public ItemRecord(String id, String title, int category, int genre, int stock, double fee) {
        super(id, title, category, genre, stock, fee);
    }

    public ItemRecord(ArrayList<String> infoList){
        super(infoList);
    }
}
