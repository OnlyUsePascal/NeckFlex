package com.example.admininfo.Controller;

import com.example.admininfo.Account;
import com.example.admininfo.AccountInfo;
import com.example.admininfo.AdminAccountListPage;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
    private Button submitButton;
    @FXML
    private TextField keywordBox;
    private Stage stage;
    private Scene scene;
    private Parent root;
    @FXML
    private Button nextScene;
    @FXML
    private AnchorPane filterPane;
    ObservableList<Account> accountList = FXCollections.observableArrayList();


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
        FilteredList<Account> filteredData = new FilteredList<>(accountList, b -> true);
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
    public void test(){
        System.out.println("TESTING!!");
    }
    public void displaySortedTable(ArrayList<Account> tempList){
        ObservableList<Account> tempObservableList = FXCollections.observableArrayList();
        for(Account acc : tempList){
            tempObservableList.add(acc);
        }
        tableView.refresh();
        tableView.setItems(tempObservableList);
    }
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        idColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("firstName"));
        rewardPointsColumn.setCellValueFactory(new PropertyValueFactory<Account, Double>("rewardPoint"));
        accountTypeColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("accountType"));
        setupTable();
        FXMLLoader loader = new FXMLLoader(AdminAccountListPage.class.getResource("filter.fxml"));
        try{
                AnchorPane pane = loader.load();
                filterPane.getChildren().add(pane);
                FilterController filterController = loader.getController();
                filterController.setControllerForMain(this);
            }
        catch(IOException e){
            e.printStackTrace();
        }



    }

}