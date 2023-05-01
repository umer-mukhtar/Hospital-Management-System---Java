module com.finalproject {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires java.sql;

    opens com.finalproject to javafx.fxml;
    exports com.finalproject;


}