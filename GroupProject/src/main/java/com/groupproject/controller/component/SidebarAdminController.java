package com.groupproject.controller.component;

import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;

public class SidebarAdminController extends SidebarController{
    public void toAdminItem(ActionEvent event){
        ShopSystem.setPageContent(PathHandler.getPageAdminItem());
    }

    public void toAdminAccount(ActionEvent event){
        ShopSystem.setPageContent(PathHandler.getPageAdminAccount());
    }

}
