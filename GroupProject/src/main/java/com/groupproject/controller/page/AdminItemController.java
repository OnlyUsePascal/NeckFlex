package com.groupproject.controller.page;

import com.groupproject.controller.popup.ItemInfoUpdateController;
import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
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

public class AdminItemController implements Initializable {
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
    @FXML
    private ComboBox<String> categoryList;

    private String optionAny;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        optionAny = "Any";
        initFilter();

        ViewHandler.lockHorizonScroll(tableViewItem);
        initColumnProperty();
        refreshTable();

    }

    public void initFilter() {
        // category
        categoryList.getItems().addAll(Arrays.asList(ConstantItem.categoryList));
        categoryList.getItems().add(optionAny);
        categoryList.setValue(optionAny);
        categoryList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                refreshTable();
            }
        });

        // search
        searchField.setOnKeyPressed(keyEvent -> {
            if (keyEvent.getCode().toString().equals("ENTER")) {
                refreshTable();
            }
        });
    }

    public void initColumnProperty() {
        itemIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        itemTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        itemStockColumn.setCellValueFactory(new PropertyValueFactory<>("stock"));

        // category
        itemCategoryColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> itemStringCellDataFeatures) {
                Item item = itemStringCellDataFeatures.getValue();
                return new SimpleObjectProperty<>(ConstantItem.categoryList[item.getCategory()]);
            }
        });

        // genre
        itemGenreColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TableColumn.CellDataFeatures<Item, String> itemStringCellDataFeatures) {
                Item item = itemStringCellDataFeatures.getValue();
                return new SimpleObjectProperty<>(item.getGenreString());
            }
        });

        // image
        itemImageColumn.setStyle("-fx-alignment: center;");
        itemImageColumn.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<Item, ImageView>, ObservableValue<ImageView>>() {
            @Override
            public ObservableValue<ImageView> call(TableColumn.CellDataFeatures<Item, ImageView> itemImageViewCellDataFeatures) {
                Image img = ViewHandler.getImage(PathHandler.getMediaImageMagnifyGlass());
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
                        getPopupUpdate(item);
                    });
                }
            };
        });
    }

    public void getPopupUpdate(Item item) {
        FXMLLoader itemLoader = new FXMLLoader(getClass().getResource(PathHandler.getPopupItemInfoUpdate()));
        try {
            AnchorPane itemPane = itemLoader.load();
            ItemInfoUpdateController controller = itemLoader.getController();
            controller.setData(item);

            EventHandler<WindowEvent> popupOnClose = event -> {
                refreshTable();
            };

            ViewHandler.getPopup(itemPane, popupOnClose);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    public void getPopupCreate() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getPopupItemInfoAdd()));
            AnchorPane pane = loader.load();

            EventHandler<WindowEvent> popupOnClose = event2 -> {
                refreshTable();
            };

            ViewHandler.getPopup(pane, popupOnClose);
        } catch (IOException err) {
            err.printStackTrace();
        }
    }

    boolean scrollEvent = false;

    public void refreshTable() {
        tableViewItem.setItems(getData());
        tableViewItem.refresh();
    }

    public ObservableList<Item> getData() {
        ObservableList<Item> items = FXCollections.observableArrayList();
        for (Item item : EntityHandler.getItemList()) {
            boolean legit = true;
            legit &= filterCategory(item);
            legit &= filterSearch(item);

            if (legit) {
                items.add(item);
            }
        }
        return items;
    }

    public boolean filterCategory(Item item) {
        String option = categoryList.getValue();
        if (option.equals(optionAny)) return true;
        return item.getCategoryString().equals(categoryList.getValue());
    }

    public boolean filterSearch(Item item) {
        String searchString = searchField.getText();
        if (searchString.isBlank()) return true;
        return ViewHandler.checkStringSimilar(item.getId(), searchString) ||
                ViewHandler.checkStringSimilar(item.getTitle(), searchString);
    }

    public void clearFilter(ActionEvent event) {
        searchField.clear();
        categoryList.setValue(optionAny);
        refreshTable();
    }


}
