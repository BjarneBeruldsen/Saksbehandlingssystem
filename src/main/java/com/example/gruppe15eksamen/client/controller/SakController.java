// Author: Bjarne Beruldsen & Severin Waller Sørensen

/*Denne filen kobler sammen SakView med sak og rolle fra common
 * mappen. Denne filen fungerer på samme måte som main */

package com.example.gruppe15eksamen.client.controller;

import java.io.IOException;
import javafx.event.ActionEvent;
import java.util.ArrayList;

import com.example.gruppe15eksamen.common.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import com.example.gruppe15eksamen.client.network.NetworkClient;
import com.example.gruppe15eksamen.client.view.SakView;
import com.example.gruppe15eksamen.client.view.TesterView;
import com.example.gruppe15eksamen.client.view.UtviklerSakerView;
import com.example.gruppe15eksamen.client.view.UtviklerView;
import com.example.gruppe15eksamen.client.view.InnsendteSakerView;
import com.example.gruppe15eksamen.client.view.LederSakerView;
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
    private UtviklerSakerView tilordnedeSaker = new UtviklerSakerView();
    private LederSakerView alleSakerLeder = new LederSakerView();

    private Bruker valgtBruker;
    private Sak sak;
    private BrukerDAO brukerDAO = new BrukerDAO();
    private ArrayList<Bruker> alleBrukere;  //arraylist som kan legges til i rullgardinliste

    private ArrayList<Sak> tildelteSaker; //skal inneholde alle saker som er tildelt enn utvikler

    private BorderPane hovedPanel;
    private Stage hovedStage;

    //kobler til server
    private NetworkClient nettverkKlient = new NetworkClient();




    public SakController(Stage stage) {
        hentBrukere();
        //Kaller opprettsak etter brukere er hentet (Dette er test og den skal egt kalles når-
        //tester på send inn knapp)
        if(alleBrukere != null && !alleBrukere.isEmpty() ) {
           opprettSak();
           tildelSak();
           tildelteSaker = hentTilDelteSaker();
           //skriver ut tildelte saker for å teste FJERN
            for(Sak sak: tildelteSaker) {
                System.out.println(sak.toString());
            }
        }

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

    private void hentBrukere() {
        try {
            alleBrukere = nettverkKlient.hentBrukere();
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
            sakViewVisning.visPanel(tilordnedeSaker.getTilordnedeSaker());
        }
        if (e.getSource() == venstreMenyVisning.getBtnLederSeAlleSaker()) {
            sakViewVisning.visPanel(alleSakerLeder.getAlleSaker());
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

    //Metode for å opprette sak(Tester) og sende til db
    //Kalles når tester trykker "opprett sak knapp"
    public void opprettSak() {
        //Harkoder data som egt. hentes fra textfelt her
        Bruker bruker = alleBrukere.get(0); //henter random fra brukerliste
        System.out.println("Bruker: " + bruker.toString() + "er hentet");
        String tittel = "SakTittel";
        String beskrivelse = "Dette er en beskrivelse";
        Prioritet prioritet = Prioritet.HØY;
        Kategori kategori = Kategori.BACKEND_FEIL;
        String rapportør = bruker.getBrukernavn();

        Sak sak = new Sak(tittel, beskrivelse, prioritet, kategori, rapportør);

        SocketRespons respons = NetworkClient.sendSak(sak);
        System.out.println("respons hentet: " + respons);

        if(respons.isGodkjent()) {
            System.out.println("Sak er lagt til");
        }
        else {
            System.out.println("Innsetting av sak feilet" + respons.getStatus());
        }
    }

    //metode der ledere kan legge til utvikler som mottaker
    public void tildelSak() {
        //Harkoder som egt hentes fra tekstfelt her (sakID og BrukerID) FJERN
        int brukerID = 1;
        int sakID = 1;

        SocketRespons respons = nettverkKlient.sendSakMottaker(brukerID, sakID);
    }

    public ArrayList<Sak> hentTilDelteSaker() {
        //harkoder data som må egt hentes fra tekstfelt FJERN
        int brukerID = 1;

        SocketRespons respons = nettverkKlient.hentTildelteSaker(brukerID);

        return respons.getSaker();
    }
}
