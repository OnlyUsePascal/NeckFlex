package com.groupproject.toolkit;

public class GetterPath {
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

    static public String getSidebar(){
        // Account currentUser = CurrentUser.getCurrentUser();
        // if (currentUser.getAccountType() == ConstantUser.ADMIN){
        //     System.out.println("is admin");
        //     return componentRoot + "SidebarAdmin.fxml";
        // }
        //
        // System.out.println("is customer");
        return componentRoot + "SidebarCustomer.fxml";
    }


}
