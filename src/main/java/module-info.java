module com.example.gruppe15eksamen {
    requires javafx.controls;
    requires java.sql;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    exports com.example.gruppe15eksamen.client;
    exports com.example.gruppe15eksamen.common;
    exports com.example.gruppe15eksamen.server.dao;
    exports com.example.gruppe15eksamen.server.network;
    exports com.example.gruppe15eksamen.server.util;

}
