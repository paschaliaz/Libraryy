module com.mycompany.library {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires java.base;

    opens com.mycompany.library to javafx.fxml;
    exports com.mycompany.library;
    opens com.mycompany.library.model;
}
