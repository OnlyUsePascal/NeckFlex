package com.groupproject.toolkit;

public class PathGetter {
    final static String pageRoot = "/com/groupproject/fxml/page/";
    final static String componentRoot = "/com/groupproject/fxml/component/";
    final static String imgRoot = "/com/groupproject/media/image/";
    final static String videoRoot = "/com/groupproject/media/video/";


    static public String getLoginMain(){
        return pageRoot + "LoginMain.fxml";
    }

    static public String getHome(){
        return pageRoot + "Home.fxml";
    }

    static public String getItemTrending(){
        return pageRoot + "ItemTrending.fxml";
    }
}
