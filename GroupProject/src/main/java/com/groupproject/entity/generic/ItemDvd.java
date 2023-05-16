package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;

import java.util.ArrayList;

public class ItemDvd extends Item{
    public ItemDvd(int year, String title, int category, int genre, int stock, double fee) {
        super(year, title, category, genre, stock, fee);
    }

    public ItemDvd(ArrayList<String> infoList){
        super(infoList);
    }

    public static ItemDvd getNewItemDvd(String title, String genre, double fee, int year, int stock){
        ItemDvd newItemDvd = new ItemDvd(
                year,
                title,
                ConstantItem.ItemCategory.DVD.ordinal(),
                ConstantItem.genreToIndex(genre),
                stock,
                fee);

        return newItemDvd;
    }

}
