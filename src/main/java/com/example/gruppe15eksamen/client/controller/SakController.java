

/*Denne filen kobler sammen SakView med sak og rolle fra common
 * mappen. Denne filen fungerer på samme måte som main */

package com.example.gruppe15eksamen.client.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
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

    private BorderPane hovedPanel;
    private Stage hovedStage;

    //kobler til server
    private NetworkClient nettverkKlient = new NetworkClient();




    public SakController(Stage stage) {
        this.hovedStage = stage;
        this.hovedPanel = sakViewVisning.getHovedPanel();
        kobleTilServer();
    }

    private void kobleTilServer() {
        //harkoder test data.. MÅ FJERNES
        Bruker bruker = new Bruker(1, "Bjarne", Rolle.UTVIKLER);
        try {
            nettverkKlient.snakkMedServer(8000, bruker);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Kunne ikke koble til server");
        }
    }

    // LeggTilLyttere 
    public void leggTilLyttere() {
        // Legger til lytter for brukerListe (ComboBox)
        sakViewVisning.getBrukerListe().setOnAction(e -> behandleKlikk(e));
    }

    
     /* // lagTabeller
    private void lagTabeller() {
        BrukerDAO.lagBrukereTabell();
    } 
    */

    
    // BehandleKlikk
    public void behandleKlikk(ActionEvent e) {
        if (e.getSource() == sakViewVisning.getBrukerListe()) {
            Bruker valgt = sakViewVisning.getBrukerListe().getValue();
            if (valgt != null)
            sakViewVisning.setValgtBruker(valgt.getBrukernavn());
        }
    }


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
