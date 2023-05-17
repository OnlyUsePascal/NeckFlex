package com.groupproject;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

enum RentalTime {
    TWO_DAYS(Duration.ofDays(2), "Two Days"),
    SEVEN_DAYS(Duration.ofDays(7), "Seven Days");

    public final Duration periodInSeconds;
    private final String name;

    RentalTime(Duration period, String name) {
        this.periodInSeconds = period;
        this.name = name;
    }

    @Override
    public String toString() {
        return this.name;
    }
}

class Order {
    public LocalDateTime orderTime;
    public Duration rentalTime;

    public Order(LocalDateTime orderTime, RentalTime rentalTime) {
        this.orderTime = orderTime;
        this.rentalTime = rentalTime.periodInSeconds;
    }

    public int checkOvertime() {
        return Duration.between(orderTime, LocalDateTime.now()).compareTo(rentalTime);
    }
}
public class MakeOrderBasicController implements Initializable {
    @FXML
    private VBox dateContainer;

    @FXML
    private AnchorPane orderPlane;

    @FXML
    private ComboBox choiceBox;

    @FXML
    private Button orderButton;

    @FXML
    private Label response;

    private ArrayList<Order> orders = new ArrayList<>();

    public void makeOrder(Event event) {
//        Order order = new Order(LocalDateTime.now(), (RentalTime) choiceBox.getValue());
        Order order = new Order(LocalDateTime.of(2023, 5, 14, 0, 0), (RentalTime) choiceBox.getValue());
        orders.add(order);

        BiConsumer<Order, AnchorPane> cancelOrder = (o, a) -> {
            dateContainer.getChildren().remove(a);
            orders.remove(o);
        };

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderBasic.fxml"));
        try {
            AnchorPane item = fxmlLoader.load();
            OrderBasicController controller = fxmlLoader.getController();

            controller.setDate(LocalDateTime.of(2023, 5, 14, 0, 0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
//            controller.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            controller.setType((String) choiceBox.getValue().toString());
            controller.setOuterOrder(order);
            controller.setOuterResponseLabel(response);
            controller.setOuterReturnOrderFunc(cancelOrder);
            controller.setOuterPlane(item);

            dateContainer.getChildren().add(item);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        choiceBox.getItems().setAll(RentalTime.values());
        choiceBox.getSelectionModel().selectFirst();
    }
}
