package com.groupproject.controller.component;

import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;

public class  SidebarCustomerController extends SidebarController{
    public void toRecord(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageUserRecord());
        menuActive(null);
    }

}
