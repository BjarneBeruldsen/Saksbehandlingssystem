package com.example.gruppe15eksamen.server.network;

import com.example.gruppe15eksamen.common.Bruker;
import com.example.gruppe15eksamen.common.Rolle;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/*Kjører for seg selv, lytter kontinuerlig etter klienter
* håndterer kommunikasjon fra/til klienter og kan håndtere
* flere klienter samtidig ved bruk av thread */
public class SakServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
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

                //oppretter I/O stream til klienten
                ObjectOutputStream utClient = new ObjectOutputStream(clientSocket.getOutputStream());
                utClient.flush();
                ObjectInputStream innClient = new ObjectInputStream(clientSocket.getInputStream());

                //Motta bruker-objekt til klient
                String handling = (String)innClient.readObject();

                if(handling.equals("HENT BRUKERE")) {
                    //hent alle brukere fra DAO her (midlertidig harkodet test under)
                    ArrayList<Bruker> brukere = new ArrayList<>();
                    brukere.add(new Bruker(2, "HovedLeder", Rolle.LEDER));
                    brukere.add(new Bruker(3, "HovedTester", Rolle.TESTER));
                    brukere.add(new Bruker(4, "HovedUtvikler", Rolle.UTVIKLER));

                    //sender liste med alle brukere tilbake til klient
                    utClient.writeObject(brukere);
                    utClient.flush();
                }

                pool.execute(() -> handleClient(clientSocket));

            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    //behandler brukerdata
    private static void handleClient(Socket socket) {
        //I/O Stream til klienten
        try {
            ObjectInputStream inClient = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream utClient = new ObjectOutputStream(socket.getOutputStream());


        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void logg(String melding) {
        System.out.println("Server: " + melding);
    }
}
