

/*Denne filen kobler sammen SakView med sak og rolle fra common
 * mappen. Denne filen fungerer på samme måte som main */

package com.example.gruppe15eksamen.client.controller;

import java.io.IOException;
import java.util.ArrayList;

import com.example.gruppe15eksamen.common.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.example.gruppe15eksamen.client.network.NetworkClient;
import com.example.gruppe15eksamen.client.view.SakView;
import com.example.gruppe15eksamen.server.dao.BrukerDAO;

public class SakController {
    
    private SakView sakViewVisning = new SakView();

    private Sak sak;
    private BrukerDAO brukerDAO = new BrukerDAO();
    private ArrayList<Bruker> alleBrukere;  //arraylist som kan legges til i rullgardinliste

    private BorderPane hovedPanel;
    private Stage hovedStage;

    //kobler til server
    private NetworkClient nettverkKlient = new NetworkClient();




    public SakController(Stage stage) {
        hentBrukere();
        this.hovedStage = stage;
        this.hovedPanel = sakViewVisning.getHovedPanel();
        //BARE EN TEST FOR Å SJEKKE AT alleBrukere har hentet brukere under
        skrivUtBrukere();
    }

    private void skrivUtBrukere() {
        if(!(alleBrukere == null)) {
            System.out.println("Alle brukere hentet fra datbasen");
            for (Bruker b : alleBrukere) {
                System.out.println(b.toString());
            }
        } else {
            System.out.println("Du må starte serveren før Main for å hente brukere");
        }
    }

    private void hentBrukere() {
        try {
            alleBrukere = nettverkKlient.hentBrukere();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Kunne ikke koble til server");
        }
    }

    // LeggTilLyttere 
    // getBtn.setOnAction

    private void lagTabeller() {
        BrukerDAO.lagBrukereTabell();
    }

    
    // BehandleKlikk
    // e.getSource 

    //Returnere scene(r) til main
 
 
    // getMetoder
    public Scene getStartScene() {
        return new Scene(hovedPanel);
    }

    // Returnere SakView
    public SakView getSakViewVisning() {
        return sakViewVisning;
    }

    //Metode for å opprette sak(Tester)
    //Kalles når tester trykker "opprett sak knapp"
    public void opprettSak() {
        //Harkoder data som egt. hentes fra textfelt her
        Bruker bruker = alleBrukere.get(0); //henter random fra brukerliste
        String tittel = "SakTittel";
        String beskrivelse = "Dette er en beskrivelse";
        Prioritet prioritet = Prioritet.HØY;
        Kategori kategori = Kategori.BACKEND_FEIL;
        String rapportør = bruker.getBrukernavn();

        Sak sak = new Sak(tittel, beskrivelse, prioritet, kategori, rapportør);

        SocketRespons respons = NetworkClient.sendSak(sak);
    }
}
