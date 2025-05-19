module com.example.gruppe15eksamen {
    requires javafx.controls;
    requires java.sql;
    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    //Må kalle på package i de ulike filene (undermappene)
    exports com.example.gruppe15eksamen.client;
    //exports com.example.gruppe15eksamen.client.controller;
   // exports com.example.gruppe15eksamen.client.network;
    // exports com.example.gruppe15eksamen.client.view;

    //exports com.example.gruppe15eksamen.model;

    //exports com.example.gruppe15eksamen.server.dao;
    //exports com.example.gruppe15eksamen.server.network;
    //exports com.example.gruppe15eksamen.server.util;
}
