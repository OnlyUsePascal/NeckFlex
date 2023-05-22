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
    exports com.groupproject.SplashScreenTest;
    opens com.groupproject.SplashScreenTest to javafx.fxml;
    exports com.groupproject.ScrollPaneTest;
    opens com.groupproject.ScrollPaneTest to javafx.fxml;
    exports com.groupproject.PaneTesting;
    opens com.groupproject.PaneTesting to javafx.fxml;
}