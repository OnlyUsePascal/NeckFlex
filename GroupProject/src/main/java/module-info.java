module com.groupproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.groupproject to javafx.fxml;
    exports com.groupproject;
    exports com.groupproject.controller;
    opens com.groupproject.controller to javafx.fxml;
    exports com.groupproject.login;
    opens com.groupproject.login to javafx.fxml;
}