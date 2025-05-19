package com.example.gruppe15eksamen.client.network;

import com.example.gruppe15eksamen.common.Bruker;
import com.example.gruppe15eksamen.common.Rolle;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/*Denne filen inneholde kode for å koble til server
* og sende data til/fra serveren */
public class NetworkClient {
    private static Socket socket;
    private static ObjectOutputStream ut;
    private static ObjectInputStream inn;

    public void snakkMedServer(int port, Bruker bruker) throws IOException, ClassNotFoundException {
        //Opprretter et endepunkt for kommunikasjon med server
        socket = new Socket("localhost", port);

        //Oppretter I/O stream
        ut = new ObjectOutputStream(socket.getOutputStream());
        inn = new ObjectInputStream(socket.getInputStream());

        //sender bruker til server
        ut.writeObject(bruker);
        ut.flush();


        //mottar rolle fra server
        String mldFraServer = (String) inn.readObject();
        System.out.println(mldFraServer);
    }
}
