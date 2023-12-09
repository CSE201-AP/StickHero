module com.example.stickhero {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.swing;
    requires javafx.media;

    opens com.example.stickhero to javafx.fxml;
    exports com.example.stickhero;
    exports com.example.stickhero.environment;
    exports com.example.stickhero.spritesheet;
    exports com.example.stickhero.sprite;
    opens com.example.stickhero.environment to javafx.fxml;
}