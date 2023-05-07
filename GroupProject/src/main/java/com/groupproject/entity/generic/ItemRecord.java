package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;

import java.util.ArrayList;

public class ItemRecord extends Item{
    public ItemRecord(int year, String title, int category, int genre, int stock, double fee) {
        super(year, title, category, genre, stock, fee);
    }

    public ItemRecord(ArrayList<String> infoList){
        super(infoList);
    }

    public static ItemRecord getNewItemRecord(String title, String genre, double fee, int year, int stock){
        ItemRecord newItemRecord = new ItemRecord(
                year,
                title,
                ConstantItem.ItemCategory.RECORD.ordinal(),
                ConstantItem.genreToIndex(genre),
                stock,
                fee);

        return newItemRecord;
    }
}
