/**
 * @author: Bjarne Beruldsen
 */
package com.example.gruppe15eksamen.server.network;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.gruppe15eksamen.common.Bruker;
import com.example.gruppe15eksamen.common.Kategori;
import com.example.gruppe15eksamen.common.Prioritet;
import com.example.gruppe15eksamen.common.Sak;
import com.example.gruppe15eksamen.common.SocketRequest;
import com.example.gruppe15eksamen.common.SocketRespons;
import com.example.gruppe15eksamen.common.Soking;
import com.example.gruppe15eksamen.common.Status;
import com.example.gruppe15eksamen.server.dao.BrukerDAO;
import com.example.gruppe15eksamen.server.dao.SakDAO;
import static com.example.gruppe15eksamen.server.dao.TabellerDAO.opprettAlleTabeller;
import com.example.gruppe15eksamen.server.util.DatabaseUtil;

/*Kjører for seg selv, lytter kontinuerlig etter klienter
* håndterer kommunikasjon fra/til klienter og kan håndtere
* flere klienter samtidig ved bruk av thread */
public class SakServer {
    private static final int PORT = 8000;
    private static final SakDAO sakDAO = new SakDAO();

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
    /**
     * Håndterer kommunikasjon og behandler data med en klient.
     * @param socket Klientens socket
     * @return SocketRespons med resultatet av forespørselen
     */
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

                case "HENT_RETTET":
                    // Henter alle saker med status "RETTET" via DAO
                    ArrayList<Sak> rettedeSaker = SakDAO.hentSakerMedStatus(Status.RETTET);

                    if (rettedeSaker != null && !rettedeSaker.isEmpty()) {
                        // Sender lista over rettede saker tilbake til klienten
                        utClient.writeObject(new SocketRespons("Hentet rettede saker", rettedeSaker, true));
                    } else {
                        // Sender tom respons hvis ingen rettede saker finnes
                        utClient.writeObject(new SocketRespons(false, "Ingen rettede saker funnet"));
                    }
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

                case "OPPDATER_STATUS":
                    // Henter sakID og valgt status fra forespørselen
                    int sakID = forespørsel.getSakID();
                    Status nyStatus = forespørsel.getStatus();
                    String kommentar = forespørsel.getKommentar();

                    // Kaller DAO for å oppdatere statusen
                    int oppdaterteRader = SakDAO.oppdaterStatusMedTesterTilbakemelding(sakID, nyStatus, kommentar);

                    if (oppdaterteRader > 0) {
                        utClient.writeObject(new SocketRespons(true, "Status oppdatert for sak med ID " + sakID));
                    } else {
                        utClient.writeObject(new SocketRespons(false, "Kunne ikke oppdatere status for sak med ID " + sakID));
                    }
                    utClient.flush();
                    break;
                case "OPPDATER_STATUS_LEDER":
                    // Henter sakID og valgt status fra forespørselen
                    int sakIDLeder = forespørsel.getSakID();
                    Status nyStatusLeder = forespørsel.getStatus();

                    // Kaller DAO for å oppdatere statusen
                    int oppdaterteRaderLeder = SakDAO.oppdaterStatusLeder(sakIDLeder, nyStatusLeder);

                    if (oppdaterteRaderLeder > 0) {
                        utClient.writeObject(new SocketRespons(true, "Status oppdatert for sak med ID " + sakIDLeder));
                    } else {
                        utClient.writeObject(new SocketRespons(false, "Kunne ikke oppdatere status for sak med ID " + sakIDLeder));
                    }
                    utClient.flush();
                    break;

                case "HENT_TILDELTE":
                    //harkoder saker for testdata FJERN
                    ArrayList<Sak> tildelteSaker = SakDAO.hentSakerTildeltUtvikler(forespørsel.getBrukerID());

                    utClient.writeObject(new SocketRespons("Hentet tildelte saker",
                            tildelteSaker, true));
                    break;

                case "SOK_SAKER":
                    System.out.println("Mottok søkeforespørsel");
                    Soking soking = (Soking) forespørsel.getData();
                    System.out.println("Søkeobjekt: " + soking);
                    List<Sak> sokteSaker = sakDAO.sokSaker(soking);
                    System.out.println("Antall saker funnet: " + sokteSaker.size());
                    utClient.writeObject(new SocketRespons("Søkeresultater", new ArrayList<>(sokteSaker), true));
                    utClient.flush();
                    break;

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
