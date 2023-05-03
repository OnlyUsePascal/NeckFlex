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

    opens com.groupproject.entity.runtime to javafx.fxml;
    exports com.groupproject.entity.runtime;

    opens com.groupproject.toolkit to javafx.fxml;
    exports com.groupproject.toolkit;

    exports com.groupproject.toolkit.Constant;
    opens com.groupproject.toolkit.Constant to javafx.fxml;
}