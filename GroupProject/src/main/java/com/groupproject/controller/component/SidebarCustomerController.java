package com.groupproject.controller.component;

import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;

public class  SidebarCustomerController extends SidebarController{
    public void toPageRecord(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageUserRecord());
        menuActive(null);
    }

    public void toPageAboutUs(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageAboutUs());
        menuActive(null);
    }

}
