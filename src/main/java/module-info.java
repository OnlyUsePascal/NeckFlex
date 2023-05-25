module com.groupproject {
    requires javafx.controls;
    requires javafx.fxml;

    opens com.groupproject to javafx.fxml;
    exports com.groupproject;

    opens com.groupproject.controller.page to javafx.fxml;
    exports com.groupproject.controller.page;

    opens com.groupproject.controller.component to javafx.fxml;
    exports com.groupproject.controller.component;

    opens com.groupproject.entity.generic to javafx.fxml;
    exports com.groupproject.entity.generic;

    opens com.groupproject.toolkit to javafx.fxml;
    exports com.groupproject.toolkit;

    exports com.groupproject.entity.Constant;
    opens com.groupproject.entity.Constant to javafx.fxml;

    opens com.groupproject.controller.popup to javafx.fxml;
    exports com.groupproject.controller.popup;
    exports com.groupproject.controller;
    opens com.groupproject.controller to javafx.fxml;
    exports com.groupproject.entity;
    opens com.groupproject.entity to javafx.fxml;
}