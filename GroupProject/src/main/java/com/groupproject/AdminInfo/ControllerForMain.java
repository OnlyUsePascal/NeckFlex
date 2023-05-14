package com.groupproject.AdminInfo;


import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class ControllerForMain implements Initializable {
    @FXML
    private TableView<Account> tableView;
    @FXML
    private TableColumn<Account, ImageView> imageColumn;
    @FXML
    private TableColumn<Account, String> idColumn ;
    @FXML
    private TableColumn<Account, String> usernameColumn;
    @FXML
    private TableColumn<Account, String> firstNameColumn;
    @FXML
    private TableColumn<Account, Double> rewardPointsColumn;
    @FXML
    private TableColumn<Account, String> accountTypeColumn ;
    @FXML
    private TextField idTextField;
    @FXML
    private TextField usernameTextField;
    @FXML
    private TextField keywordBox;
    @FXML
    private ChoiceBox choiceList;
    private String[] choice = {"Guest", "Regular", "VIP", "All"};
    private String selectChoice;
    ObservableList<Account> accountList = FXCollections.observableArrayList();
    ObservableList<Account> accountListAfterGroup = FXCollections.observableArrayList();


    public void changingWindow(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Scene scene = btn.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(AccountInfo.class.getResource("accountView.fxml"));
        scene.setRoot(fxmlLoader.load());

        AccountInfoController accountInfoController = fxmlLoader.getController();
        Account clickedAccount = tableView.getSelectionModel().getSelectedItem();
        accountInfoController.setAccount(clickedAccount);
    }
    public void submit(ActionEvent event){
        ObservableList<Account> currentTableData = tableView.getItems();
        String currentAccountId = idTextField.getText();
        for(Account acc : currentTableData){
            if(acc.getId().equals(currentAccountId)){
                acc.setUsername(usernameTextField.getText());
                tableView.refresh();
                break;
            }
        }
        System.out.println(currentTableData.get(1).getUsername());
    }
    @FXML
    public void rowClicked(MouseEvent event){
        Account clickedAccount = tableView.getSelectionModel().getSelectedItem();
        idTextField.setText(clickedAccount.getId());
        usernameTextField.setText(clickedAccount.getUsername());
    }
    public void searching(MouseEvent event){

        FilteredList<Account> filteredData = new FilteredList<>(accountListAfterGroup, b -> true);
        String keyword = keywordBox.getText().toLowerCase();
        filteredData.setPredicate(account -> {
                //If no search value then displaay all accounts
                if (keyword == null || keyword.isEmpty()) {
                    return true;
                }
                if (account.getId().toLowerCase().indexOf(keyword) != -1 ) {
                    return true;
                } else if (account.getFirstName().toLowerCase().indexOf(keyword) != -1) {
                    return true;
                } else
                    return false;
            });

        SortedList<Account> sortedData = new SortedList<>(filteredData);
        //Bind the SortedList comparator to the TableView comparator
        sortedData.comparatorProperty().bind(tableView.comparatorProperty());
        //Add sorted (and filtered) data to the table
        tableView.setItems(sortedData);
    }



    public void setupTable(){

        accountList = FXCollections.observableArrayList(
                new  Account("1", "username1", 1,"nhan","truong",3,"391510951","HCM", "Guest"),
                new Account("2", "username2", 10,"dat","pham",2,"3915102351","Hanoi", "VIP"),
                new Account("3", "huanchodien", 10,"huan","pham",2,"3915102351","Hanoi", "VIP")

        );
        tableView.setItems(accountList);


    }
    public void setAccount(Account acc1){
        for(Account acc : accountList){
            if(acc.getId().equals(acc1.getId())){
                accountList.remove(acc);
                accountList.add(acc1);
                break;
            }
        }
    }
    public void displayTable(){
        tableView.refresh();
        tableView.setItems(accountList);
    }
    public ArrayList<Account> getSortedAccountList(String Type){
        ArrayList<Account> tempList = new ArrayList<Account>();
        for(Account acc : accountList){
                if(acc.getAccountType().equals(Type)){
                    tempList.add(acc);
                }
            }
        return tempList;
    }
    public void displaySortedTable(ArrayList<Account> tempList){
        ObservableList<Account> tempObservableList = FXCollections.observableArrayList();
        for(Account acc : tempList){
            tempObservableList.add(acc);
        }
        tableView.refresh();
        tableView.setItems(tempObservableList);
    }
    public void sortByAccountType(){
        choiceList.getSelectionModel().selectedItemProperty().addListener((v, oldValue, newValue) -> {
            selectChoice = newValue.toString();
            if(selectChoice.equals("All")){
                accountListAfterGroup = accountList;
                displayTable();
            }
            else{
                accountListAfterGroup = FXCollections.observableArrayList(getSortedAccountList(selectChoice));
                displaySortedTable(getSortedAccountList(selectChoice));
            }
        });
    }
    public void addAccountFunction(ActionEvent event) throws IOException {
        Button btn = (Button) event.getSource();
        Scene scene = btn.getScene();
        FXMLLoader fxmlLoader = new FXMLLoader(RegistrationPageForAdmin.class.getResource("/com/groupproject/RegistrationInAdminPage.fxml"));
        scene.setRoot(fxmlLoader.load());
    }
    public void updateNewAccount(Account acc){
        accountList.add(acc);
        displayTable();
    }
    public void clearFilter(){
        keywordBox.clear();
        choiceList.setValue("All");
        accountListAfterGroup = accountList;
        displayTable();
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("firstName"));
        rewardPointsColumn.setCellValueFactory(new PropertyValueFactory<Account, Double>("rewardPoint"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountType"));
        setupTable();
        accountListAfterGroup = accountList;
        // Set list and default value for ChoiceBox
        choiceList.getItems().addAll("Guest", "Regular", "VIP","Admin", "All");
        choiceList.setValue("All");
        sortByAccountType();
    }

}