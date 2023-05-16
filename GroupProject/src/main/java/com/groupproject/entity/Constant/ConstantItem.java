package com.groupproject.entity.Constant;

public class ConstantItem {
    static public String[] categoryList = {"Dvd", "Movie Record", "Video Game"};
    static public enum ItemCategory {
        DVD,
        RECORD,
        GAME
    }

    static public int categoryToIndex(String category){
        for (int i = 0 ; i < categoryList.length; i++){
            if (categoryList[i].equals(category)){
                return i;
            }
        }
        return -1;
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
