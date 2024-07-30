module com.example.chessbot {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.chessbot to javafx.fxml;
    exports com.example.chessbot;
}