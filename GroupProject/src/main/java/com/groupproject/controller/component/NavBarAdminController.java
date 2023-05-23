package com.groupproject.controller.component;

import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;

public class NavBarAdminController extends NavBarController{
    public void toPageAdminItem(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageAdminItem());

    }
}
