package com.groupproject.controller.component;

import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;

public class  SidebarCustomerController extends SidebarController{
    public void toRecord(ActionEvent event){
        ShopSystem.setPageContent(PathHandler.getPageUserRecord());
    }

}
