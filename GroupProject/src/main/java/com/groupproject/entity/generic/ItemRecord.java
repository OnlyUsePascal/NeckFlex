package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;

import java.util.ArrayList;

public class ItemRecord extends Item{
    public ItemRecord(ArrayList<String> infoList){
        super(infoList);
    }

    public ItemRecord(String id, String title, int category, int genre, int stock, double fee) {
        super(id, title, category, genre, stock, fee);
    }

    public ItemRecord(String title, int category, int genre, int stock, int year, double fee) {
        super(title, category, genre, stock, year, fee);
    }

}
