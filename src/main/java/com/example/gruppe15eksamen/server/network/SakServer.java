package com.example.gruppe15eksamen.server.network;

import com.example.gruppe15eksamen.common.Bruker;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/*Kjører for seg selv, lytter kontinuerlig etter klienter
* håndterer kommunikasjon fra/til klienter og kan håndtere
* flere klienter samtidig ved bruk av thread */
public class SakServer {
    private static final int PORT = 8000;

    public static void main(String[] args) {
        snakkMedKlienter();
    }

    private static void snakkMedKlienter() {
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
                Bruker bruker = (Bruker) innClient.readObject();
                logg("Bruker med rolle: " + bruker.getRolle() + " er koblet til");
                String ut = "Du er koblet til serveren";
                utClient.writeObject(ut);
                utClient.flush();


                new Thread(() -> handleClient(bruker)).start();

            }
        } catch (IOException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    //behandler brukerdata
    private static void handleClient(Bruker bruker) {

    }

    private static void logg(String melding) {
        System.out.println("Server: " + melding);
    }
}
