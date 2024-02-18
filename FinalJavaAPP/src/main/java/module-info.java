module com.example.finaljavaapp {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.finaljavaapp to javafx.fxml;
    exports com.example.finaljavaapp;
}