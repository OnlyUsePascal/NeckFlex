package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;

import java.util.ArrayList;

public class ItemDvd extends Item{
    public ItemDvd(String title, double fee) {
        super(title, fee);
    }

    public ItemDvd(String id, String title, int category, int genre, int stock, double fee) {
        super(id, title, category, genre, stock, fee);
    }

    public ItemDvd(ArrayList<String> infoList){
        super(infoList);
    }

    public static ItemDvd getNewItemDvd(String title, String genre, double fee, int year, int stock){
        ItemDvd newItemDvd = new ItemDvd(getItemId(year),
                title,
                ConstantItem.ItemCategory.DVD.ordinal(),
                ConstantItem.genreToIndex(genre),
                stock,
                fee);

        return newItemDvd;
    }

}
