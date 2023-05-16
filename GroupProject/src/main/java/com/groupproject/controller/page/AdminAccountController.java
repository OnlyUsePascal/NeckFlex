package com.groupproject.controller.page;

import com.groupproject.controller.popup.AccountInfoUpdateController;
import com.groupproject.entity.Constant.ConstantAccount;
import com.groupproject.entity.generic.Account;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.entity.runtime.ViewHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class AdminAccountController implements Initializable {
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
    private TextField searchField;
    @FXML
    private ComboBox<String> choiceList;

    String optionAny;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        optionAny = "Any";
        initFilter();

        initColumnProperty();
        refreshTable();
    }

    public void initFilter(){
        choiceList.getItems().addAll(Arrays.asList(ConstantAccount.statusList));
        choiceList.getItems().add(optionAny);
        choiceList.setValue(optionAny);

        choiceList.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
            refreshTable();
        });


    }

    public void initColumnProperty(){
        idColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("id"));
        usernameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("username"));
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<Account, String>("firstName"));
        rewardPointsColumn.setCellValueFactory(new PropertyValueFactory<Account, Double>("lastName"));

        //account type
        accountTypeColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Account, String> accountStringCellDataFeatures) {
                Account account = accountStringCellDataFeatures.getValue();
                return new SimpleObjectProperty<>(account.getStatusString());
            }
        });

        //image
        imageColumn.setStyle("-fx-alignment: center;");
        imageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Account, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Account, ImageView> itemImageViewCellDataFeatures) {
                Image img = ViewHandler.getImage(PathHandler.getMediaImageMagnifyGlass());
                ImageView imageView = new ImageView(img);
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);
                return new SimpleObjectProperty<ImageView>(imageView);
            }
        });

        imageColumn.setCellFactory(column -> {
            return new TableCell<Account, ImageView>() {
                private final ImageView imageView = new ImageView();

                {
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    imageView.setMouseTransparent(true);
                    setGraphic(imageView);
                }

                @Override
                protected void updateItem(ImageView imageView, boolean empty) {
                    super.updateItem(imageView, empty);
                    this.imageView.setImage((empty || imageView == null) ? null : imageView.getImage());
                    setOnMouseClicked(event -> {
                        Account account = getTableView().getItems().get(getIndex());
                        getPopupUpdate(account);
                    });
                }
            };
        });
    }

    public void refreshTable(){
        tableView.setItems(getData());
        tableView.refresh();
    }

    public ObservableList<Account> getData() {
        ObservableList<Account> accounts = FXCollections.observableArrayList();
        for (Account account : EntityHandler.getAccountList()){
            boolean legit = true;
            legit &= filterType(account);
            legit &= filterSearch(account);

            if (legit){
                accounts.add(account);
            }
        }
        return accounts;
    }

    public void getPopupUpdate(Account account){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getPopupAccountInfoUpdate()));
            AnchorPane pane = loader.load();
            AccountInfoUpdateController controller = loader.getController();
            controller.setData(account);

            EventHandler<WindowEvent> popupOnClose = event -> {
                refreshTable();
            };

            ViewHandler.getPopup(pane, popupOnClose);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void getPopupCreate(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getPopupAccountInfoAdd()));
            AnchorPane pane = loader.load();

            EventHandler<WindowEvent> popupOnClose = event2 -> {
                refreshTable();
            };

            ViewHandler.getPopup(pane, popupOnClose);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public boolean filterType(Account account){
        String option = choiceList.getValue();
        if (option.equals(optionAny)) return true;
        return account.getStatusString().equals(option);
    }

    public boolean filterSearch(Account account){
        String searchKeyword = searchField.getText();
        if(searchKeyword.isBlank()) return true;
        return ViewHandler.checkStringSimilar(account.getId(), searchKeyword) ||
                ViewHandler.checkStringSimilar(account.getUsername(), searchKeyword) ||
                ViewHandler.checkStringSimilar(account.getFirstName(), searchKeyword) ||
                ViewHandler.checkStringSimilar(account.getLastName(), searchKeyword);
    }

    public void clearFilter(ActionEvent event){
        searchField.clear();
        choiceList.setValue(optionAny);
        refreshTable();
    }

}
