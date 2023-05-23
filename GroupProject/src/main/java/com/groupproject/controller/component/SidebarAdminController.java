package com.groupproject.controller.component;

import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;

public class SidebarAdminController extends SidebarController{
    public void toAdminItem(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageAdminItem());
        menuActive(null);
    }

    public void toAdminAccount(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageAdminAccount());
        menuActive(null);
    }

}
