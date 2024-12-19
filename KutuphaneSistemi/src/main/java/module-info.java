module org.example.kutuphanesistemi {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens org.example.kutuphanesistemi to javafx.fxml;
    exports org.example.kutuphanesistemi;
    exports org.example.kutuphanesistemi.controller;
    opens org.example.kutuphanesistemi.controller to javafx.fxml;
}