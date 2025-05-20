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
    private static Socket socket;
    private static ObjectOutputStream ut;
    private static ObjectInputStream inn;
    final static int PORT = 8000;

    public ArrayList<Bruker> hentBrukere() throws IOException, ClassNotFoundException {
        //Opprretter et endepunkt for kommunikasjon med server
        opprettForbindelse();

        //skriver forespørsel til server
        SocketRequest forespørsel = new SocketRequest("HENT BRUKERE");

        //sender bruker til/fra server
        Object object = sendOgMotta(forespørsel);

        //returnere brukere til SakController
        return (ArrayList<Bruker>)object;
    }

    //metode som sender sak til server og returnere respons fra serveren (godkjent/ikke godkjent)
    public static SocketRespons sendSak(Sak sak) {
        try {
            //endepunkt for kommunikasjon med server
            opprettForbindelse();

            //skriver forespørsel til server
            SocketRequest forespørsel = new SocketRequest("CREATE", sak);

            //motar respons fra server (false/true)
            SocketRespons respons = (SocketRespons)sendOgMotta(forespørsel);

            //mottar respons fra server (false/true)
            if(respons.isGodkjent()) {
                return respons;
            } else {
                return new SocketRespons(false, "Handlingen er ikke godkjent");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return new SocketRespons(false, ""); //FJERN ETTERPÅ
    }

    //hjelpe metode som oppretter forbindelse
    private static void opprettForbindelse() throws IOException{
        socket = new Socket("localhost", PORT);
        ut = new ObjectOutputStream(socket.getOutputStream());
        inn = new ObjectInputStream(socket.getInputStream());
    }

    //hjelpemetode som sender og mottar svar fra/til db
    private static Object sendOgMotta(SocketRequest forespørsel) throws IOException, ClassNotFoundException{
        ut.writeObject(forespørsel);
        ut.flush();
        return  inn.readObject();
    }
}
