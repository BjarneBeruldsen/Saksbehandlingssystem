// Author: Bjarne Beruldsen & Severin Waller Sørensen

/*Denne filen kobler sammen SakView med sak og rolle fra common
 * mappen. Denne filen fungerer på samme måte som main */

package com.example.gruppe15eksamen.client.controller;

import java.io.IOException;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import java.util.ArrayList;

import com.example.gruppe15eksamen.common.*;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
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
import com.example.gruppe15eksamen.server.dao.BrukerDAO;
import jdk.net.Sockets;

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
    private ArrayList<String> alleUtviklere; //arrayList for brukernavn til rullgardinliste i Leder
    private ObservableList<Sak> alleSaker; //arrayList som tar bare på alle saker

    private ArrayList<Sak> tildelteSaker; //skal inneholde alle saker som er tildelt enn utvikler

    private BorderPane hovedPanel;
    private Stage hovedStage;

    //kobler til server
    private NetworkClient nettverkKlient = new NetworkClient();




    public SakController(Stage stage) {
        hentBrukere();
        hentSaker(); 
        //Kaller opprettsak etter brukere er hentet (Dette er test og den skal egt kalles når-
        //tester på send inn knapp)
        if(alleBrukere != null && !alleBrukere.isEmpty() ) {
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
        leggTilLyttere();
    }

    //metode som henter alle saker
    private void hentSaker() {
        alleSaker = nettverkKlient.hentSaker();
    }

    //metode som henter alle brukere
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
        saksSkjema.getBtnOpprett().setOnAction(e -> behandleKlikk(e));

        // Legge til lyttere for knapper i venstremenyen
        venstreMenyVisning.getBtnHjem().setOnAction(e -> behandleKlikk(e));
        if (venstreMenyVisning.getBtnTesterOpprettSak() != null) {
            venstreMenyVisning.getBtnTesterOpprettSak().setOnAction(e -> behandleKlikk(e));
        }
        if (venstreMenyVisning.getBtnTesterInnsendteSaker() != null) {
            venstreMenyVisning.getBtnTesterInnsendteSaker().setOnAction(e -> behandleKlikk(e));
        }
        if (venstreMenyVisning.getBtnUtviklerMineSaker() != null) {
            venstreMenyVisning.getBtnUtviklerMineSaker().setOnAction(e -> behandleKlikk(e));
        }
        if (venstreMenyVisning.getBtnLederSeAlleSaker() != null) {
            venstreMenyVisning.getBtnLederSeAlleSaker().setOnAction(e -> behandleKlikk(e));
        }
        if(alleSakerLeder.getBtLeggTilMottaker() != null) {
            alleSakerLeder.getBtLeggTilMottaker().setOnAction(e -> behandleKlikk(e));
        }
    }



    // variabel som tar vare på rollen til valgt bruker.
    // Brukes til å endre/oppdatere view
    String rolleView = "";

    // BehandleKlikk
    public void behandleKlikk(ActionEvent e) {

        // Behandle valg av bruker
        if (e.getSource() == sakViewVisning.getBrukerListe()) {
            valgtBruker = sakViewVisning.getBrukerListe().getValue();
            Rolle rolle = valgtBruker.getRolle();
            venstreMenyVisning.setLoggetInnBrukerId(valgtBruker.getBrukerID());
            venstreMenyVisning.setLoggetInnBrukernavn(valgtBruker.getBrukernavn());

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
                sakViewVisning.setLblOverskrift("Tester");
                venstreMenyVisning.leggTilTesterKnapper();
            } else if (rolleView.equals("UTVIKLER")) {
                sakViewVisning.visPanel(utviklerViewVisning.visUtviklerPanel());
                sakViewVisning.setLblOverskrift("Utvikler");
                venstreMenyVisning.leggTilUtviklerKnapper();
            } else if (rolleView.equals("LEDER")) {
                sakViewVisning.visPanel(lederViewvVisning.visLederPanel());
                sakViewVisning.setLblOverskrift("Leder");
                venstreMenyVisning.leggTilLederKnapper();
            }

            // Sikre at bruker må ha valgt rolle før en trykker bekreft
            // Med andre ord: 'Bekreft' gjør ikke noe om en ikke har valgt bruker
            if (valgtBruker != null) {
                // NB! må kalle på oppdaterBrukerInfo, hvis ikke blir første innlogging alltid lik 0 / null.
                venstreMenyVisning.oppdaterBrukerInfo(valgtBruker.getBrukerID(), valgtBruker.getBrukernavn());
                sakViewVisning.visVenstreMeny(venstreMenyVisning.getVenstreMeny());
                leggTilLyttere();
            }
        }

        // Behandleklikk for venstremenyen
        if (e.getSource() == venstreMenyVisning.getBtnHjem()) {
            sakViewVisning.visPanel(sakViewVisning.visLoggInnPanel());
            sakViewVisning.skjulVenstreMeny();
            venstreMenyVisning.nullstillKnapper();
            valgtBruker = null; // Nullstill valgtBruker
            rolleView = "";     // Nullstill rolleView
        }
        if (e.getSource() == venstreMenyVisning.getBtnTesterOpprettSak()) {
            saksSkjema.setRapportør(valgtBruker.getBrukernavn());
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
            //Kall på hentUtviklere her
            hentUtviklere();
            //setter nedtrekksliste til å ha brukernavn til utviklere
            alleSakerLeder.getCbUtviklere().getItems().clear();
            alleSakerLeder.getCbUtviklere().getItems().addAll(alleUtviklere);
            alleSakerLeder.getCbUtviklere().setPromptText("Velg");
            alleSakerLeder.getSaksTabell().setItems(alleSaker);
            sakViewVisning.visPanel(alleSakerLeder.getAlleSaker());
        }
        if(e.getSource() == saksSkjema.getBtnOpprett()) {
            opprettSak();
        }

        if(e.getSource() == alleSakerLeder.getBtLeggTilMottaker()) {
            leggTilMottaker();
        }

    }

    private void leggTilMottaker() {
        //henter sakId og brukernavn fra GUI-komponenter
        Sak valgtSak = alleSakerLeder.getSaksTabell().getSelectionModel().getSelectedItem();
        String valgtBrukernavn = alleSakerLeder.getCbUtviklere().getValue();

        if(valgtSak != null && valgtBrukernavn != null) {
            int sakID = valgtSak.getSakID();

            //sender til server
            SocketRespons respons = nettverkKlient.sendSakMottaker(valgtBrukernavn, sakID);

            if(respons.isGodkjent()) {
                sakViewVisning.setGodkjenning(respons.getStatus());
            }
            else {
                sakViewVisning.setFeilmelding(respons.getStatus());
            }

        } else {
            sakViewVisning.setFeilmelding("Du må velge sak og mottaker");
        }
    }

    //metode som henter alle utviklere
    private void hentUtviklere() {
        alleUtviklere = nettverkKlient.hentUtviklere();
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
        //Henter data fra sakskjema-form og legger til objekt
        String tittel = saksSkjema.getTittel();
        String beskrivelse = saksSkjema.getBeskrivelse();
        Prioritet prioritet = saksSkjema.getPrioritet();
        Kategori kategori = saksSkjema.getKategori();
        String rapportør = saksSkjema.getRapportør();

        Sak sak = new Sak(tittel, beskrivelse, prioritet, kategori, rapportør);

        SocketRespons respons = NetworkClient.sendSak(sak);

        if(respons.isGodkjent()) {
            sakViewVisning.setGodkjenning("Sak er lagt til i database");
        }
        else {
            sakViewVisning.setFeilmelding("Innsetting av sak feilet: " + respons.getStatus());
        }
    }



    public ArrayList<Sak> hentTilDelteSaker() {
        //harkoder data som må egt hentes fra tekstfelt FJERN
        int brukerID = 1;

        SocketRespons respons = nettverkKlient.hentTildelteSaker(brukerID);

        return respons.getSaker();
    }
}
