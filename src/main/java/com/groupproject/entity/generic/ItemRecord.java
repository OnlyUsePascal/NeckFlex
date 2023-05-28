package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;

import java.util.ArrayList;

public class ItemRecord extends Item{
    public ItemRecord(ArrayList<String> infoList){
        super(infoList);
    }

    public ItemRecord(String id, String title, int category, int genre, int stock, double fee, String desc) {
        super(id, title, category, genre, stock, fee, desc);
    }

    public ItemRecord(String title, int category, int genre, int stock, String year, double fee, String desc) {
        super(title, category, genre, stock, year, fee, desc);
    }

}
