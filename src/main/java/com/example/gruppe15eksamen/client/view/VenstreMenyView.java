/**
 * @Author Severin Waller Sørensen
 */

package com.example.gruppe15eksamen.client.view;

import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;

public class VenstreMenyView {
    
    // Globale variabler
    VBox venstreMeny;
    VBox brukerInfoBoks;
    int loggetInnBrukerId;
    String loggetInnBrukernavn;

    // Globale knapper (FELLES/ALLE)
    Button btnHjem;
    Button btnRefresh;
    // Unike/rolle-spesifikke knapper (for TESTER)
    Button btnTesterOpprettSak, btnTesterInnsendteSaker;
    // Unike/rolle-spesifikke knapper (for UTVIKLER)
    Button btnUtviklerMineSaker;
    // Unike/rolle-spesifikke knapper (for LEDER)
    Button btnLederOpprettSak, btnLederSeAlleSaker;


    // Konstruktør
    public VenstreMenyView() {
        venstreMeny = new VBox();
        venstreMeny.setAlignment(Pos.TOP_CENTER);
        venstreMeny.setPadding(new Insets(10 ,10, 10, 10));
        venstreMeny.setSpacing(15);
        venstreMeny.setStyle("-fx-background-color:rgb(46, 133, 91)");
        btnHjem = new Button("Hovedside");
        btnHjem.getStyleClass().add("venstre-meny-knapp");
        btnRefresh = new Button("Oppdater"); 
        btnRefresh.getStyleClass().add("venstre-meny-knapp");
        leggTilBrukerInfoBoks();
        venstreMeny.getChildren().addAll(btnHjem, btnRefresh);
    }

    // Informasjon om InnloggetBruker
    public void leggTilBrukerInfoBoks() {
   
        // Fjerne bruukerInfoBoksen om den er der (for at det ikke skal komme dobbelt)
        if (brukerInfoBoks != null && venstreMeny.getChildren().contains(brukerInfoBoks)) {
            venstreMeny.getChildren().remove(brukerInfoBoks);
        }

        // Initiere BrukerInfoBoks (+ legge til/kalle på StyleClass)
        brukerInfoBoks = new VBox(2);
        brukerInfoBoks.getStyleClass().add("bruker-info-boks");

        // Opprette labels og legge til Style
        Label lblBrukerInfo = new Label("Bruker: ");
        lblBrukerInfo.getStyleClass().add("brukerinfo-tittel");
        Label lblId = new Label("_ID: " + loggetInnBrukerId);
        lblId.getStyleClass().add("brukerinfo-detaljer");
        Label lblBrukernavn = new Label("Brukernavn: " + loggetInnBrukernavn);
        lblBrukernavn.getStyleClass().add("brukerinfo-detaljer");

        brukerInfoBoks.getChildren().addAll(lblBrukerInfo, lblId, lblBrukernavn);
        venstreMeny.getChildren().add(0, brukerInfoBoks); // legg til øverst (index: 0)
    }
    
    // Metode for å legge til TESTER-knapper
    public Node leggTilTesterKnapper() {
        btnTesterOpprettSak = new Button("Opprett sak");
        btnTesterInnsendteSaker = new Button("Mine saker");

        // Styling for knappene
        btnTesterOpprettSak.getStyleClass().add("venstre-meny-knapp");
        btnTesterInnsendteSaker.getStyleClass().add("venstre-meny-knapp");

        // Legge til knappene i panelet
        venstreMeny.getChildren().addAll(btnTesterOpprettSak, btnTesterInnsendteSaker);
        return venstreMeny;
    }

    // Metode for å legge til UTVIKLER-knapper
    public Node leggTilUtviklerKnapper() {
        btnUtviklerMineSaker = new Button("Mine saker");

        // Styling for knappen(e)
        btnUtviklerMineSaker.getStyleClass().add("venstre-meny-knapp");

        // Legge til knappen(e) i panelet
        venstreMeny.getChildren().add(btnUtviklerMineSaker);
        return venstreMeny;
    }

    // Metode for å legge til LEDER-knapper
    public Node leggTilLederKnapper() {
        btnLederOpprettSak = new Button("Opprett sak");
        btnLederSeAlleSaker = new Button("Se alle saker");

        // Styling for knappene
        btnLederOpprettSak.getStyleClass().add("venstre-meny-knapp");
        btnLederSeAlleSaker.getStyleClass().add("venstre-meny-knapp");

        // legg til knappene i panelet
        venstreMeny.getChildren().addAll(btnLederOpprettSak, btnLederSeAlleSaker);
        return venstreMeny;
    }

    // Metode for å oppdatere brukerinfo:
    public void oppdaterBrukerInfo(int id, String brukernavn) {
        this.loggetInnBrukerId = id;
        this.loggetInnBrukernavn = brukernavn;
        leggTilBrukerInfoBoks();
    }

    // Metode for å fjerne/nullstille knapper
    // fjerner alle Rolle-knapper, men beholder/legger til
    // "Hjem" og "Oppdater" knappene igjen
    //  (samt kaller på leggTilBrukerInfo)
    public void nullstillKnapper() {
        venstreMeny.getChildren().clear();
        leggTilBrukerInfoBoks();
        venstreMeny.getChildren().addAll(btnHjem, btnRefresh);
    }

    // get-metode for venstre meny
    public VBox getVenstreMeny() { return venstreMeny; }

    // get-metoder for Buttons
    public Button getBtnHjem() { return btnHjem; }
    public Button getBtnRefresh() { return btnRefresh; }
    public Button getBtnTesterOpprettSak() { return btnTesterOpprettSak; }
    public Button getBtnTesterInnsendteSaker() { return btnTesterInnsendteSaker; }
    public Button getBtnUtviklerMineSaker() { return btnUtviklerMineSaker; }
    public Button getBtnLederOpprettSak() { return btnLederOpprettSak; }
    public Button getBtnLederSeAlleSaker() { return btnLederSeAlleSaker; }

    // set-metoder for info om innlogget bruker
    public void setLoggetInnBrukerId(int id) { this.loggetInnBrukerId = id; }
    public void setLoggetInnBrukernavn(String navn) { this.loggetInnBrukernavn = navn; }

}
