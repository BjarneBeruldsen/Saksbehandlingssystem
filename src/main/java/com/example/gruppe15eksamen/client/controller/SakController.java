// Author: Severin Waller Sørensen

/*Denne filen kobler sammen SakView med sak og rolle fra common
 * mappen. Denne filen fungerer på samme måte som main */

package com.example.gruppe15eksamen.client.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.util.ArrayList;

import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.example.gruppe15eksamen.client.network.NetworkClient;
import com.example.gruppe15eksamen.client.view.SakView;
import com.example.gruppe15eksamen.client.view.TesterView;
import com.example.gruppe15eksamen.client.view.UtviklerView;
import com.example.gruppe15eksamen.client.view.InnsendteSakerView;
import com.example.gruppe15eksamen.client.view.LederView;
import com.example.gruppe15eksamen.client.view.VenstreMenyView;
import com.example.gruppe15eksamen.client.view.SaksSkjema;
import com.example.gruppe15eksamen.common.Bruker;
import com.example.gruppe15eksamen.common.Rolle;
import com.example.gruppe15eksamen.common.Sak;
import com.example.gruppe15eksamen.server.dao.BrukerDAO;

public class SakController {
    
    private SakView sakViewVisning = new SakView();
    private TesterView testerViewVisning = new TesterView();
    private UtviklerView utviklerViewVisning = new UtviklerView();
    private LederView lederViewvVisning = new LederView();
    private VenstreMenyView venstreMenyVisning = new VenstreMenyView();
    private SaksSkjema saksSkjema = new SaksSkjema();
    private InnsendteSakerView innsendteSakerTabell = new InnsendteSakerView();

    private Bruker valgtBruker;
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

        if (alleBrukere != null) {
            sakViewVisning.setBrukerListe(alleBrukere);
        }
        //BARE EN TEST FOR Å SJEKKE AT alleBrukere har hentet brukere under
        skrivUtBrukere();
        leggTilLyttere();
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
    public void leggTilLyttere() {
        // Legger til lytter for brukerListe (ComboBox)
        sakViewVisning.getBrukerListe().setOnAction(e -> behandleKlikk(e));
        sakViewVisning.getBekreftBrukerBtn().setOnAction(e -> behandleKlikk(e));

        // Legge til lyttere for knapper i venstremenyen
        venstreMenyVisning.getBtnHjem().setOnAction(e -> behandleKlikk(e));
        // Spurte KI om tips for å håndtere enkelte av meny-knappene, siden de
        // ikke opprettes før en av leggTil(ROLLE)knapper() kjøres. 
        // KI foreslo å bare legge til en '!= null' sjekk.
        if (venstreMenyVisning.getBtnTesterOpprettSak() != null) {
            venstreMenyVisning.getBtnTesterOpprettSak().setOnAction(e -> behandleKlikk(e));
        }
        if (venstreMenyVisning.getBtnTesterInnsendteSaker() != null) {
            venstreMenyVisning.getBtnTesterInnsendteSaker().setOnAction(e -> behandleKlikk(e));
        }
        if (venstreMenyVisning.getBtnUtviklerMineSaker() != null) {
            venstreMenyVisning.getBtnUtviklerMineSaker().setOnAction(e -> behandleKlikk(e));
        }
        if (venstreMenyVisning.getBtnLederSeAlleSaker() != null ) {
            venstreMenyVisning.getBtnLederSeAlleSaker().setOnAction(e -> behandleKlikk(e));
        }
    }

    
     /* // lagTabeller
    private void lagTabeller() {
        BrukerDAO.lagBrukereTabell();
    } 
    */

    // variabel som tar vare på rollen til valgt bruker.
    // Brukes til å endre/oppdatere view
    String rolleView = "";
    
    // BehandleKlikk
    public void behandleKlikk(ActionEvent e) {

        // Behandle valg av bruker
        if (e.getSource() == sakViewVisning.getBrukerListe()) {
            valgtBruker = sakViewVisning.getBrukerListe().getValue();
            Rolle rolle = valgtBruker.getRolle();

            // Sette rolleView-variabel til valgt bruker sin rolle
            if (rolle.equals(Rolle.TESTER)) {
                rolleView = "TESTER";
            } else if (rolle.equals(Rolle.UTVIKLER)) {
                rolleView = "UTVIKLER";
            } else if (rolle.equals(Rolle.LEDER)) {
                rolleView = "LEDER";
            }
        }

        // Behandle / oppdatere view basert på brukerens rolle
        if (e.getSource() == sakViewVisning.getBekreftBrukerBtn()) {
            if (rolleView.equals("TESTER")) {
                sakViewVisning.visPanel(testerViewVisning.visTesterPanel());
                venstreMenyVisning.leggTilTesterKnapper();
            } else if (rolleView.equals("UTVIKLER")) {
                sakViewVisning.visPanel(utviklerViewVisning.visUtviklerPanel());
                venstreMenyVisning.leggTilUtviklerKnapper();
            } else if (rolleView.equals("LEDER")) {
                sakViewVisning.visPanel(lederViewvVisning.visLederPanel());
                venstreMenyVisning.leggTilLederKnapper();
            }

            sakViewVisning.visVenstreMeny(venstreMenyVisning.getVenstreMeny());

            leggTilLyttere();
        }

        // Behandleklikk for venstremenyen
        if (e.getSource() == venstreMenyVisning.getBtnHjem()) {
            sakViewVisning.visPanel(sakViewVisning.visLoggInnPanel());
            sakViewVisning.skjulVenstreMeny();
            venstreMenyVisning.nullstillKnapper();
            rolleView = "";
        }
        if (e.getSource() == venstreMenyVisning.getBtnTesterOpprettSak()) {
            saksSkjema.getRapportørFelt().setPromptText(valgtBruker.getBrukernavn());
            sakViewVisning.visPanel(saksSkjema.getSaksSkjema());
        }
        if (e.getSource() == venstreMenyVisning.getBtnTesterInnsendteSaker()) {
            sakViewVisning.visPanel(innsendteSakerTabell.getInnsendteSaker());
        }
        if (e.getSource() == venstreMenyVisning.getBtnUtviklerMineSaker()) {

        }
        if (e.getSource() == venstreMenyVisning.getBtnLederSeAlleSaker()) {

        }

    }
 
    // get-metode for å hente hovedPanel
    public Scene getStartScene() {
        return new Scene(hovedPanel);
    }

    // Returnere SakView
    public SakView getSakViewVisning() {
        return sakViewVisning;
    }

}
