// Author: Severin Waller Sørensen

package com.example.gruppe15eksamen.server.network;

import com.example.gruppe15eksamen.common.*;

import static com.example.gruppe15eksamen.server.dao.TabellerDAO.opprettAlleTabeller;

import com.example.gruppe15eksamen.server.dao.BrukerDAO;
import com.example.gruppe15eksamen.server.dao.SakDAO;
import com.example.gruppe15eksamen.server.util.DatabaseUtil;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*Kjører for seg selv, lytter kontinuerlig etter klienter
* håndterer kommunikasjon fra/til klienter og kan håndtere
* flere klienter samtidig ved bruk av thread */
public class SakServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        try (Connection conn = DatabaseUtil.getConnection()) {
            System.out.println("Database opprettet og tilkoblet");
        } catch (SQLException | IOException e) {
            System.err.println("Feil ved opprettelse eller tilkobling til database");
            e.printStackTrace();
            return;
        }
        opprettAlleTabeller();
        System.out.println("Alle tabeller er nå opprettet");
        snakkMedKlienter();
    }

    private static void snakkMedKlienter() {
        ExecutorService pool = Executors.newCachedThreadPool();
        //lytter etter klienter
        try(ServerSocket serverSocket = new ServerSocket(PORT)) {
            logg("Server startet på port " + PORT);

            while(true) {
                logg("Lytter etter klienter");

                //aksepterer klienten
                Socket clientSocket = serverSocket.accept();
                logg("En Klient er koblet til");

                  pool.execute(() -> handleClient(clientSocket));

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //behandler brukerdata
    private static SocketRespons handleClient(Socket socket) {
        //I/O Stream til klienten
        try (ObjectOutputStream utClient = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inClient = new ObjectInputStream(socket.getInputStream());)
        {

            SocketRequest forespørsel = (SocketRequest)inClient.readObject();
            String handling = forespørsel.getHandling().toUpperCase();

            //switch setning som utfører operasjoner mot datbasen basert på socketrequest sin handling
            switch(handling){
                case "HENT BRUKERE":
                    ArrayList<Bruker> brukere = (ArrayList<Bruker>) BrukerDAO.hentAlleBrukere();

                    //sender liste med alle brukere tilbake til klient
                    utClient.writeObject(brukere);
                    utClient.flush();
                    break;

                case "LAGSAK" :
                    //oppretter sakobjekt fra foespørsel
                    Sak sak = forespørsel.getSak();
                    System.out.println("Sak som legges til: " + forespørsel.getSak().toString() );
                    int resultat = SakDAO.insertSak(sak);
                    //sender melding til klienten om det er godkjent
                    if(resultat > 0) {
                        utClient.writeObject(new SocketRespons(true, "Saken er lagt til: "));
                    } else {
                        utClient.writeObject(new SocketRespons(false, "Saken kunne ikke lagres i databasen"));
                    }
                    break;
                case "HENT_UTVIKLERE" :
                    //henter utviklere via DAO
                    ArrayList<String> utviklere = BrukerDAO.hentAlleUtviklere();
                    //sender utviklere til klienten
                    utClient.writeObject(utviklere);
                    utClient.flush();
                    break;

                case "HENT_SAKER" :
                    //henter alle saker via DAO
                    ArrayList<Sak> saker = SakDAO.hentAlleSaker();
                    //sender alle saker til klienten
                    utClient.writeObject(saker);
                    utClient.flush();
                    break;

                case "ADD_MOTTAKER":
                    //kaller på DAO for å legge mottaker til sak
                    int rader = SakDAO.tildelSak(forespørsel.getSakID(), forespørsel.getBrukernavn());

                    if(rader > 0) {
                        utClient.writeObject(new SocketRespons(true, "Mottaker er lagt til sak"));
                    } else {
                        utClient.writeObject(new SocketRespons(false, "Kunne ikke legge mottaker til sak"));
                    }
                    break;

                case "HENT_TILDELTE":
                    //harkoder saker for testdata FJERN
                    ArrayList<Sak> tildelteSaker = SakDAO.hentSakerTildeltUtvikler(forespørsel.getBrukerID());

                    utClient.writeObject(new SocketRespons("Hentet tildelte saker",
                            tildelteSaker, true));

                default : utClient.writeObject(new SocketRespons(false,
                        "finner ikke handling" + handling));
                    break;
            }

        } catch (IOException | ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return new SocketRespons(false, e.getMessage());
        }
        return null;
    }

    private static void logg(String melding) {
        System.out.println("Server: " + melding);
    }
}
