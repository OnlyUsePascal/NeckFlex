package com.groupproject.toolkit;

public class GetterPath {
    final static String pageRoot = "/com/groupproject/fxml/page/";
    final static String componentRoot = "/com/groupproject/fxml/component/";
    final static String imgRoot = "/com/groupproject/media/image/";
    final static String videoRoot = "/com/groupproject/media/video/";


    static public String getLoginMain(){
        return pageRoot + "LoginMain.fxml";
    }

    static public String getPageHome(){
        return pageRoot + "Home.fxml";
    }

    static public String getPageItemTrending(){
        return pageRoot + "ItemTrending.fxml";
    }

    static public String getPageItemAll(){
        return pageRoot + "ItemAll.fxml";
    }

    static public String getPageBtn(String btnContent){
        return pageRoot + btnContent + ".fxml";
    }

    static public String getComponentSidebar(){
        // Account currentUser = CurrentUser.getCurrentUser();
        // if (currentUser.getAccountType() == ConstantUser.ADMIN){
        //     System.out.println("is admin");
        //     return componentRoot + "SidebarAdmin.fxml";
        // }
        //
        // System.out.println("is customer");
        return componentRoot + "SidebarCustomer.fxml";
    }

    static public String getComponentItemBox(){
        return componentRoot + "ItemBox.fxml";
    }

    static public String getMediaImage(String imgName){
        return imgRoot + imgName;
    }

    static public String getStyleSheet(){
        return "/com/groupproject/style.css";
    }
}
