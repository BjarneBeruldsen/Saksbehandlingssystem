// Author: Severin Waller Sørensen

package com.example.gruppe15eksamen.server.network;

import com.example.gruppe15eksamen.common.*;

import static com.example.gruppe15eksamen.server.dao.TabellerDAO.opprettAlleTabeller;

import com.example.gruppe15eksamen.server.util.DatabaseUtil;

import java.io.IOException;
import java.io.ObjectInput;
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
    private static void handleClient(Socket socket) {
        //I/O Stream til klienten
        try (ObjectInputStream inClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream utClient = new ObjectOutputStream(socket.getOutputStream());) {

            SocketRequest forespørsel = (SocketRequest)inClient.readObject();
            String handling = forespørsel.getHandling().toUpperCase();

            //switch setning som utfører operasjoner mot datbasen basert på socketrequest sin handling
            switch(handling){
                case "HENT BRUKERE":
                    //hent alle brukere fra DAO her (midlertidig harkodet test under)
                    ArrayList<Bruker> brukere = new ArrayList<>();
                    brukere.add(new Bruker(2, "HovedLeder", Rolle.LEDER));
                    brukere.add(new Bruker(3, "HovedTester", Rolle.TESTER));
                    brukere.add(new Bruker(4, "HovedUtvikler", Rolle.UTVIKLER));

                    //sender liste med alle brukere tilbake til klient
                    utClient.writeObject(brukere);
                    utClient.flush();
                    break;

                case "LAGSAK" :
                    //kaller på DAO her for å legge til sak
                    System.out.println("Sak som legges til: " + forespørsel.getSak().toString() );
                    //sender melding til klienten om det er godkjent
                    utClient.writeObject(new SocketRespons(true, "Saken er lagt til: " +
                            forespørsel.getSak().toString()));
                    break;

                case "ADD_MOTTAKER":
                    //kaller på DAO for å legge til sak basert på sakID og brukerID
                    //TEST FJERN
                    System.out.println("SakId:" + forespørsel.getSakID() + ";BrukerId:" +forespørsel.getBrukerID());
                    //sender melding til klienten om det er godkjent
                    utClient.writeObject(new SocketRespons(true, "Mottaker er lagt til"));
                    break;

                case "HENT_TILDELTE":
                    //harkoder saker for testdata FJERN
                    ArrayList<Sak> tildelteSaker = new ArrayList<>();

                    tildelteSaker.add(new Sak(
                            1,
                            "Feil i påloggingssystem",
                            "Brukere får feilmelding ved pålogging med BankID.",
                            Prioritet.HØY,
                            Kategori.BACKEND_FEIL,
                            Status.INNSENDT,
                            "Ola Nordmann",
                            "Kari Nordmann",
                            LocalDateTime.of(2025, 5, 1, 10, 30),
                            LocalDateTime.of(2025, 5, 2, 14, 15)
                    ));

                    System.out.println("BrukerId:" +forespørsel.getBrukerID());

                    utClient.writeObject(new SocketRespons("Hentet tildelte saker", tildelteSaker, true));

                default : utClient.writeObject(new SocketRespons(false,
                        "finner ikke handling" + handling));
                    break;
            }

        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private static void logg(String melding) {
        System.out.println("Server: " + melding);
    }
}
