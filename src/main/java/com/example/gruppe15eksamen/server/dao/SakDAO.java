package com.example.gruppe15eksamen.server.dao;
/* inneholder metode for operasjoner mot databasen som
* gjelder sak-håndtering*/

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import com.example.gruppe15eksamen.common.Kategori;
import com.example.gruppe15eksamen.common.Prioritet;
import com.example.gruppe15eksamen.common.Sak;
import com.example.gruppe15eksamen.common.Soking;
import com.example.gruppe15eksamen.common.Status;
import com.example.gruppe15eksamen.server.util.DatabaseUtil;


//Opprette metoder for saker og diverse
public class SakDAO {

    //metode for å opprette sak

    //metode som henter alle saker basert på rolle og bruker-
    //og legger til i en liste

    //metode som tildeler sak til utvikler
    public static int tildelSak(int sakId, String brukernavn) {
        String sql = """
            UPDATE sak
            SET mottaker = ?
            WHERE sakID = ?
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
    //metode for å opprette sak
//    public static void insertStudent(Sak sak) {
//        String query = """
//                INSERT INTO sak (tittel, beskrivelse, rapportorBrukerId, prioritetId, statusId,
//                kategoriId, tidsstempel, oppdatertTidspunkt)
//                VALUES(?, ?, ?, ?, ?, ?, ?, ?)
//                """;
//        try(Connection conn = DatabaseUtil.getConnection()) {
//            PreparedStatement pstmt = conn.prepareStatement(query);
//            pstmt.setString(1, sak.getTittel());
//            pstmt.setString(2, sak.getBeskrivelse());
//        } catch (SQLException | IOException e) {
//            e.printStackTrace();
//        }
//
//    }
    //metode for å opprette sak
    public static int insertSak(Sak sak) throws SQLException, IOException{
        String query = """
                INSERT INTO sak (tittel, beskrivelse, rapportørBrukerId, prioritetId, statusId,
                kategoriId, tidsstempel, oppdatertTidspunkt)
                VALUES(?, ?, ?, ?, ?, ?, ?, ?)
                """;
        try(Connection conn = DatabaseUtil.getConnection()) {
            PreparedStatement pstmt = conn.prepareStatement(query);

            //henter rapportørens id basert på brukernavn

            int rapportørId = BrukerDAO.hentBrukerIdFraNavn(sak.getRapportør());

            pstmt.setString(1, sak.getTittel());
            pstmt.setString(2, sak.getBeskrivelse());
            pstmt.setInt(3, rapportørId);
            pstmt.setInt(4, sak.getPrioritet().getId());
            pstmt.setInt(5, sak.getStatus().getId());
            pstmt.setInt(6, sak.getKategori().getId());
            pstmt.setTimestamp(7, Timestamp.valueOf(sak.getTidsstempel()));
            pstmt.setTimestamp(8, Timestamp.valueOf(sak.getOppdatertTidspunkt()));

            //antall rader som er oppdatert
            return pstmt.executeUpdate();


        } catch (SQLException | IOException e) {
           e.printStackTrace();
           return 0;
        }

    }
    //metode som henter sak basert på id
    public List<Sak> hentSaker(int sakID){
        List<Sak> saker = new ArrayList<>();

        String sql = "SELECT* FROM sak WHERE sakID =?";

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
                sak.setTidsstempel(rs.getTimestamp("opprettet_tidspunkt").toLocalDateTime());
                sak.setOppdatertTidspunkt(rs.getTimestamp("oppdatert_tidspunkt").toLocalDateTime());

                saker.add(sak);
            }

        } catch (SQLException | IOException e) {
            e.printStackTrace();
        }
        return saker;

    }
    //Metode for slett sak
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
        sql.append(" AND rapportør LIKE ?");
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


