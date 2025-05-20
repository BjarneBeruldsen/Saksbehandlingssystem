package com.example.gruppe15eksamen.server.dao;

import com.example.gruppe15eksamen.server.util.DatabaseUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

/*Inneholder metoder for å utføre operasjoner mot
* databasen for bruker. F.eks. hent brukere, lagbruker osv.  */
public class BrukerDAO {
    //metode for å lage bruker tabell
    public static void lagBrukereTabell() {
        String sql = "create table if not exists Brukere(id INT AUTO_INCREMENT PRIMARY KEY, navn VARCHAR(100), " +
                "rolle VARCHAR(100))";
        try(Connection conn = DatabaseUtil.getConnection()) {
            Statement stmt = conn.createStatement();
            stmt.executeUpdate(sql);
            System.out.println("Tabellen bruker er opprettet");
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    //DAO for å hente alle brukere fra databasen


}

