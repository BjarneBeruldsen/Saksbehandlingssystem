
/*Inneholder UI/GUI i javax for visning av brukergrensesnitt
 * for de ulike brukerne.. En metode får egenspesifiserte GUI for
 * Ledere, Testere og Utviklere? Evt. egen view */

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
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;

import static com.example.gruppe15eksamen.client.view.ViewKonstanter.VINDU_BREDDE;
import static com.example.gruppe15eksamen.client.view.ViewKonstanter.VINDU_HØYDE;
import com.example.gruppe15eksamen.common.Bruker;

public class SakView {
    // Globale variabler
    BorderPane hovedPanel = new BorderPane();
    GridPane loggInnPanel;
    BorderPane hjemPanel;
    TextField tittel;
    ComboBox<Bruker> brukerListe = new ComboBox<>();
    Label lblOverskrift = new Label("");
    Label lblStatus = new Label("");
    Button bekreftBrukerBtn = new Button("Bekreft");

    // Konstruktør
    public SakView() {
        // Legge til top/bottom og angi vindustørrelse
        hovedPanel.setTop(lagTopPanel());
        hovedPanel.setBottom(lagBunnPanel());
        hovedPanel.setPrefHeight(VINDU_HØYDE);
        hovedPanel.setPrefWidth(VINDU_BREDDE);

        // Default/start (CENTER = Innloggingspanel)
        visPanel(visLoggInnPanel());
    }



    // Lage HjemPanel
    private Node lagHjemPanel() {
        hjemPanel = new BorderPane();
        hjemPanel.setCenter(lagLoggInnPanel());
        hjemPanel.setStyle("-fx-background-color:rgb(207, 18, 18)");
        return hjemPanel;
    }

    // Lage LoggInnPanel
    private Node lagLoggInnPanel() {
        loggInnPanel = new GridPane();
        loggInnPanel.setStyle("-fx-background-color:rgb(18, 207, 43)");
        loggInnPanel.add(new Label("Velg bruker: "), 1,1);
        loggInnPanel.add(brukerListe, 1, 2);
        loggInnPanel.add(bekreftBrukerBtn, 1, 3);
        loggInnPanel.setPrefHeight(VINDU_HØYDE/2);
        loggInnPanel.setPrefWidth(VINDU_BREDDE/2);
        loggInnPanel.setAlignment(Pos.CENTER);

        return loggInnPanel;
    }



    // Lage TopPanel
    private Node lagTopPanel() {
        // Opprette panel og legge til styling
        HBox topPanel = new HBox();
        topPanel.setStyle("-fx-background-color: #d3d3d3; -fx-border-width: 2px; -fx-border-color: black");
        topPanel.setSpacing(10);
        topPanel.setPadding(new Insets(15, 15, 15, 15));
        topPanel.setAlignment(Pos.CENTER);

        // Endrer størrelse på label og legger til status (ikke gjort/ferdig enda)
        Font fonten = Font.font("COPPERPLATE GOTHIC BOLC, 24");
        lblOverskrift.setFont(fonten);
        topPanel.getChildren().add(lblOverskrift);

        return topPanel;
    }

    // Lage BunnPanel
    private Node lagBunnPanel() {
        // Oppretter panel og legger til styling
        HBox bunnPanel = new HBox();
        bunnPanel.setStyle("-fx-background-color: #d3d3d3; -fx-border-widht: 2px; -fx-border-color: black");
        bunnPanel.setSpacing(10);
        bunnPanel.setPadding(new Insets(15, 15, 15, 15));
        bunnPanel.setAlignment(Pos.CENTER);

        // Endrer størrelse på label og legger til status (ikke gjort/ferdig enda)
        Font fonten = Font.font("COPPERPLATE GOTHIC BOLC, 24");
        lblStatus.setFont(fonten);
        bunnPanel.getChildren().add(lblStatus);

        return bunnPanel;
    }

    // Buttons


    // Diverse metoder

    // metode som bytter CENTER-panel
    public void visPanel(Node panel) {
        hovedPanel.setCenter(panel);
    }

    // metode for å vise LoggInn-panel (default/start)
    public Node visLoggInnPanel() {
        return lagHjemPanel();
    }





    // getMetoder

    // Returner hovedPanel
    public BorderPane getHovedPanel() { return hovedPanel; }

    // Returner brukerListe
    public ComboBox<Bruker> getBrukerListe() { return brukerListe; }

    // Returnerer bekreftBrukerBtn ("Bekreft"/valg av bruker) 
    public Button getBekreftBrukerBtn() { return bekreftBrukerBtn; }

    // setMetoder


    // setMetode for brukerListe (ComboBox)
    public void setBrukerListe(ArrayList<Bruker> brukere) {
        brukerListe.getItems().clear();
        brukerListe.getItems().addAll(brukere);
    }

   
    // setMetode for overskrift
    public void setLblOverskrift(String tekst) { lblOverskrift.setText(tekst); }

    // setMetode for status
    public void setLblStatus(String tekst) { lblStatus.setText(tekst); }


}
