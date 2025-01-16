module com.example.ooppage10 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ooppage10 to javafx.fxml;
    exports com.example.ooppage10;
}