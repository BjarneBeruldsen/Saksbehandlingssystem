/**
 * @author: Laurent Zogaj
 * En databaseUtil klasse som kan bli sett på som en konfig klasse for å opprette en kobling til databasen og opprette databasen hvis den ikke finnes.
 * Vi bruker mysql-connector-driveren for å opprette en kobling til databasen.
 * Samt at vi hener inn verdier fra properties filen 
 * Vi returnerer en kobling til databasen som kan brukes i de forskjellige DAO klassene og andre klasser som trenger å koble seg til databasen.
 * @return En Connection-objekt til tilkobling til databasen, som kan hentes i DAO-klassene for eks.
 * @throws SQLException Hvis det oppstår SQL-feil ved kobling eller oppretting av databasen
 * @throws IOException  Hvis property-filen ikke kan leses
 */
package com.example.gruppe15eksamen.server.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class DatabaseUtil {
    private static final String CONFIG_FILE = "src/main/resources/db.properties"; //Selve property filen der verdiene for oppkobling mot databasen ligger

    //Oppretter lesing mot konfig filen (property)
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
        try (FileInputStream fis = new FileInputStream(CONFIG_FILE)) {
            props.load(fis);
        } catch (IOException e) {
            throw new IOException("Kunne ikke lese fil " + CONFIG_FILE, e);
        }

        //Henter verdier fra properties
        String url  = props.getProperty("db.url");
        String user     = props.getProperty("db.username");
        String password = props.getProperty("db.password");
        String dbName   = props.getProperty("db.name");

        //Oppretter database hvis den ikke finnes
        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt     = conn.createStatement()) {
            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS " + dbName);
        } catch (SQLException e) {
            throw new SQLException("Feil ved opprettelse av database '" + dbName + "'", e);
        }
        //Oppkobling mot databasen
        try {
            return DriverManager.getConnection(url + dbName, user, password);
        } catch (SQLException e) {
            throw new SQLException("Kunne ikke koble til database '" + dbName + "'", e);
        }
    }
}