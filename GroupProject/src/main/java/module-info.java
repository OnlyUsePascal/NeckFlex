module com.groupproject {
    requires javafx.controls;
    requires javafx.fxml;

//    exports com.groupproject;
//    opens com.groupproject to javafx.fxml;

    exports com.groupproject.controller;
    opens com.groupproject.controller to javafx.fxml;

    exports com.groupproject.login;
    opens com.groupproject.login to javafx.fxml;

    exports com.groupproject.ControllerAdminInfo;
    opens com.groupproject.ControllerAdminInfo to javafx.fxml;
    exports com.groupproject.Page;
    opens com.groupproject.Page to javafx.fxml;
    exports com.groupproject.Entity;
    opens com.groupproject.Entity to javafx.fxml;

}