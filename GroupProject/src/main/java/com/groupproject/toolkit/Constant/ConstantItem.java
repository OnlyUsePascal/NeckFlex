package com.groupproject.toolkit.Constant;

import javafx.scene.control.ChoiceBox;

public class ConstantItem {
    static public String[] categoryList = {"Dvd", " Movie Record", "Video Game"};
    static public enum ItemCategory{
        DVD,
        RECORD,
        GAME;
    }


    static public String[] genreList = {"Action", "Thrilling", "Comedy", "Sci - fi", "Romance"};
    static public int genreToIndex(String genre){
        for (int i = 0 ; i < genreList.length; i++){
            if (genreList[i].equals(genre)){
                return i;
            }
        }
        return -1;
    }

    static public enum ItemInfo {
        ID,
        TITLE,
        CATEGORY,
        GENRE,
        STOCK,
        FEE
    }
}
