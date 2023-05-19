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

import java.io.*;
import java.net.URL;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.StringTokenizer;
import java.util.function.BiConsumer;
import java.util.Scanner;

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
    public RentalTime rentalTime;

    public Order(LocalDateTime orderTime, RentalTime rentalTime) {
        this.orderTime = orderTime;
        this.rentalTime = rentalTime;
    }

    public int checkOvertime() {
        return Duration.between(orderTime, LocalDateTime.now()).compareTo((Duration)rentalTime.periodInSeconds);
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

    private void renderOrder(Order order) {
        BiConsumer<Order, AnchorPane> cancelOrder = (o, a) -> {
            dateContainer.getChildren().remove(a);
//            orders.remove(o);
        };

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("OrderBasic.fxml"));
        try {
            AnchorPane item = fxmlLoader.load();
            OrderBasicController controller = fxmlLoader.getController();

//            controller.setDate(LocalDateTime.of(2023, 5, 14, 0, 0).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
            controller.setDate(LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));

            if (choiceBox.getValue() != null)
                controller.setType((String) choiceBox.getValue().toString());
            else
                controller.setType(order.rentalTime.toString());

            controller.setOuterOrder(order);
            controller.setOuterResponseLabel(response);
            controller.setOuterReturnOrderFunc(cancelOrder);
            controller.setOuterPlane(item);

            dateContainer.getChildren().add(item);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void makeOrder(Event event) {
        Order order = new Order(LocalDateTime.now(), (RentalTime) choiceBox.getValue());
//        Order order = new Order(LocalDateTime.of(2023, 5, 14, 0, 0), (RentalTime) choiceBox.getValue());
        orders.add(order);

        renderOrder(order);
    }

    public void loadOrderData() {
        try {
            File file = new File(new File("GroupProject/src/main/resources/com/data/orders.txt").getAbsolutePath());

            if (file.exists()) {
                Scanner scanner = new Scanner(file);

                while (scanner.hasNextLine()) {
                    String data = scanner.nextLine();
                    StringTokenizer st = new StringTokenizer(data, "|");
                    ArrayList<String> list = new ArrayList<>();

                    while (st.hasMoreTokens()) {
                        list.add(st.nextToken());
                    }

                    Order order = new Order(LocalDateTime.parse(list.get(0)), RentalTime.valueOf(list.get(1)));
                    orders.add(order);
                    renderOrder(order);
                }
            } else {
                System.out.println("The file does not exist.");
            }
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    public void saveOrderData() {
        try {
            File inFile = new File(new File("GroupProject/src/main/resources/com/data/orders.txt").getAbsolutePath());
            PrintWriter printWriter = new PrintWriter(inFile);
            for (Order o : orders) {
                printWriter.println(o.orderTime.toString() + '|' + o.rentalTime.name());
            }
            printWriter.close();
        } catch(IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
//        try {
//            ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File("GroupProject/src/main/resources/com/data/orders.txt").getAbsolutePath()));
//            String[] arr = (String[])inputStream.readObject();
//            System.out.println(arr);
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
        loadOrderData();

        choiceBox.getItems().setAll(RentalTime.values());
        choiceBox.getSelectionModel().selectFirst();
    }
}
