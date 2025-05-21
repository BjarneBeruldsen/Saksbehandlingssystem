package com.example.gruppe15eksamen.server.dao;

import com.example.gruppe15eksamen.common.Bruker;
import com.example.gruppe15eksamen.server.util.DatabaseUtil;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/*Inneholder metoder for å utføre operasjoner mot
* databasen for bruker. F.eks. hent brukere, lagbruker osv.  */
public class BrukerDAO {

    //DAO metode for å hente alle brukere fra databasen
    public static List<Bruker> hentAlleBrukere() throws SQLException, IOException {
        String sql = "SELECT brukerId, navn, rolleId FROM Brukere";
        List<Bruker> alleBrukere = new ArrayList<>();
        try (Connection conn = DatabaseUtil.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                int brukerId = rs.getInt("brukerId");
                String navn = rs.getString("navn");
                int rolleId = rs.getInt("rolleId");

                //alleBrukere.add(new Bruker(brukerId, navn, rolleId));
            }
        } catch (SQLException | IOException e) {
            System.err.println("Feil ved henting av brukere: " + e.getMessage());
        }
        return alleBrukere;
    }
    //DAO metode for å hente en spesifikk bruker basert på brukernavn


}

