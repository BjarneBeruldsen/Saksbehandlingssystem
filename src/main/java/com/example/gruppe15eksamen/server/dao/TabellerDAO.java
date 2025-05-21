package com.example.gruppe15eksamen.server.dao;

import com.example.gruppe15eksamen.server.util.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class TabellerDAO {

    //En metode for å opprette tabellen kategori og sette inn data (ENUMS) i form av varchar
    private static void lagKategoriTabell() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Kategori (
              kategoriId   INT AUTO_INCREMENT PRIMARY KEY,
              kategoriNavn VARCHAR(25) NOT NULL UNIQUE
                CHECK (kategoriNavn IN ('UI_FEIL','BACKEND_FEIL','FUNKSJONSFORESPØRSEL'))
            );
            """;
        String sqlData = """
            INSERT IGNORE INTO Kategori (kategoriNavn)
            VALUES ('UI_FEIL'),('BACKEND_FEIL'),('FUNKSJONSFORESPØRSEL');
            """;
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sqlData);
            System.out.println("Tabellen Kategori er opprettet og fylt med ENUM verdier");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette eller fylle tabellen kategori");
            e.printStackTrace();
        }
    }
    //En metode for å opprette tabellen prioritet og sette inn data (ENUMS) i form av varchar
    private static void lagPrioritetTabell() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Prioritet (
              prioritetId   INT AUTO_INCREMENT PRIMARY KEY,
              prioritetNavn VARCHAR(10) NOT NULL UNIQUE
                CHECK (prioritetNavn IN ('LAV','MIDDELS','HØY'))
            );
            """;
        String sqlData = """
            INSERT IGNORE INTO Prioritet (prioritetNavn)
            VALUES ('LAV'),('MIDDELS'),('HØY');
            """;
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sqlData);
            System.out.println("Tabellen Prioritet er opprettet og fylt med ENUM verdier");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette eller fylle tabellen prioritet");
            e.printStackTrace();
        }
    }
    //En metode for å opprette tabellen status og sette inn data (ENUMS) i form av varchar
    private static void lagStatusTabell() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Status (
              statusId   INT AUTO_INCREMENT PRIMARY KEY,
              statusNavn VARCHAR(15) NOT NULL UNIQUE
                CHECK (statusNavn IN (
                  'INNSENDT','TILDELT','PÅGÅR','RETTET','LØST','TEST_MISLYKTES','LUKKET'
                ))
            );
            """;
        String sqlData = """
            INSERT IGNORE INTO Status (statusNavn)
            VALUES ('INNSENDT'),('TILDELT'),('PÅGÅR'),
                   ('RETTET'),('LØST'),('TEST_MISLYKTES'),('LUKKET');
            """;
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sqlData);
            System.out.println("Tabellen Status er opprettet og fylt med ENUM verdier");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette eller fylle tabellen status");
            e.printStackTrace();
        }
    }
    //En metode for å opprette tabellen rolle og sette inn data (ENUMS) i form av varchar
    private static void lagRolleTabell() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Rolle (
              rolleId    INT AUTO_INCREMENT PRIMARY KEY,
              rolleNavn  VARCHAR(15) NOT NULL UNIQUE
                CHECK (rolleNavn IN ('LEDER','TESTER','UTVIKLER'))
            );
            """;
        String sqlData = """
            INSERT IGNORE INTO Rolle (rolleNavn)
            VALUES ('LEDER'),('TESTER'),('UTVIKLER');
            """;
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            stmt.executeUpdate(sqlData);
            System.out.println("Tabellen Rolle er opprettet og fylt med ENUM verdier");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette eller fylle tabellen rolle");
            e.printStackTrace();
        }
    }
    //Oppretter tabellen brukere
    private static void lagBrukereTabell() {
        String sqlOpprett = """
        CREATE TABLE IF NOT EXISTS Brukere (
          brukerId INT AUTO_INCREMENT PRIMARY KEY,
          navn     VARCHAR(45) NOT NULL UNIQUE,
          rolleId  INT NOT NULL,
          FOREIGN KEY (rolleId) REFERENCES Rolle(rolleId)
        );
        """;

        //legger til tre brukere med hver sin rolle for testing
        String sqlData = """
        INSERT IGNORE INTO Brukere (navn, rolleId) VALUES
        ('Bruker_Tester', 1),
        ('Bruker_Utvikler', 2),
        ('Bruker_Leder', 3);
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sqlOpprett);
            System.out.println("Tabellen Brukere er opprettet");

            stmt.executeUpdate(sqlData);
            System.out.println("Tre brukere er lagt til i tabellen Brukere");

        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette tabellen Brukere eller legge til brukere");
            e.printStackTrace();
        }
    }
    //Oppretter tabellen sak
    private static void lagSakTabell() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Sak (
              sakId              INT AUTO_INCREMENT PRIMARY KEY,
              tittel             VARCHAR(100) NOT NULL,
              beskrivelse        TEXT,
              rapportorBrukerId  INT NOT NULL,
              mottakerBrukerId   INT,
              prioritetId        INT NOT NULL,
              statusId           INT NOT NULL,
              kategoriId         INT NOT NULL,
              tidsstempel        DATETIME DEFAULT CURRENT_TIMESTAMP,
              oppdatertTidspunkt DATETIME
                DEFAULT CURRENT_TIMESTAMP
                ON UPDATE CURRENT_TIMESTAMP,
              FOREIGN KEY (rapportorBrukerId) REFERENCES Brukere(brukerId),
              FOREIGN KEY (mottakerBrukerId)  REFERENCES Brukere(brukerId),
              FOREIGN KEY (prioritetId)        REFERENCES Prioritet(prioritetId),
              FOREIGN KEY (statusId)           REFERENCES Status(statusId),
              FOREIGN KEY (kategoriId)         REFERENCES Kategori(kategoriId)
            );
            """;
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