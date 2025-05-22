/**
 * @author: Laurent Zogaj
 * En DAO-klasse som oppretter tabeller i databasen for saksbehandlingssystemet vårt.
 * Klassen oppretter tabellene: Kategori, Prioritet, Status, Rolle, Brukere og Sak.
 * Hver tabell har egne metoder for opprettelse og innsetting av enum-verdier som data(Kategori, prioritet, status, rolle).
 * Tabellene kobles sammen med foreign keys. 
 * Klassen har en metode som oppretter alle tabellene i riktig rekkefølge.
 * Vi har også validering med CHECK constraint på enum-verdiene i tabellene Kategori, Prioritet, Status og Rolle, for ekstra sikkerhet i tillegg til at vi bruker enum-verdier.
 */
package com.example.gruppe15eksamen.server.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import com.example.gruppe15eksamen.server.util.DatabaseUtil;
public class TabellerDAO {

    /**
     * Oppretter tabellen Kategori og setter inn enum-verdier som data.
     */
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

    /**
     * Oppretter tabellen Prioritet og setter inn enum-verdier som data.
     */
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

    /**
     * Oppretter tabellen Status og setter inn enum-verdier som data.
     */
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

    /**
     * Oppretter tabellen Rolle og setter inn enum-verdier som data.
     */
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

    /**
     * Oppretter tabellen Brukere og legger inn eksempelbrukere for hver rolle.
     */
    private static void lagBrukereTabell() {
        String sqlOpprett = """
        CREATE TABLE IF NOT EXISTS Brukere (
          brukerId INT AUTO_INCREMENT PRIMARY KEY,
          navn     VARCHAR(45) NOT NULL UNIQUE,
          rolleId  INT NOT NULL,
          FOREIGN KEY (rolleId) REFERENCES Rolle(rolleId)
        );
        """;

        //Eksempelbrukere med roller for test
        String sqlData = """
        INSERT IGNORE INTO Brukere (navn, rolleId) VALUES
        ('Bruker_Leder', 1),
        ('Bruker_Tester', 2),
        ('Bruker_Utvikler', 3);
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

    /**
     * Oppretter tabellen Sak med foreign keys og legger til kolonnene utviklerKommentar og testerTilbakemelding.
     */
    private static void lagSakTabell() {
        String sql = """
            CREATE TABLE IF NOT EXISTS Sak (
              sakId              INT AUTO_INCREMENT PRIMARY KEY,
              tittel             VARCHAR(100) NOT NULL,
              beskrivelse        TEXT,
              rapportørBrukerId  INT NOT NULL,
              mottakerBrukerId   INT,
              prioritetId        INT NOT NULL,
              statusId           INT NOT NULL,
              kategoriId         INT NOT NULL,
              utviklerKommentar  VARCHAR(255),
              testerTilbakemelding VARCHAR(255),
              tidsstempel        DATETIME DEFAULT CURRENT_TIMESTAMP,
              oppdatertTidspunkt DATETIME
                DEFAULT CURRENT_TIMESTAMP
                ON UPDATE CURRENT_TIMESTAMP,
              FOREIGN KEY (rapportørBrukerId) REFERENCES Brukere(brukerId),
              FOREIGN KEY (mottakerBrukerId)  REFERENCES Brukere(brukerId),
              FOREIGN KEY (prioritetId)        REFERENCES Prioritet(prioritetId),
              FOREIGN KEY (statusId)           REFERENCES Status(statusId),
              FOREIGN KEY (kategoriId)         REFERENCES Kategori(kategoriId)
            );
            """;
        try (Connection conn = DatabaseUtil.getConnection();
             Statement stmt = conn.createStatement()) {
            stmt.executeUpdate(sql);
            System.out.println("Tabellen Sak er opprettet med kolonnene utviklerKommentar og testerTilbakemelding.");
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke opprette tabellen Sak.");
            e.printStackTrace();
        }
    }

    /**
     * Legger til alle tabeller til en metode, da trenger vi ikke å kalle på hver enkelt tabell.
     */
    public static void opprettAlleTabeller() {
        lagKategoriTabell();
        lagPrioritetTabell();
        lagStatusTabell();
        lagRolleTabell();
        lagBrukereTabell();
        lagSakTabell();
    }
}