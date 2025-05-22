/**
 * @Author Severin Waller Sørensen
 */

/* Inneholder UI/GUI i javax for visning av brukergrensesnitt
 * for de ulike brukerne.. En metode får egenspesifiserte GUI for
 * Ledere, Testere og Utviklere? Evt. egen view */

 /* -----------------------------------------------------------------
  * NB! Bruk av inline "-fx-" hentet fra egne/private prosjekt
  * og tilpasset/modifisert for dette prosjektet. 
  * I disse prosjektene har jeg ofte spurt copilot om den kan skrive
  * et "utkast"/legge til default -fx- for background, border, etc.
  * som jeg deretter endrer/modifiserer selv. (gjort for å spare tid)
  * -----------------------------------------------------------------
  * Jeg har hentet diverse styling og strukturelement fra
  * disse (egne/private) prosjekte og tilpasset dem for dette.
  * -----------------------------------------------------------------
  * OPPDATERING: lagt mesteparten av "-fx-" i en egen styles.css fil.
  */

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
import javafx.scene.layout.Pane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
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

        // Brukes for å sentrere hjemPanel
        // (hindre at BorderPane CENTER tar hele plassen)
        Pane left = new Pane();
        left.setMinWidth(VINDU_BREDDE/3);
        Pane right = new Pane();
        right.setMinWidth(VINDU_BREDDE/3);
        Pane top = new Pane();
        top.setMinHeight(VINDU_HØYDE/3);
        Pane bunn = new Pane();
        bunn.setMinHeight(VINDU_HØYDE/3);
        hjemPanel.setLeft(left);
        hjemPanel.setRight(right);
        hjemPanel.setTop(top);
        hjemPanel.setBottom(bunn);

        hjemPanel.setCenter(lagLoggInnPanel());
        hjemPanel.setStyle("-fx-background-color:rgb(236, 236, 236)");
        setLblOverskrift("Saksbehandling");
        setLblStatus("");
        return hjemPanel;
    }

    // Lage LoggInnPanel
    private Node lagLoggInnPanel() {

        // ytre 'container' for sentrering
        VBox wrapper = new VBox();
        wrapper.setAlignment(Pos.CENTER);
        wrapper.setMaxHeight(VINDU_HØYDE/4);
        wrapper.setPrefWidth(VINDU_BREDDE/4);
       
        // Innholdet/selve loggInn-menyen
        VBox menyBoks = new VBox();
        menyBoks.getStyleClass().add("logginn-menyboks");

        // Styling for "header"/tittel (Login)
        Label lblLogin = new Label("Login");
        lblLogin.setFont(Font.font("Arial", 20));
        lblLogin.setStyle("-fx-font-weight: bold;");
        // Styling for undertittel (Velg bruker)
        Label lblVelgBruker = new Label("velg bruker: ");
        lblVelgBruker.setFont(Font.font("Verdana", 12));
        lblVelgBruker.setStyle("-fx-text-fill:rgb(180, 180, 180)");
        // Plassere labels i VBox
        VBox labelStyleBoks = new VBox(lblLogin, lblVelgBruker);
        labelStyleBoks.setSpacing(3);
        labelStyleBoks.setAlignment(Pos.CENTER);

        // Styling for Brukerliste (valgliste)
        HBox brukerlisteStyleBoks = new HBox(brukerListe);
        brukerlisteStyleBoks.setAlignment(Pos.CENTER);

        // Styling for "Bekreft"-button
        bekreftBrukerBtn.getStyleClass().add("bekreft-bruker-btn");

        // Plassere elementene i menyBoks
        menyBoks.getChildren().addAll(labelStyleBoks, brukerlisteStyleBoks, bekreftBrukerBtn);

        // Plassere menyen i wrapper. (+ returnere wrapper)
        wrapper.getChildren().add(menyBoks);
        return wrapper;
    }



    // Lage TopPanel
    private Node lagTopPanel() {
        // Opprette panel og legge til styling
        HBox topPanel = new HBox();
        topPanel.setStyle("-fx-background-color: rgb(6, 67, 0); -fx-border-width: 2px; -fx-border-color: black");
        topPanel.setSpacing(10);
        topPanel.setPadding(new Insets(1, 1, 1, 1));
        topPanel.setAlignment(Pos.CENTER);
        topPanel.setMinHeight(50);
        topPanel.setPrefHeight(50);

        // Legge til Style for lblOverskrift, samt legge til i topPanel
        lblOverskrift.getStyleClass().add("overskrift-label");
        topPanel.getChildren().add(lblOverskrift);

        return topPanel;
    }

    // Lage BunnPanel
    private Node lagBunnPanel() {
        // Oppretter panel og legger til styling
        HBox bunnPanel = new HBox();
        bunnPanel.setStyle("-fx-background-color:rgb(87, 87, 87); -fx-border-widht: 2px; -fx-border-color: black");
        bunnPanel.setSpacing(10);
        bunnPanel.setPadding(new Insets(15, 15, 15, 15));
        bunnPanel.setAlignment(Pos.CENTER);
        bunnPanel.setMinHeight(50);
        bunnPanel.setPrefHeight(50);

        // Endrer størrelse på label og legger til status (ikke gjort/ferdig enda)
        Font fonten = Font.font("ARIAL, 24");
        lblStatus.setFont(fonten);
        bunnPanel.getChildren().add(lblStatus);

        return bunnPanel;
    }

    // metode som bytter CENTER-panel
    public void visPanel(Node panel) {
        hovedPanel.setCenter(panel);
    }

    // metode for å vise LoggInn-panel (default/start)
    public Node visLoggInnPanel() {
        return lagHjemPanel();
    }

    // metode for å vise VenstreMeny
    public void visVenstreMeny(VBox venstreMeny) {
        hovedPanel.setLeft(venstreMeny);
    }

    // metode for å skjule VenstreMeny (f.eks. bruker trykker på "hjem")
    public void skjulVenstreMeny() {
        hovedPanel.setLeft(null);
    }





    // Returner hovedPanel
    public BorderPane getHovedPanel() { return hovedPanel; }

    // Returner brukerListe
    public ComboBox<Bruker> getBrukerListe() { return brukerListe; }

    // Returnerer bekreftBrukerBtn ("Bekreft"/valg av bruker) 
    public Button getBekreftBrukerBtn() { return bekreftBrukerBtn; }

    // setMetode for brukerListe (ComboBox)
    public void setBrukerListe(ArrayList<Bruker> brukere) {
        brukerListe.getItems().clear();
        brukerListe.getItems().addAll(brukere);
    }

   
    // setMetode for overskrift
    public void setLblOverskrift(String tekst) { lblOverskrift.setText(tekst); }

    // setMetode for status
    public void setLblStatus(String tekst) { lblStatus.setText(tekst); }

    //metode for å sette feilmelding
    public void setFeilmelding(String feilmelding) {
        lblStatus.setTextFill(Color.RED);
        lblStatus.setText(feilmelding);
    }
    //metode for å sette godkjenningsmelding
    public void setGodkjenning(String melding) {
        lblStatus.setTextFill(Color.WHITE);
        lblStatus.setText(melding);
    }

    //metode for å tømme loggen
    public void tømStatusMelding() {
        lblStatus.setText("");
        lblStatus.setTextFill(Color.WHITE);
    }

    public Label getLblStatus() {
        return lblStatus;
    }
}
