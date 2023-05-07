package com.example.admininfo.ControllerAdminInfo;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;

import java.net.URL;
import java.util.ResourceBundle;

public class FilterController implements Initializable{
    @FXML
    private ChoiceBox choiceList;
    private String[] choice = {"Guest", "Regular", "VIP", "All"};
    private String selectChoice;
    ControllerForMain controllerForMain;
    public void setControllerForMain(ControllerForMain controller){
        this.controllerForMain = controller;
    }
    public void sortByAccountType(){
        if(selectChoice.equals("All")){
            controllerForMain.displayTable();
        }
        else{
            controllerForMain.displaySortedTable(controllerForMain.getSortedAccountList(selectChoice));
        }

    }
    public void getChoice(Event event) {
        String choice = choiceList.getValue().toString();
        selectChoice = choice;
    }
    public void initialize(URL url, ResourceBundle rb){
        choiceList.getItems().addAll(choice);
        choiceList.setOnAction(this::getChoice);
    }

}

