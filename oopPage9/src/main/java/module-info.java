module com.example.ooppage9 {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.ooppage9 to javafx.fxml;
    exports com.example.ooppage9;
}