package com.example.gruppe15eksamen.server.dao;

import com.example.gruppe15eksamen.server.util.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TabellerDAO {

    //En metode for å opprette tabellen kategori
    private static void lagKategoriTabell() {
        String sql = ""
                + "CREATE TABLE IF NOT EXISTS Kategori ("
                + "  kategoriId INT AUTO_INCREMENT PRIMARY KEY, "
                + "  navn VARCHAR(45)"
                + ");";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabellen Kategori er opprettet");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette tabellen Kategori");
            e.printStackTrace();
        }
    }
    //En metode for å opprette tabellen prioritet
    private static void lagPrioritetTabell() {
        String sql = ""
                + "CREATE TABLE IF NOT EXISTS Prioritet ("
                + "  prioritetId INT AUTO_INCREMENT PRIMARY KEY, "
                + "  navn VARCHAR(45)"
                + ");";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabellen Prioritet er opprettet");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette tabellen Prioritet");
            e.printStackTrace();
        }
    }
    //En metode for å opprette tabellen status
    private static void lagStatusTabell() {
        String sql = ""
                + "CREATE TABLE IF NOT EXISTS Status ("
                + "  statusId INT AUTO_INCREMENT PRIMARY KEY, "
                + "  navn VARCHAR(45)"
                + ");";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabellen Status er opprettet");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette tabellen Status");
            e.printStackTrace();
        }
    }
    //En metode for å opprette tabellen rolle
    private static void lagRolleTabell() {
        String sql = ""
                + "CREATE TABLE IF NOT EXISTS Rolle ("
                + "  rolleId INT AUTO_INCREMENT PRIMARY KEY, "
                + "  navn VARCHAR(45)"
                + ");";
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabellen Rolle er opprettet");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette tabellen Rolle");
            e.printStackTrace();
        }
    }
    //En metode for å opprette brukeretabell
    private static void lagBrukereTabell() {
        String sql = ""
                + "CREATE TABLE IF NOT EXISTS Brukere ("
                + "  brukerId INT AUTO_INCREMENT PRIMARY KEY, "
                + "  navn VARCHAR(45), "
                + "  rolleId INT, "
                + "  FOREIGN KEY (rolleId) REFERENCES Rolle(rolleId)"
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
    //En metode for å opprette saktabell
    private static void lagSakTabell() {
        String sql = ""
                + "CREATE TABLE IF NOT EXISTS Sak ("
                + "  sakId INT AUTO_INCREMENT PRIMARY KEY, "
                + "  tittel VARCHAR(45), "
                + "  beskrivelse VARCHAR(45), "
                + "  rapportorBrukerId INT, "
                + "  mottakerBrukerId INT, "
                + "  prioritetId INT, "
                + "  statusId INT, "
                + "  kategoriId INT, "
                + "  tidsstempel DATETIME, "
                + "  oppdatertTidspunkt DATETIME, "
                + "  FOREIGN KEY (rapportorBrukerId) REFERENCES Brukere(brukerId), "
                + "  FOREIGN KEY (mottakerBrukerId) REFERENCES Brukere(brukerId), "
                + "  FOREIGN KEY (prioritetId) REFERENCES Prioritet(prioritetId), "
                + "  FOREIGN KEY (statusId) REFERENCES Status(statusId), "
                + "  FOREIGN KEY (kategoriId) REFERENCES Kategori(kategoriId)"
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
    //Samler alle tabellene i en metode
    public static void opprettAlleTabeller() {
        lagKategoriTabell();
        lagPrioritetTabell();
        lagStatusTabell();
        lagRolleTabell();
        lagBrukereTabell();
        lagSakTabell();
    }
}