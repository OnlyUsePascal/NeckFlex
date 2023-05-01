module com.groupproject {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.groupproject to javafx.fxml;
    exports com.groupproject;
    exports com.groupproject.bank;
    opens com.groupproject.bank to javafx.fxml;
}