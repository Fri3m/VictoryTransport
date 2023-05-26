module com.example.victorytransport {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.victorytransport to javafx.fxml;
    exports com.example.victorytransport;
}