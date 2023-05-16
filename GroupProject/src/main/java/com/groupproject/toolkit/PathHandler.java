package com.groupproject.toolkit;

import com.groupproject.entity.runtime.EntityHandler;

public class PathHandler {
    final static String pageRoot = "/com/groupproject/fxml/page/";
    final static String componentRoot = "/com/groupproject/fxml/component/";
    final static String popupRoot = "/com/groupproject/fxml/popup/";
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

    static public String getPageUserDeposit() {
        return pageRoot + "UserDeposit.fxml";
    }

    static public String getPageAdminAccount(){
        return pageRoot + "AdminAccount.fxml";
    }

    static public String getPageAdminItem(){
        return pageRoot + "AdminItem.fxml";
    }


    static public String getPageCart(){
        return pageRoot + "Cart.fxml";
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


    static public String getPopupItemInfoCart(){
        return popupRoot + "ItemInfoCart.fxml";
    }

    static public String getPopupItemInfoUpdate(){
        return popupRoot + "ItemInfoUpdate.fxml";
    }

    static public String getPopupItemInfoAdd(){
        return popupRoot + "ItemInfoAdd.fxml";
    }

    static public String getPopupAccountInfoUpdate(){
        return popupRoot + "AccountInfoUpdate.fxml";
    }

    static public String getPopupAccountInfoAdd(){
        return popupRoot + "AccountInfoAdd.fxml";
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
