package com.example.gruppe15eksamen.client.network;

import com.example.gruppe15eksamen.common.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

/*Denne filen inneholde kode for å koble til server
* og sende data til/fra serveren */
public class NetworkClient {
    final static int PORT = 8000;

    public ArrayList<Bruker> hentBrukere() throws IOException, ClassNotFoundException {
        //skriver forespørsel som skal sendes til server
        SocketRequest forespørsel = new SocketRequest("HENT BRUKERE");

        //Opprretter et endepunkt for kommunikasjon med server
        try (Socket socket = new Socket("localhost", PORT);
             ObjectOutputStream ut = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
        ) {
            //sender forespørsel til klienten om å hente brukere
            ut.writeObject(forespørsel);
            ut.flush();

            //sender bruker til/fra server
            Object object = inn.readObject();

            //returnere brukere til SakController
            return (ArrayList<Bruker>) object;
        }
    }

        //metode som sender sak til server og returnere respons fra serveren (godkjent/ikke godkjent)
        public static SocketRespons sendSak (Sak sak){
            //forespørsel om å lagre gitt sak via server til database
            SocketRequest forespørsel = new SocketRequest("LAGSAK", sak);

            //endepunkt for kommunikasjon med server
            try (Socket socket = new Socket("localhost", PORT);
                 ObjectOutputStream ut = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
            ) {
                //skriver forespørsel til server
                ut.writeObject(forespørsel);
                ut.flush();

                //motar respons fra server (false/true)
                SocketRespons respons = (SocketRespons) inn.readObject();

                //sjekker om responsen er godkjent
                if (respons.isGodkjent()) {
                    return respons;
                } else {
                    return new SocketRespons(false, "Handlingen er ikke godkjent");
                }
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return new SocketRespons(false, "Feil med kommunikasjon med server");
            }
        }

        //Metode som sender SakId og Utviklerens brukernavn til server
        public static SocketRespons sendSakMottaker(String brukernavn, int sakID) {
            //forespørsel som sender med brukerID sakID og operasjon som skal utføres av server
            SocketRequest forespørsel = new SocketRequest("ADD_MOTTAKER", brukernavn, sakID);

            //endepunkt for kommunikasjon med server
            try (Socket socket = new Socket("localhost", PORT);
                 ObjectOutputStream ut = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
            ) {
                //skriver forespørsel til server
                ut.writeObject(forespørsel);
                ut.flush();

                //mottar respons fra server (false/true)
                SocketRespons respons = (SocketRespons) inn.readObject();

                //sjekker om responsen er godkjent
                if (respons.isGodkjent()) {
                    return respons;
                } else {
                    return new SocketRespons(false, "Handlingen er ikke godkjent");
                }

            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return new SocketRespons(false, "Feil med tildeling av sak til utvikler");
            }
        }

        //sender bruker id til server og returnerer alle saker som er tildelt bruker
        public static SocketRespons hentTildelteSaker(int brukerId) {
            SocketRequest forespørsel = new SocketRequest(brukerId, "HENT_TILDELTE");

            //endepunkt for kommunikasjon med server
            try (Socket socket = new Socket("localhost", PORT);
                 ObjectOutputStream ut = new ObjectOutputStream(socket.getOutputStream());
                 ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
            ) {
                //skriver forespørsel til server
                ut.writeObject(forespørsel);
                ut.flush();

                //mottar respons fra server (false/true)
                SocketRespons respons = (SocketRespons) inn.readObject();
                System.out.println("respons: " + respons.getStatus());

                //sjekker om responsen er godkjent
                if (respons.isGodkjent()) {
                    return respons;
                } else {
                    return new SocketRespons(false, "Handlingen er ikke godkjent");
                }
            }
            catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
                return new SocketRespons(false, "Feil med henting av tildelte saker");
            }
        }

    public ArrayList<String> hentUtviklere() {
        SocketRequest forespørsel = new SocketRequest("HENT_UTVIKLERE");

        //Opprretter et endepunkt for kommunikasjon med server
        try (Socket socket = new Socket("localhost", PORT);
             ObjectOutputStream ut = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
        ) {
            //skriver forespørsel til server
            ut.writeObject(forespørsel);
            ut.flush();

            //returnere brukere til SakController
            return (ArrayList<String>) inn.readObject();
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ObservableList<Sak> hentSaker() {
        SocketRequest forespørsel = new SocketRequest("HENT_SAKER");
        //Opprretter et endepunkt for kommunikasjon med server
        try (Socket socket = new Socket("localhost", PORT);
             ObjectOutputStream ut = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
        ) {
            //skriver forespørsel til server
            ut.writeObject(forespørsel);
            ut.flush();

            ArrayList<Sak> saker= (ArrayList<Sak>) inn.readObject();

            //returnere brukere til SakController
            return FXCollections.observableArrayList(saker);
        }
        catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public SocketRespons oppdaterStatus(Status valgtStatus, int sakID, String kommentar) {
        // Lager en forespørsel med operasjon, status og sakID
        SocketRequest forespørsel = new SocketRequest("OPPDATER_STATUS", valgtStatus, sakID, kommentar);

        try (Socket socket = new Socket("localhost", PORT);
             ObjectOutputStream ut = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream inn = new ObjectInputStream(socket.getInputStream());
        ) {
            // Sender forespørselen til serveren
            ut.writeObject(forespørsel);
            ut.flush();

            // Leser respons fra serveren
            SocketRespons respons = (SocketRespons) inn.readObject();

            // Returnerer responsen hvis den er godkjent, ellers returnerer feilmelding
            if (respons.isGodkjent()) {
                return respons;
            } else {
                return new SocketRespons(false, "Kunne ikke oppdatere status for sak");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            return new SocketRespons(false, "Feil med kommunikasjon ved oppdatering av status");
        }
    }
}
