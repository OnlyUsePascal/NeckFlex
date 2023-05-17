module com.groupproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.groupproject to javafx.fxml;
    exports com.groupproject;
    exports com.groupproject.controllers;
    opens com.groupproject.controllers to javafx.fxml;
    exports com.groupproject.types;
    opens com.groupproject.types to javafx.fxml;
    exports com.groupproject.Item;
    opens com.groupproject.Item to javafx.fxml;
}