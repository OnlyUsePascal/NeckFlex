package com.groupproject.entity.generic;

import com.groupproject.entity.Constant.ConstantItem;

import java.util.ArrayList;

public class ItemGame extends Item{
    public ItemGame(int year, String title, int category, int genre, int stock, double fee) {
        super(year, title, category, genre, stock, fee);
    }

    public ItemGame(ArrayList<String> infoList){
        super(infoList);
    }

    public static ItemGame getNewItemGame(String title, String genre, double fee, int year, int stock){
        ItemGame newItemGame = new ItemGame(
                year,
                title,
                ConstantItem.ItemCategory.GAME.ordinal(),
                ConstantItem.genreToIndex(genre),
                stock,
                fee);

        return newItemGame;
    }
}
