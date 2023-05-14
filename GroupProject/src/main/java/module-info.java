module com.groupproject {
    requires javafx.controls;
    requires javafx.fxml;

//    exports com.groupproject;
//    opens com.groupproject to javafx.fxml;

    exports com.groupproject.controller;
    opens com.groupproject.controller to javafx.fxml;

    exports com.groupproject.login;
    opens com.groupproject.login to javafx.fxml;

    opens com.groupproject.Entity to javafx.fxml;
    exports com.groupproject.ImportImage;
    opens com.groupproject.ImportImage to javafx.fxml;
    exports com.groupproject.AdminInfo;
    opens com.groupproject.AdminInfo to javafx.fxml;
}