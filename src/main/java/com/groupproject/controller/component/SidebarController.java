package com.groupproject.controller.component;

import com.groupproject.controller.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.AnchorPane;

import java.net.URL;
import java.util.ResourceBundle;
import java.util.concurrent.atomic.AtomicBoolean;

public class SidebarController implements Initializable {

    @FXML
    private AnchorPane menuPane;

    private AtomicBoolean menuIsOpen = new AtomicBoolean(true);


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        ViewHandler.setSidebarController(this);
    }

    public boolean isOpen(){
        return menuIsOpen.get();
    }

    public void menuActive(){
        ViewHandler.setMenuActive();
        menuIsOpen.set(!menuIsOpen.get());
    }

    public void toPageItemAll(ActionEvent event){
        ViewHandler.setPageContent(PathHandler.getPageItemAll());
        menuActive();
    }


}
