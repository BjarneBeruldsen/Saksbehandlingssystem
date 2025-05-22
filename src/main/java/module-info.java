module com.example.gruppe15eksamen {
    requires javafx.controls;
    requires transitive javafx.graphics;
    requires transitive java.sql;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;
    requires jdk.net;

    exports com.example.gruppe15eksamen.client;
    exports com.example.gruppe15eksamen.common;
    exports com.example.gruppe15eksamen.server.dao;
    exports com.example.gruppe15eksamen.server.network;
    exports com.example.gruppe15eksamen.server.util;

}
