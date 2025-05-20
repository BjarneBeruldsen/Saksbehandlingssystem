

/*Denne filen kobler sammen SakView med sak og rolle fra common
 * mappen. Denne filen fungerer på samme måte som main */

package com.example.gruppe15eksamen.client.controller;

import java.io.IOException;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import com.example.gruppe15eksamen.client.network.NetworkClient;
import com.example.gruppe15eksamen.client.view.SakView;
import com.example.gruppe15eksamen.common.Bruker;
import com.example.gruppe15eksamen.common.Rolle;
import com.example.gruppe15eksamen.common.Sak;
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
        kobleTilServer();
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

    private void kobleTilServer() {
        try {
            alleBrukere = nettverkKlient.kobleTilOgHentBrukere(8000);
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

}
