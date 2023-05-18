package com.groupproject.controller.page;

import com.groupproject.controller.component.ItemBoxController;
import com.groupproject.entity.Constant.ConstantItem;
import com.groupproject.entity.generic.Item;
import com.groupproject.entity.runtime.EntityHandler;
import com.groupproject.toolkit.PathHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class ItemAllController implements Initializable {
    // @FXML
    // HBox itemPage;
    // @FXML
    // ScrollPane itemPageScrollPane;
    @FXML
    private VBox pageContainer;
    @FXML
    private ScrollPane pageContainerSrollPane;
    @FXML
    private Label pageIndex;
    @FXML
    private ComboBox<String> categoryList;
    @FXML
    private ComboBox<String> genreList;
    @FXML
    private ComboBox<String> sortByList;
    @FXML
    private ComboBox<String> orderList;
    @FXML
    private CheckBox availableBox;

    private final int rowSize = 5;
    private final int rowPerPage = 4;
    private final int pageSize = rowSize * rowPerPage;
    private int itemCnt;
    private int pageCnt;
    private int currentPage;
    private final String optionAny = "Any";
    private final String[] sortByOptions = {"Name", "Price"};
    private final String[] orderOptions = {"Ascending", "Descending"};
    private ArrayList<Item> itemsToShow;

    @Override
    public void initialize(java.net.URL url, java.util.ResourceBundle resourceBundle) {
        initFilter();
        refreshPage();
    }

    public void initFilter(){
        categoryList.getItems().addAll(Arrays.asList(ConstantItem.categoryList));
        categoryList.getItems().add(0, optionAny);
        categoryList.setValue(optionAny);

        genreList.getItems().addAll(Arrays.asList(ConstantItem.genreList));
        genreList.getItems().add(0, optionAny);
        genreList.setValue(optionAny);

        sortByList.getItems().addAll(Arrays.asList(sortByOptions));
        sortByList.setValue(sortByOptions[0]);

        orderList.getItems().addAll(Arrays.asList(orderOptions));
        orderList.setValue(orderOptions[0]);
    }

    public void refreshLayout(){
        itemCnt = itemsToShow.size();
        pageCnt = itemCnt / pageSize;
        currentPage = 0;
    }

    public void refreshData(){
        itemsToShow = new ArrayList<>();
        //filter
        for (Item item : EntityHandler.getItemList()) {
            if (!checkItem(item)) continue;
            itemsToShow.add(item);
        }
        //sort
        sortItemList(itemsToShow);

        //layout
        refreshLayout();
    }

    public void refreshPage(){
        pageContainer.getChildren().clear();

        refreshData();

        //show
        for (int i = 0; i < rowPerPage; i++) {
            HBox itemRow = new HBox();

            // item box
            int min = currentPage * pageSize + i * rowSize;
            int max = Math.min(currentPage * pageSize + (i + 1) * rowSize, itemCnt);
            for (int j = min; j < max; j++) {
                Item item = itemsToShow.get(j);

                Button itemBox = getItemBox(item);
                itemRow.getChildren().add(itemBox);
            }

            pageContainer.getChildren().add(itemRow);
        }
    }

    public boolean checkItem(Item item){
        return checkItemCategory(item) &&
                checkItemGenre(item) &&
                checkItemAvailable(item);
    }

    public boolean checkItemCategory(Item item){
        String category = categoryList.getValue();
        if (category.equals(optionAny)) return true;
        return item.getCategoryString().equals(category);
    }

    public boolean checkItemGenre(Item item){
        String genre = genreList.getValue();
        if (genre.equals(optionAny)) return true;
        return item.getGenreString().equals(genre);
    }

    public boolean checkItemAvailable(Item item){
        if (availableBox.isSelected()) return item.isAvailable();
        return !item.isAvailable();
    }

    public void sortItemList(ArrayList<Item> itemList){
        String sortBy = sortByList.getValue();
        String order = orderList.getValue();

        itemList.sort((item1, item2) -> {
            if (sortBy.equals(sortByOptions[0])) {
                if (order.equals(orderOptions[0])) {
                    return item1.getTitle().compareTo(item2.getTitle());
                } else {
                    return item2.getTitle().compareTo(item1.getTitle());
                }
            } else {
                if (order.equals(orderOptions[0])) {
                    return (int) (item1.getPrice() - item2.getPrice());
                } else {
                    return (int) (item2.getPrice() - item1.getPrice());
                }
            }
        });
    }

    public Button getItemBox(Item item) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(PathHandler.getComponentItemBox()));
            Button itemBox = loader.load();
            ItemBoxController itemBoxController = loader.getController();

            itemBoxController.setData(item);

            return itemBox;
        } catch (IOException err) {
            err.printStackTrace();
            return null;
        }
    }

    public void clearFilter(){
        initFilter();
        refreshPage();
    }

    public void changePage(ActionEvent event){
        Button button = (Button) event.getSource();
        String id = button.getId();

        if (id.equals("pagePrev")) {
            if (currentPage <= 0) return;
            currentPage--;
        } else {
            if (currentPage >= pageCnt) return;
            currentPage++;
        }

        refreshPage();
        pageIndex.setText((currentPage + 1) + "");
    }
}
