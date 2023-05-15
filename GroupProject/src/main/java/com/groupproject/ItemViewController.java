package com.groupproject;

import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

import java.net.URL;
import java.util.*;
import java.util.stream.Collectors;

public class ItemViewController implements Initializable {
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> itemsToShow = new ArrayList<>();
    private ArrayList<CartItem> cart = new ArrayList<>();

    @FXML
    private VBox buttonContainer;

    @FXML
    private ComboBox categoryDropdown;

    @FXML
    private ComboBox sortByDropdown;

    @FXML
    private CheckBox availableCheckbox;

    @FXML
    private ComboBox genreDropdown;

    @FXML
    private ComboBox orderDropdown;

    private void updateContainer() {
        buttonContainer.getChildren().clear();
        for (Item i: itemsToShow) {
            buttonContainer.getChildren().add(i.getButton());
        }
    }

    private void restoreShownItemsToDefault() {
        itemsToShow = (ArrayList<Item>) items.clone();
    }

    private void restoreFilterFieldsToDefault() {
        orderDropdown.getSelectionModel().selectFirst();
        sortByDropdown.getSelectionModel().selectFirst();
        genreDropdown.getSelectionModel().selectFirst();
        categoryDropdown.getSelectionModel().selectFirst();
        availableCheckbox.setSelected(true);
    }

    static class sortShownItemsByPrice implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            return Double.compare(o1.getPrice(), o2.getPrice());
        }
    }

    static class sortShownItemsByTitle implements Comparator<Item> {
        @Override
        public int compare(Item o1, Item o2) {
            return o1.getName().compareTo(o2.getName());
        }
    }

    public void filterShownItemsByGenre(String genre) {
        itemsToShow = itemsToShow.stream().filter(i -> i.getGenre().equals(genre)).collect(Collectors.toCollection(ArrayList<Item>::new));
    }

    public void filterShownItemsByCategory(String category) {
        itemsToShow = itemsToShow.stream().filter(i -> i.getCategory().equals(category)).collect(Collectors.toCollection(ArrayList<Item>::new));
    }

    public void filterShownItemsByAvailability() {
        itemsToShow = itemsToShow.stream().filter(i -> i.getAmount() > 0).collect(Collectors.toCollection(ArrayList<Item>::new));
    }

    static <T> Comparator<T> createComparatorChain(ArrayList<Comparator<T>> comparators) {
        Comparator<T> result = null;
        for (Comparator<T> comparator : comparators) {
            if (result == null) {
                result = comparator;
            } else {
                result = result.thenComparing(comparator);
            }
        }
        return result;
    }

    public void restoreContainerToDefault(Event e) {
        restoreShownItemsToDefault();
        restoreFilterFieldsToDefault();

        itemsToShow.sort(new sortShownItemsByTitle());
        filterShownItemsByAvailability();
        updateContainer();
    }

    public void sortItems(Event e) {
        restoreShownItemsToDefault();
        ArrayList<Comparator<Item>> comparators = new ArrayList<>();

        String genre = (String) genreDropdown.getValue();
        if (!genre.equals("any")) {
            filterShownItemsByGenre(genre);
        }

        String category = (String) categoryDropdown.getValue();
        if (!category.equals("any")) {
            filterShownItemsByCategory(category);
        }

        String sortBy = (String) sortByDropdown.getValue();
        if (sortBy.equals("title")) {
            comparators.add(new sortShownItemsByTitle());
        } else {
            comparators.add(new sortShownItemsByPrice());
        }

        if (availableCheckbox.isSelected()) {
            filterShownItemsByAvailability();
        }

        String order = (String) orderDropdown.getValue();
        if (order.equals("descending")) {
            Comparator<Item> lastComparator = comparators.get(comparators.size() - 1);
            comparators.set(comparators.size() - 1, lastComparator.reversed());
        }

        itemsToShow.sort(createComparatorChain(comparators));

        updateContainer();
    }
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        genreDropdown.setItems(FXCollections.observableArrayList(new String[]{"any", "home video", "thriller", "kids", "romance"}));
        orderDropdown.setItems(FXCollections.observableArrayList(new String[]{"ascending", "descending"}));

        sortByDropdown.setItems(FXCollections.observableArrayList(new String[]{"title", "price"}));
        categoryDropdown.setItems(FXCollections.observableArrayList(new String[]{"any", "dvd", "game", "record"}));

        restoreFilterFieldsToDefault();

        items.add(new Item("adventures of dat", 12, "home video", 123.4, new GregorianCalendar(2003, 1, 3), "dvd"));
        items.add(new Item("swallowing his genuine sentiments", 7, "thriller", 15.6, new GregorianCalendar(1965, 4, 23), "dvd"));
        items.add(new Item("up", 3, "kids", 145.0, new GregorianCalendar(1988, 8, 10), "game"));
        items.add(new Item("love money rock'n'roll", 0, "romance", 987.1, new GregorianCalendar(2011, 11, 17), "game"));
        items.add(new Item("thank you for your love", 12, "home video", 1212.4, new GregorianCalendar(2004, 1, 3), "record"));
        items.add(new Item("thank you for your hear", 0, "thriller", 1123.6, new GregorianCalendar(1966, 4, 23), "record"));
        items.add(new Item("oh please don't ever be a lonely heart again", 6, "kids", 15.0, new GregorianCalendar(1989, 8, 10), "record"));
        items.add(new Item("rap snitches", 4, "romance", 98712.1, new GregorianCalendar(2012, 11, 17), "record"));

        restoreShownItemsToDefault();

        for (int i = 0 ; i < 8; i++) {
            String filePath = "ItemPage.fxml";

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(filePath));
                Scene scene = new Scene(fxmlLoader.load());

                itemsToShow.get(i).setController(fxmlLoader.getController());

                itemsToShow.get(i).getController().setTitle(itemsToShow.get(i).getName());
                itemsToShow.get(i).getController().setQuantity(itemsToShow.get(i).getAmount());
                itemsToShow.get(i).getController().setGenre(itemsToShow.get(i).getGenre());
                itemsToShow.get(i).getController().setPrice(itemsToShow.get(i).getPrice());
                itemsToShow.get(i).getController().setCart(cart);

                Button button = new Button();
                button.setText(itemsToShow.get(i).getName() + " " + itemsToShow.get(i).getAmount() + " " + itemsToShow.get(i).getGenre() + " " + itemsToShow.get(i).getPrice() + " " + itemsToShow.get(i).getCategory());

                itemsToShow.get(i).setButton(button);
                itemsToShow.get(i).setScene(scene);

                buttonContainer.getChildren().add(button);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }
}
