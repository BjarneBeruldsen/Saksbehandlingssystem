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
        venstreMeny.setStyle("-fx-background-color: #f0f0f0");
        btnHjem = new Button("Hovedside");
        venstreMeny.getChildren().add(btnHjem);
    }
    
    // Metode for å legge til TESTER-knapper
    public Node leggTilTesterKnapper() {
        btnTesterOpprettSak = new Button("Opprett sak");
        btnTesterInnsendteSaker = new Button("Mine saker");

        venstreMeny.getChildren().addAll(btnTesterOpprettSak, btnTesterInnsendteSaker);
        return venstreMeny;
    }

    // Metode for å legge til UTVIKLER-knapper
    public Node leggTilUtviklerKnapper() {
        btnUtviklerMineSaker = new Button("Mine saker");

        venstreMeny.getChildren().add(btnUtviklerMineSaker);
        return venstreMeny;
    }

    // Metode for å legge til LEDER-knapper
    public Node leggTilLederKnapper() {
        btnLederSeAlleSaker = new Button("Se alle saker");

        venstreMeny.getChildren().add(btnLederSeAlleSaker);
        return venstreMeny;
    }

    // Metode for å fjerne/nullstille knapper
    // fjerner alle Rolle-knapper, men beholder/legger til
    // "Hjem" knappen igjen
    public void nullstillKnapper() {
        venstreMeny.getChildren().clear();
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

}
