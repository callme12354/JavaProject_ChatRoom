module com.example.csc1004javaprojectchatroom {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens com.example.csc1004javaprojectchatroom to javafx.fxml;
    exports com.example.csc1004javaprojectchatroom;
}