package com.example.gruppe15eksamen.server.util;
/*Inneholder en metode for å hente databasetilkobling */
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseUtil {
    private static final String CONFIG_FILE = "src/main/db.properties";

    //Metode for å hente kobling til database
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream(CONFIG_FILE);
        props.load(fis);

        String url = props.getProperty("db.url");
        String user = props.getProperty("db.username");
        String password = props.getProperty("db.password");

        return DriverManager.getConnection(url, user, password);
    }

}

