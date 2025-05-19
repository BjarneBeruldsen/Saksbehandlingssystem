module com.example.gruppe15eksamen {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.gruppe15eksamen to javafx.fxml;
    exports com.example.gruppe15eksamen;
}