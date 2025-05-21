package com.example.gruppe15eksamen.server.dao;

import com.example.gruppe15eksamen.common.Bruker;
import com.example.gruppe15eksamen.common.Rolle;
import com.example.gruppe15eksamen.server.util.DatabaseUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Inneholder metoder for å utføre operasjoner mot
 * databasen for bruker. F.eks. hent brukere, lagbruker osv.  */
public class BrukerDAO {

    //Metode for å hente alle brukere
    public static List<Bruker> hentAlleBrukere() throws SQLException, IOException {
        String sql = ""
                + "SELECT b.brukerId, b.navn AS brukernavn, r.rolleNavn AS rollenavn "
                + "FROM Brukere b "
                + "JOIN Rolle r ON b.rolleId = r.rolleId";
        List<Bruker> alleBrukere = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int id = rs.getInt("brukerId");
                String navn = rs.getString("brukernavn");
                String rollenavn = rs.getString("rollenavn");
                Rolle rolle = Rolle.valueOf(rollenavn);
                alleBrukere.add(new Bruker(id, navn, rolle));
            }
        } catch (SQLException | IOException e) {
            System.err.println("Feil ved henting av brukere: " + e.getMessage());
        }
        return alleBrukere;
    }
    //Metode for å hente en spesifikk bruker basert på brukernavn


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
                    throw new SQLException("Fant ikke bruker medd navn: " + navn);
                }
        }
    }
}