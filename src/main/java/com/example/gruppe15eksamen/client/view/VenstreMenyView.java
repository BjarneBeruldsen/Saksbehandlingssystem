// Author: Severin Waller Sørensen

package com.example.gruppe15eksamen.client.view;

import java.util.ArrayList;
import javafx.geometry.Pos;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import static com.example.gruppe15eksamen.client.view.ViewKonstanter.VINDU_BREDDE;
import static com.example.gruppe15eksamen.client.view.ViewKonstanter.VINDU_HØYDE;

public class VenstreMenyView {
    
    // Globale variabler
    VBox venstreMeny;
    VBox brukerInfoBoks;
    int loggetInnBrukerId;
    String loggetInnBrukernavn;

    // Globale knapper (FELLES/ALLE)
    Button btnHjem;
    // Unike/rolle-spesifikke knapper (for TESTER)
    Button btnTesterOpprettSak, btnTesterInnsendteSaker;
    // Unike/rolle-spesifikke knapper (for UTVIKLER)
    Button btnUtviklerMineSaker;
    // Unike/rolle-spesifikke knapper (for LEDER)
    Button btnLederSeAlleSaker;


    // Konstruktør
    public VenstreMenyView() {
        venstreMeny = new VBox();
        venstreMeny.setPadding(new Insets(10 ,10, 10, 10));
        venstreMeny.setSpacing(15);
        venstreMeny.setStyle("-fx-background-color:rgb(46, 133, 91)");
        btnHjem = new Button("Hovedside");
        btnHjem.setStyle(
            "-fx-background-color:rgb(210, 210, 210);" +
            "-fx-text-fill: #050505;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weigh: bold" +
            "-fx-background-radius: 6px;" +
            "-fx-padding: 8;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0.1, 2, 0);"
        );
        leggTilBrukerInfoBoks();
        venstreMeny.getChildren().add(btnHjem);
    }

    // Informasjon om InnloggetBruker
    public void leggTilBrukerInfoBoks() {
        brukerInfoBoks = new VBox(2);
        brukerInfoBoks.setPadding(new Insets(6));
        brukerInfoBoks.setStyle(
            "-fx-background-color: white;" +
            "-fx-border-color: #cccccc;" +
            "-fx-border-radius: 4px;" +
            "-fx-background-radius: 4px;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.1), 5, 0, 2, 2);"
        );

        // Opprette labels og legge til Style
        Label lblBrukerInfo = new Label("Logget inn som: ");
        lblBrukerInfo.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill:rgb(0, 0, 0);");
        Label lblId = new Label("- _ID: " + loggetInnBrukerId); 
        lblId.setStyle("-fx-font-size: 12px; -fx-text-fill:rgb(0, 0, 0);");
        Label lblBrukernavn = new Label("- Brukernavn: " + loggetInnBrukernavn);
        lblBrukernavn.setStyle("-fx-font-size: 12px; -fx-text-fill:rgb(0, 0, 0);");

        brukerInfoBoks.getChildren().addAll(lblBrukerInfo, lblId, lblBrukernavn);
        venstreMeny.getChildren().add(0, brukerInfoBoks); // legg til øverst (index: 0)
    }
    
    // Metode for å legge til TESTER-knapper
    public Node leggTilTesterKnapper() {
        btnTesterOpprettSak = new Button("Opprett sak");
        btnTesterInnsendteSaker = new Button("Mine saker");

        // Styling for knappene
        btnTesterOpprettSak.setStyle(
            "-fx-background-color:rgb(210, 210, 210);" +
            "-fx-text-fill: #050505;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weigh: bold" +
            "-fx-background-radius: 6px;" +
            "-fx-padding: 8;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0.1, 2, 0);"
        );
        btnTesterInnsendteSaker.setStyle(
            "-fx-background-color:rgb(210, 210, 210);" +
            "-fx-text-fill: #050505;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weigh: bold" +
            "-fx-background-radius: 6px;" +
            "-fx-padding: 8;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0.1, 2, 0);"
        );

        // Legge til knappene i panelet
        venstreMeny.getChildren().addAll(btnTesterOpprettSak, btnTesterInnsendteSaker);
        return venstreMeny;
    }

    // Metode for å legge til UTVIKLER-knapper
    public Node leggTilUtviklerKnapper() {
        btnUtviklerMineSaker = new Button("Mine saker");
        btnUtviklerMineSaker.setStyle(
            "-fx-background-color:rgb(210, 210, 210);" +
            "-fx-text-fill: #050505;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weigh: bold" +
            "-fx-background-radius: 6px;" +
            "-fx-padding: 8;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0.1, 2, 0);"
        );

        venstreMeny.getChildren().add(btnUtviklerMineSaker);
        return venstreMeny;
    }

    // Metode for å legge til LEDER-knapper
    public Node leggTilLederKnapper() {
        btnLederSeAlleSaker = new Button("Se alle saker");
        btnLederSeAlleSaker.setStyle(
            "-fx-background-color:rgb(210, 210, 210);" +
            "-fx-text-fill: #050505;" +
            "-fx-font-size: 14px;" +
            "-fx-font-weigh: bold" +
            "-fx-background-radius: 6px;" +
            "-fx-padding: 8;" +
            "-fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 8, 0.1, 2, 0);"
        );

        venstreMeny.getChildren().add(btnLederSeAlleSaker);
        return venstreMeny;
    }

    // Metode for å fjerne/nullstille knapper
    // fjerner alle Rolle-knapper, men beholder/legger til
    // "Hjem" knappen igjen (og kaller på leggTilBrukerInfo)
    public void nullstillKnapper() {
        venstreMeny.getChildren().clear();
        leggTilBrukerInfoBoks();
        venstreMeny.getChildren().add(btnHjem);
    }

    // get-metode for venstre meny
    public VBox getVenstreMeny() { return venstreMeny; }

    // get-metoder for Buttons
    public Button getBtnHjem() { return btnHjem; }
    public Button getBtnTesterOpprettSak() { return btnTesterOpprettSak; }
    public Button getBtnTesterInnsendteSaker() { return btnTesterInnsendteSaker; }
    public Button getBtnUtviklerMineSaker() { return btnUtviklerMineSaker; }
    public Button getBtnLederSeAlleSaker() { return btnLederSeAlleSaker; }

    // set-metoder for info om innlogget bruker
    public void setLoggetInnBrukerId(int id) { this.loggetInnBrukerId = id; }
    public void setLoggetInnBrukernavn(String navn) { this.loggetInnBrukernavn = navn; }

}
