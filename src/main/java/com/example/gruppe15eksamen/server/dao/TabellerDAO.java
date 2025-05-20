package com.example.gruppe15eksamen.server.dao;

import com.example.gruppe15eksamen.server.util.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TabellerDAO {


    //En metode for å opprette saktabelll
    public static void lagSakTabell() {
        String sql = ""
                + "CREATE TABLE IF NOT EXISTS Sak ("
                + "  sakID INT AUTO_INCREMENT PRIMARY KEY,"
                + "  tittel VARCHAR(50),"
                + "  beskrivelse VARCHAR(100),"
                + "  prioritet VARCHAR(50),"
                + "  kategori VARCHAR(50),"
                + "  status VARCHAR(50),"
                + "  rapportør VARCHAR(50),"
                + "  mottaker VARCHAR(50),"
                + "  tidsstempel DATETIME"
                + ");";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabellen Sak er opprettet");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette tabellen Sak");
            e.printStackTrace();
        }
    }

    //En metode for å opprette brukeretabell
    public static void lagBrukereTabell() {
        String sql = ""
                + "CREATE TABLE IF NOT EXISTS Brukere ("
                + "  id INT AUTO_INCREMENT PRIMARY KEY,"
                + "  navn VARCHAR(100),"
                + "  rolle VARCHAR(100)"
                + ");";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabellen Brukere er opprettet");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette tabellen Brukere");
            e.printStackTrace();
        }
    }
}
