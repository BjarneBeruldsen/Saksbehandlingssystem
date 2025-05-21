package com.example.gruppe15eksamen.server.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.example.gruppe15eksamen.common.Bruker;
import com.example.gruppe15eksamen.common.Rolle;
import com.example.gruppe15eksamen.server.util.DatabaseUtil;

/*Inneholder metoder for å utføre operasjoner mot
 * databasen for bruker. F.eks. hent brukere, lagbruker osv.  */

//Metode for å hente alle brukere
public class BrukerDAO {
    public static List<Bruker> hentAlleBrukere() {
        String sql = """
            SELECT b.brukerId,
                   b.navn       AS brukernavn,
                   r.rolleNavn  AS rollenavn
              FROM Brukere b
              JOIN Rolle   r ON b.rolleId = r.rolleId
            """;
        List<Bruker> alleBrukere = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int    id        = rs.getInt("brukerId");
                String navn      = rs.getString("brukernavn");
                String rollenavn = rs.getString("rollenavn");
                Rolle  rolle     = Rolle.valueOf(rollenavn);
                alleBrukere.add(new Bruker(id, navn, rolle));
            }
        } catch (SQLException | IOException e) {
            System.err.println("Feil ved henting av brukere: " + e.getMessage());
            e.printStackTrace();
        }
        return alleBrukere;
    }
    //Metode som henter brukerID basert på brukernavn
    public static int hentBrukerIdFraNavn(String navn) throws SQLException, IOException {
        String query = "SELECT brukerId FROM Brukere WHERE navn = ?";
        try(Connection conn = DatabaseUtil.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(query)) {
                pstmt.setString(1, navn);
                ResultSet rs = pstmt.executeQuery();
                if(rs.next()) {
                    return rs.getInt("brukerId");
                } else {
                    throw new SQLException("Fant ikke bruker med navn: " + navn);
                }
        }
    }
    //Metode for å hente en spesifikk bruker basert på brukernavn
    public static Bruker hentBruker(String brukernavn) {
        String sql = """
            SELECT b.brukerId,
                   b.navn       AS brukernavn,
                   r.rolleNavn  AS rollenavn
              FROM Brukere b
              JOIN Rolle   r ON b.rolleId = r.rolleId
             WHERE b.navn = ?
            """;
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, brukernavn);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    int    id        = rs.getInt("brukerId");
                    String navn      = rs.getString("brukernavn");
                    String rollenavn = rs.getString("rollenavn");
                    Rolle  rolle     = Rolle.valueOf(rollenavn);
                    return new Bruker(id, navn, rolle);
                }
            }
        } catch (SQLException | IOException e) {
            System.err.println("Feil ved henting av bruker '" + brukernavn + "': " + e.getMessage());
            e.printStackTrace();
        }
        return null;
    }

    //metode som henter ut alle utviklere
    public static ArrayList<String> hentAlleUtviklere() {
        String query = """
            SELECT b.navn 
            FROM Brukere b
            JOIN rolle r ON b.rolleId = r.rolleId
            WHERE r.rolleNavn = 'UTVIKLER'
            """;
        ArrayList<String> alleUtviklere = new ArrayList<>();


        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                alleUtviklere.add(rs.getString("navn"));
            }
        } catch (SQLException | IOException e) {
            System.err.println("Feil ved henting av brukere: " + e.getMessage());
            e.printStackTrace();
        }
        return alleUtviklere;
    }
}