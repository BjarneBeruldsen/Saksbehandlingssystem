/**
 * Dette er DAO-klassen for Sak
 * som håndterer databaseoperasjoner relatert til sakene.
 * @author: Laurent Zogaj, Bjarne Beruldsen & Abdinasir Ali
 */
package com.example.gruppe15eksamen.server.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.example.gruppe15eksamen.common.Kategori;
import com.example.gruppe15eksamen.common.Prioritet;
import com.example.gruppe15eksamen.common.Sak;
import com.example.gruppe15eksamen.common.Soking;
import com.example.gruppe15eksamen.common.Status;
import com.example.gruppe15eksamen.server.util.DatabaseUtil;

public class SakDAO {

    /**
     * Oppdaterer status og utviklerkommentar for en gitt sak.
     * @param sakId            ID til saken som skal oppdateres
     * @param nyStatus         Ny status som skal settes
     * @param utviklerKommentar Kommentar fra utvikler som skal lagres
     * @return Antall rader oppdatert (1 hvis suksess, 0 ellers)
     */
    public static int oppdaterStatus(int sakId, Status nyStatus, String utviklerKommentar) {
        String sql = """
        UPDATE sak
        SET statusId = (
            SELECT statusId FROM status WHERE statusNavn = ?
        ),
        utviklerKommentar = ?
        WHERE sakID = ?
    """;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nyStatus.name()); // Bruker statusNavn fra enum
            pstmt.setString(2, utviklerKommentar); // Setter utviklerKommentar
            pstmt.setInt(3, sakId); // Setter sakID
            return pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke oppdatere status og utviklerkommentar på sak " + sakId + ": " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Oppdaterer status for en sak uten kommentarer (brukes av leder).
     * @param sakId ID til saken som skal oppdateres
     * @param nyStatus Ny status som skal settes
     * @return Antall rader oppdatert (1 hvis suksess, 0 ellers)
     */
    public static int oppdaterStatusLeder(int sakId, Status nyStatus) {
        String sql = """
        UPDATE sak
        SET statusId = (
            SELECT statusId FROM status WHERE statusNavn = ?
        )
        WHERE sakID = ?
    """;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nyStatus.name()); // Setter status
            pstmt.setInt(2, sakId); // Setter sakID
            return pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke oppdatere status på sak " + sakId + ": " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }

    /**
     * Oppdaterer status og testerTilbakemelding for en gitt sak.
     * @param sakId               ID til saken som skal oppdateres
     * @param nyStatus            Ny status som skal settes
     * @param testerTilbakemelding Tilbakemelding fra tester som skal lagres
     * @return Antall rader oppdatert (1 hvis suksess, 0 ellers)
     */
    public static int oppdaterStatusMedTesterTilbakemelding(int sakId, Status nyStatus, String testerTilbakemelding) {
        String sql = """
        UPDATE sak
        SET statusId = (
            SELECT statusId FROM status WHERE statusNavn = ?
        ),
        testerTilbakemelding = ?
        WHERE sakID = ?
    """;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nyStatus.name()); // Bruker statusNavn fra enum
            pstmt.setString(2, testerTilbakemelding); // Setter testerTilbakemelding
            pstmt.setInt(3, sakId); // Setter sakID
            return pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke oppdatere status og testerTilbakemelding på sak " + sakId + ": " + e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }



