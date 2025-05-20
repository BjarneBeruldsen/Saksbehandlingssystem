package com.example.gruppe15eksamen.client.network;

import com.example.gruppe15eksamen.common.*;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
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

        //Metode som sender SakId og Utviklerens brukerid til server
        public static SocketRespons sendSakMottaker(int brukerID, int sakID) {
            //forespørsel som sender med brukerID sakID og operasjon som skal utføres av server
            SocketRequest socketRequest = new SocketRequest(brukerID, sakID, "");

            return new SocketRespons(false, "IKKE LAGT TIL");
        }
}
