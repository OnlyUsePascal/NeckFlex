package com.groupproject.toolkit;

import com.groupproject.entity.runtime.EntityHandler;

public class PathHandler {
    final static String pageRoot = "/com/groupproject/fxml/page/";
    final static String componentRoot = "/com/groupproject/fxml/component/";
    final static String imgRoot = "/com/groupproject/media/image/";
    final static String videoRoot = "/com/groupproject/media/video/";
    final static String textRoot = "src/main/resources/com/groupproject/media/text/";


    static public String getPageLoginMain() {
        return pageRoot + "LoginMain.fxml";
    }

    static public String getPageRegister() {
        return pageRoot + "LoginRegister.fxml";
    }

    static public String getPageHome() {
        return pageRoot + "Home.fxml";
    }

    static public String getPageItemTrending() {
        return pageRoot + "ItemTrending.fxml";
    }

    static public String getPageItemAll() {
        return pageRoot + "ItemAll.fxml";
    }

    static public String getPageUserRecord() {
        return pageRoot + "UserRecord.fxml";
    }

    static public String getPageUserProfile() {
        return pageRoot + "UserProfile.fxml";
    }

    static public String getPageAdminAccount(){
        return pageRoot + "AdminAccountList.fxml";
    }

    static public String getPageAdminItem(){
        return pageRoot + "AdminItemList.fxml";
    }

    static public String getPageUserDeposit() {
        return pageRoot + "UserDeposit.fxml";
    }

    static public String getPageCart(){
        return pageRoot + "Cart.fxml";
    }

    static public String getPageItemInfo(){
        return pageRoot + "ItemInfo.fxml";
    }

    static public String getPageItemInfoUpdate(){
        return pageRoot + "ItemInfoUpdate.fxml";
    }

    static public String getComponentSidebar() {
        if (EntityHandler.getCurrentUser().isAdmin()){
            return componentRoot + "SidebarAdmin.fxml";
        }
        return componentRoot + "SidebarCustomer.fxml";
    }

    static public String getComponentNavBar() {
        if (EntityHandler.getCurrentUser().isAdmin()) {
            return componentRoot + "NavBarAdmin.fxml";
        }
        return componentRoot + "NavBarCustomer.fxml";
    }

    static public String getComponentItemBox() {
        return componentRoot + "ItemBox.fxml";
    }

    static public String getComponentCartDetail(){
        return componentRoot + "CartDetail.fxml";
    }

    static public String getComponentOrder(){
        return componentRoot + "Order.fxml";
    }

    static public String getComponentOrderDetail(){
        return componentRoot + "OrderDetail.fxml";
    }

    static public String getComponentItemTrendingTile(){
        return componentRoot + "ItemTrendingTile.fxml";
    }


    static public String getMediaImage(String imgName) {
        return imgRoot + imgName;
    }

    static public String getMediaImageMagnifyGlass(){
        return "magnifying-glass-solid (1).png";
    }

    static public String getMediaTextItem(String textName) {
        return textRoot + textName;
    }


    static public String getMediaTextAccount() {
        return textRoot + "account.txt";
    }

    static public String getMediaTextItem() {
        return textRoot + "item2.txt";
    }

    static public String getMediaTextCart(){
        return textRoot + "cart.txt";
    }

    static public String getStyleSheet() {
        return "/com/groupproject/style.css";
    }
}