    /**
     * Tildeler en sak til en gitt utvikler (brukernavn).
     * @param sakId      ID til saken som skal tildeles
     * @param brukernavn Brukernavn til utvikler som skal motta saken
     * @return Antall rader oppdatert (1 hvis suksess, 0 ellers)
     */
    public static int tildelSak(int sakId, String brukernavn) {
        System.out.println("sakID:" + sakId + "brukernavn" + brukernavn);
        String sql = """
            UPDATE sak
            SET mottakerBrukerId = (
                SELECT brukerId FROM brukere WHERE navn = ?
            )
            WHERE sakId = ?
            """;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, brukernavn);
            ps.setInt(2, sakId);
            return ps.executeUpdate();
        } catch (SQLException | IOException e) {
            System.err.printf("Kunne ikke tildele sak til" + sakId, brukernavn, e.getMessage());
            e.printStackTrace();
            return 0;
        }
    }
    /**
     * Oppretter (lager) en ny sak i databasen.
     * @param sak Sak-objektet som skal lagres
     * @return Antall rader oppdatert (1 hvis suksess, 0 ellers)
     * @throws SQLException Hvis databasefeil oppstår
     * @throws IOException  Hvis IO-feil oppstår ved tilkobling
     */
    public static int insertSak(Sak sak) throws SQLException, IOException{
        String query = """
                INSERT INTO sak (tittel, beskrivelse, rapportørBrukerId, prioritetId, statusId,
                kategoriId, tidsstempel, oppdatertTidspunkt)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try(Connection conn = DatabaseUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);
            //Henter rapportørens id basert på brukernavn
            int rapportørId = BrukerDAO.hentBrukerIdFraNavn(sak.getRapportør());

            pstmt.setString(1, sak.getTittel());
            pstmt.setString(2, sak.getBeskrivelse());
            pstmt.setInt(3, rapportørId);
            pstmt.setInt(4, sak.getPrioritet().getId());
            pstmt.setInt(5, sak.getStatus().getId());
            pstmt.setInt(6, sak.getKategori().getId());
            pstmt.setTimestamp(7, Timestamp.valueOf(sak.getTidsstempel()));
            pstmt.setTimestamp(8, Timestamp.valueOf(sak.getOppdatertTidspunkt()));
            //Antall rader som er oppdatert
            return pstmt.executeUpdate();
        } catch (SQLException | IOException e) {
           e.printStackTrace();
           return 0;
        }

    }
    /**
     * Henter saker basert på sakID
     * @param sakID ID til saken
     * @return Liste med saker som matcher ID
     */
    public List<Sak> hentSaker(int sakID){
        List<Sak> saker = new ArrayList<>();
           String sql = """
        SELECT s.*, 
            br.navn AS rapportørNavn, 
            mo.navn AS mottakerNavn,
            p.prioritetNavn, 
            k.kategoriNavn, 
            st.statusNavn
        FROM sak s
        INNER JOIN brukere br ON s.rapportørBrukerId = br.brukerId
        INNER JOIN brukere mo ON s.mottakerBrukerId = mo.brukerId
        INNER JOIN prioritet p ON s.prioritetId = p.prioritetId
        INNER JOIN kategori k ON s.kategoriId = k.kategoriId
        INNER JOIN status st ON s.statusId = st.statusId
        WHERE s.sakID = ?
        """;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sakID);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Sak sak = new Sak();
                sak.setSakID(rs.getInt("sakID"));
                sak.setTittel(rs.getString("tittel"));
                sak.setBeskrivelse(rs.getString("beskrivelse"));
                sak.setPrioritet(Prioritet.valueOf(rs.getString("prioritet")));
                sak.setKategori(Kategori.valueOf(rs.getString("kategori")));
                sak.setStatus(Status.valueOf(rs.getString("status")));
                sak.setRapportør(rs.getString("rapportør"));
                sak.setMottaker(rs.getString("mottaker"));

                saker.add(sak);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return saker;
    }
    /**
     * Sletter en sak fra databasen basert på sakID.
     * @param sakID ID til saken som skal slettes
     */
    public void slettSak(int sakID){
    String sql = "DELETE FROM sak WHERE sakID = ?";
        try (Connection conn = DatabaseUtil.getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, sakID);
            int slettetRader = stmt.executeUpdate();
            if (slettetRader > 0) {
                System.out.println("Sak med ID " + sakID + " ble slettet.");
            } else {
                System.out.println("Ingen sak ble slettet med ID " + sakID);
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Henter alle saker i databasen.
     * @return ArrayList med alle saker
     */
    public static ArrayList<Sak> hentAlleSaker() {
        ArrayList<Sak> saker = new ArrayList<>();
        String sql = """
        SELECT s.sakId, s.tittel, s.beskrivelse, s.tidsstempel, s.oppdatertTidspunkt,
               rapportor.navn AS rapportorNavn,
               mottaker.navn AS mottakerNavn,
               p.prioritetNavn, st.statusNavn, k.kategoriNavn
        FROM Sak s
        JOIN Brukere rapportor ON s.rapportørBrukerId = rapportor.brukerId
        LEFT JOIN Brukere mottaker ON s.mottakerBrukerId = mottaker.brukerId
        JOIN Prioritet p ON s.prioritetId = p.prioritetId
        JOIN Status st ON s.statusId = st.statusId
        JOIN Kategori k ON s.kategoriId = k.kategoriId
        """;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Sak sak = new Sak();

                sak.setSakID(rs.getInt("sakId"));
                sak.setTittel(rs.getString("tittel"));
                sak.setBeskrivelse(rs.getString("beskrivelse"));
                sak.setTidsstempel(rs.getTimestamp("tidsstempel").toLocalDateTime());
                sak.setOppdatertTidspunkt(rs.getTimestamp("oppdatertTidspunkt").toLocalDateTime());

                sak.setRapportør(rs.getString("rapportorNavn"));

                String mottakerNavn = rs.getString("mottakerNavn");
                if (mottakerNavn != null) {
                    sak.setMottaker(mottakerNavn);
                }

                // Anta at du har enum-lignende klasser for disse
                sak.setPrioritet(Prioritet.valueOf(rs.getString("prioritetNavn")));
                sak.setStatus(Status.valueOf(rs.getString("statusNavn")));
                sak.setKategori(Kategori.valueOf(rs.getString("kategoriNavn")));

                saker.add(sak);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }

        return saker;
    }

    //metode som henter utvikler sine saker
    public static ArrayList<Sak> hentSakerTildeltUtvikler(int brukerId) {
        ArrayList<Sak> saker = new ArrayList<>();

        String sql = """
            SELECT s.sakId, s.tittel, s.beskrivelse, s.tidsstempel, s.oppdatertTidspunkt,
                   rapportor.navn AS rapportorNavn,
                   mottaker.navn AS mottakerNavn,
                   p.prioritetNavn, st.statusNavn, k.kategoriNavn
            FROM Sak s
            JOIN Brukere rapportor ON s.rapportørBrukerId = rapportor.brukerId
            LEFT JOIN Brukere mottaker ON s.mottakerBrukerId = mottaker.brukerId
            JOIN Prioritet p ON s.prioritetId = p.prioritetId
            JOIN Status st ON s.statusId = st.statusId
            JOIN Kategori k ON s.kategoriId = k.kategoriId
            WHERE s.mottakerBrukerId = ?
        """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, brukerId);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sak sak = new Sak();

                    sak.setSakID(rs.getInt("sakId"));
                    sak.setTittel(rs.getString("tittel"));
                    sak.setBeskrivelse(rs.getString("beskrivelse"));
                    sak.setTidsstempel(rs.getTimestamp("tidsstempel").toLocalDateTime());
                    sak.setOppdatertTidspunkt(rs.getTimestamp("oppdatertTidspunkt").toLocalDateTime());

                    sak.setRapportør(rs.getString("rapportorNavn"));

                    String mottakerNavn = rs.getString("mottakerNavn");
                    if (mottakerNavn != null) {
                        sak.setMottaker(mottakerNavn);
                    }

                    // Enum basert på navn (forutsetter at enum-navn matcher db-navn)
                    sak.setPrioritet(Prioritet.valueOf(rs.getString("prioritetNavn")));
                    sak.setStatus(Status.valueOf(rs.getString("statusNavn")));
                    sak.setKategori(Kategori.valueOf(rs.getString("kategoriNavn")));

                    saker.add(sak);
                }
            }
        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return saker;
    }

    /**
     * Henter alle saker i databasen med en spesifikk status.
     * @param status Statusen som skal filtreres på
     * @return ArrayList med saker som har den spesifiserte statusen
     */
    public static ArrayList<Sak> hentSakerMedStatus(Status status) {
        ArrayList<Sak> saker = new ArrayList<>();
        String sql = """
        SELECT s.sakId, s.tittel, s.beskrivelse, s.tidsstempel, s.oppdatertTidspunkt,
               rapportor.navn AS rapportorNavn,
               mottaker.navn AS mottakerNavn,
               p.prioritetNavn, st.statusNavn, k.kategoriNavn
        FROM Sak s
        JOIN Brukere rapportor ON s.rapportørBrukerId = rapportor.brukerId
        LEFT JOIN Brukere mottaker ON s.mottakerBrukerId = mottaker.brukerId
        JOIN Prioritet p ON s.prioritetId = p.prioritetId
        JOIN Status st ON s.statusId = st.statusId
        JOIN Kategori k ON s.kategoriId = k.kategoriId
        WHERE st.statusNavn = ?
    """;

        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, status.name()); // Setter status basert på enum-verdi

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Sak sak = new Sak();

                    sak.setSakID(rs.getInt("sakId"));
                    sak.setTittel(rs.getString("tittel"));
                    sak.setBeskrivelse(rs.getString("beskrivelse"));
                    sak.setTidsstempel(rs.getTimestamp("tidsstempel").toLocalDateTime());
                    sak.setOppdatertTidspunkt(rs.getTimestamp("oppdatertTidspunkt").toLocalDateTime());

                    sak.setRapportør(rs.getString("rapportorNavn"));

                    String mottakerNavn = rs.getString("mottakerNavn");
                    if (mottakerNavn != null) {
                        sak.setMottaker(mottakerNavn);
                    }

                    sak.setPrioritet(Prioritet.valueOf(rs.getString("prioritetNavn")));
                    sak.setStatus(Status.valueOf(rs.getString("statusNavn")));
                    sak.setKategori(Kategori.valueOf(rs.getString("kategoriNavn")));

                    saker.add(sak);
                }
            }
        } catch (SQLException | IOException e) {
            System.err.println("Kunne ikke hente saker med status " + status + ": " + e.getMessage());
            e.printStackTrace();
        }

        return saker;
    }





    /**
     * Søker etter saker i databasen basert på ulike søkekriterier.
     * @param soking Soking-objekt som inneholder søkekriteriene
     * @return Liste med saker som matcher søkekriteriene
     */

    //Metode for søking
    public List<Sak> sokSaker(Soking soking) {
    List<Sak> resultater = new ArrayList<>();

    StringBuilder sql = new StringBuilder("SELECT * FROM sak WHERE 1=1");
    List<Object> parametere = new ArrayList<>();

    if (soking.getPrioritet() != null) {
        sql.append(" AND prioritet = ?");
        parametere.add(soking.getPrioritet().name());
    }

    if (soking.getStatus() != null) {
        sql.append(" AND status = ?");
        parametere.add(soking.getStatus());
    }

    if (soking.getKategori() != null) {
        sql.append(" AND kategori = ?");
        parametere.add(soking.getKategori());
    }

    if (soking.getTittel() != null && !soking.getTittel().isEmpty()) {
        sql.append(" AND tittel LIKE ?");
        parametere.add("%" + soking.getTittel() + "%");
    }

    if (soking.getBeskrivelse() != null && !soking.getBeskrivelse().isEmpty()) {
        sql.append(" AND beskrivelse LIKE ?");
        parametere.add("%" + soking.getBeskrivelse() + "%");
    }
    

    if (soking.getOpprettetAr() != null) {
        sql.append(" AND YEAR(opprettet_tidspunkt) = ?");
        parametere.add(soking.getOpprettetAr());
    }

    if (soking.getOppdatertAr() != null) {
        sql.append(" AND YEAR(oppdatert_tidspunkt) = ?");
        parametere.add(soking.getOppdatertAr());
    }
   
    if (soking.getReporterNavn() != null && !soking.getReporterNavn().isEmpty()) {
        sql.append(" AND rapportorBrukerid IN (SELECT brukerid FROM brukere WHERE navn LIKE ?)");
        parametere.add("%" + soking.getReporterNavn() + "%");
    }
    try (Connection conn = DatabaseUtil.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

        for (int i = 0; i < parametere.size(); i++) {
            stmt.setObject(i + 1, parametere.get(i));
        }
        ResultSet rs = stmt.executeQuery();
        while (rs.next()) {
            Sak sak = new Sak();
            sak.setSakID(rs.getInt("sakID"));
            sak.setTittel(rs.getString("tittel"));
            sak.setBeskrivelse(rs.getString("beskrivelse"));
            sak.setPrioritet(Prioritet.valueOf(rs.getString("prioritet")));
            sak.setKategori(Kategori.valueOf(rs.getString("kategori")));
            sak.setStatus(Status.valueOf(rs.getString("status")));
            sak.setRapportør(rs.getString("rapportør"));
            sak.setMottaker(rs.getString("mottaker"));
            sak.setTidsstempel(rs.getTimestamp("opprettet_tidspunkt").toLocalDateTime());
            sak.setOppdatertTidspunkt(rs.getTimestamp("oppdatert_tidspunkt").toLocalDateTime());
            
            resultater.add(sak);
        }

    } catch (SQLException | IOException e) {
        e.printStackTrace();
    }
    return resultater;
    }
}


