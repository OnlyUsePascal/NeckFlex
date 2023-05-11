package com.groupproject.controller.page;

import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.ShopSystem;
import com.groupproject.toolkit.ObjectHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Popup;
import javafx.stage.WindowEvent;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AdminItemListController implements Initializable {
    @FXML
    private TableView<Item> tableViewItem;
    @FXML
    private TableColumn<Item, ImageView> itemImageColumn;
    @FXML
    private TableColumn<Item, String> itemIdColumn;
    @FXML
    private TableColumn<Item, String> itemTitleColumn;
    @FXML
    private TableColumn<Item, String> itemCategoryColumn;
    @FXML
    private TableColumn<Item, String> itemGenreColumn;
    @FXML
    private TableColumn<Item, Integer> itemStockColumn;
    @FXML
    private TextField searchField;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initColumnProperty();
        refreshTable();
    }

    public void initColumnProperty() {
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        itemStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        //category
        itemCategoryColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> itemStringCellDataFeatures) {
                Item item = itemStringCellDataFeatures.getValue();
                return new SimpleObjectProperty<>(ConstantItem.categoryList[item.getCategory()]);
            }
        });

        //genre
        itemGenreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> itemStringCellDataFeatures) {
                Item item = itemStringCellDataFeatures.getValue();
                return new SimpleObjectProperty<>(item.getGenre());
            }
        });

        // image
        itemImageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Item, ImageView> itemImageViewCellDataFeatures) {
                Image img = ObjectHandler.getImage(PathHandler.getMediaImageMagnifyGlass());
                ImageView imageView = new ImageView(img);
                imageView.setFitWidth(25);
                imageView.setFitHeight(25);
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
                protected void updateItem(ImageView imageView, boolean empty) {
                    super.updateItem(imageView, empty);
                    this.imageView.setImage((empty || imageView == null) ? null : imageView.getImage());
                    setOnMouseClicked(event -> {
                        System.out.println("testing");
                        Item item = getTableView().getItems().get(getIndex());
                        getPopup(item);
                    });
                }
            };
        });
    }

    public void getPopup(Item item){
        FXMLLoader itemLoader = new FXMLLoader(getClass().getResource(PathHandler.getPageItemInfoUpdate()));
        try {
            AnchorPane itemPane = itemLoader.load();
            ItemInfoUpdateController controller = itemLoader.getController();
            controller.setData(item);

            EventHandler<WindowEvent> popupOnClose = event -> {
                refreshTable();
            };

            ObjectHandler.getPopup(itemPane, popupOnClose);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    boolean what = false;

    public void refreshTable(){
        // tableViewItem.getItems().clear();
        tableViewItem.setItems(getData());
        tableViewItem.refresh();
    }

    public ObservableList<Item> getData() {
        // ObservableList<Item> itemList = ArrayList
        // if (what){
        //     return null;
        // }

        // what = true;
        ObservableList<Item> items = FXCollections.observableArrayList();
        for (Item item : ShopSystem.getItemList()) {
            items.add(item);
        }
        return items;
    }
}
