package com.groupproject.controllers;

import com.groupproject.ItemInfo;
import com.groupproject.ItemView;
import com.groupproject.RegistrationPage;
import com.groupproject.types.Item;
import com.groupproject.types.SystemShop;
import javafx.beans.binding.Bindings;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.ResourceBundle;

public class ItemViewController implements Initializable {

    @FXML
    private TableView<Item> TableViewItem ;
    @FXML
    private TableColumn<Item, ImageView> itemImageColumn;
    @FXML
    private TableColumn<Item, String> itemIdColumn;
    @FXML
    private TableColumn<Item, String> itemTitleColumn;
    @FXML
    private TableColumn<Item, String> itemCategoryColumn;
    @FXML
    private TableColumn<Item, Integer> itemStockColumn;
    @FXML
    private TextField searchField;

    private Map<Item, ImageView> imageViewCache = new HashMap<>();


    public void searchItemAction(){
        System.out.println(123456);
        TableViewItem.setItems(getItems(searchField.getText()));
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        searchField.setText("");
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            TableViewItem.setItems(getItems(newValue));
        });

        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        itemCategoryColumn.setCellValueFactory(new PropertyValueFactory<>("genre"));
        itemStockColumn.setCellValueFactory(new PropertyValueFactory<>("numberOfCopies"));
        TableViewItem.setItems(getItems(searchField.getText()));

//        TableViewItem.addEventFilter(MouseEvent.MOUSE_CLICKED, new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                // Do nothing
//                // This prevents the event from being consumed by the TableView
//            }
//        });


        itemImageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Item, ImageView> cellDataFeatures) {
                Item item = cellDataFeatures.getValue();

                // Check if the ImageView has already been loaded for this Item
                if (imageViewCache.containsKey(item)) {
                    return new SimpleObjectProperty<ImageView>(imageViewCache.get(item));
                }

                System.out.println(item);
                System.out.println("Loading imageVIew!");

                File file = new File("src/main/resources/images/image1.png").getAbsoluteFile();
                Image image = new Image(file.toURI().toString());
                ImageView imageView = new ImageView(image);
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);

                imageViewCache.put(item, imageView);

                return new SimpleObjectProperty<ImageView>(imageView);
            }
        });


        itemImageColumn.setCellFactory(column -> {
            return new TableCell<Item, ImageView>() {
                private final ImageView imageView = new ImageView();

                {
                    imageView.setFitWidth(25);
                    imageView.setFitHeight(25);
                    imageView.setMouseTransparent(true);
                    setGraphic(imageView);
                }

                @Override
                protected void updateItem(ImageView image, boolean empty) {
                    super.updateItem(image, empty);
                    if (empty || image == null) {
                        imageView.setImage(null);
                    } else {
                        imageView.setImage(image.getImage());
                        setOnMouseClicked(event -> {
                            System.out.println("testing");
                            Item item = getTableView().getItems().get(getIndex());
                            SystemShop.itemInfoSelected = item;
//                            System.out.println(item);
                            ItemInfo itemInfo = new ItemInfo();
                            try {
                                itemInfo.start(new Stage());
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }
                }
            };
        });



    }

    public ObservableList<Item> getItems(String searchKeyword){
        ObservableList<Item> items = FXCollections.observableArrayList();
        for (Item item : SystemShop.getItemLists()) {
//            System.out.println(item);
            if(searchKeyword.isBlank()){
                items.add(item);
            }else {
                if (item.getId().contains(searchKeyword) || item.getTitle().contains(searchKeyword)) {
                    items.add(item);
                }
            }
        }
        System.out.println("Size of the items: " + items.size());
        return items;
    }

//    public static void closeWindow() {
//        ItemView.window.close();
//    }

}
